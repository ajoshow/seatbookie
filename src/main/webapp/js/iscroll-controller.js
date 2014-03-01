var SBookie = SBookie || {};

function invalidateIScroll() {
    SBookie.myScroll = new IScroll('#viewport', {
    startX: 0,
    startY: 0,
    scrollY: true,
    scrollX: true,
    freeScroll: true,
    mouseWheel: true,
    indicators: {
      el: '#minimap',
      interactive: true
    }
  });
}


$(document).ready(function () {
    init();
    $(window).resize(function(){
        // $("#viewport").css("width",getViewportWidth());
        // $("#viewport").css("height",getViewportHeight());
    });
});

function init() {
    $("#viewport").css({
      width: getViewportWidth(),
      height: getViewportHeight()
    })
    invalidateIScroll();
    SBookie.myScroll.scrollTo(-$("#viewport-svg").width()/4, -$("#viewport-svg").height()/3, 2000, IScroll.utils.ease.back);
}

function getViewportWidth(){
   var $viewportW = $(window).width();
   var $actualImgWidth = $("#viewport-svg").width();
   return Math.min($viewportW, $actualImgWidth) + "px";
}

function getViewportHeight(){
  var $viewportH = Math.max(200, $(window).height()*2/3-100); // mins 100px from header height
  var $actualImgHeight = $("#viewport-svg").height();
  return Math.min($viewportH, $actualImgHeight) + "px";
}