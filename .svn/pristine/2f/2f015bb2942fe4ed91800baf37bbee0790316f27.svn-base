$(document).ready(function($) {
	$("#fileToUpload").change(function() {
		ajaxFileUpload();
	});
});

function showUploadDialog() {
	$("#loading").css('visibility', 'hidden');
	$("#showImg").css('display', 'none');
	$("#open").css('display', 'none');
	$("#fileToUpload").click();
}

function showLoading() {
	$("#loading").css('visibility', 'visible');
	$("#showImg").css('display', 'none');
	$("#open").css('display', 'none');
}

function hideLoading() {
	$("#showImg").css('display', 'inline-block');
	$("#open").css('display', 'inline-block');
	$("#loading").css('visibility', 'hidden');
}

function setImageSrc(graphId) {
	$("#showImg").attr('src', './' + graphId + ".svg");
	$("#open").unbind();
	$("#open").click(function() {
		window.open("./bookie.html?graphId="+graphId, '_blank');
	});
}

function ajaxFileUpload() {
	showLoading();

	/*
	 * prepareing ajax file upload url: the url of script file handling the
	 * uploaded files fileElementId: the file type of input element id and it
	 * will be the index of $_FILES Array() dataType: it support json, xml
	 * secureuri:use secure protocol success: call back function when the ajax
	 * complete error: callback function when the ajax failed
	 */
	$.ajaxFileUpload({
		url : './fileupload',
		secureuri : false,
		fileElementId : 'fileToUpload',// 這個是對應到 input file 的 ID
		dataType : 'json',
		success : function(data, status) {
			hideLoading();
			setImageSrc( data[0].graphId );
			$("#fileToUpload").change(function() {
				ajaxFileUpload();
			});
		},
		error : function(data, status, e) {
			alert(e);
		}
	});

	return false;

}