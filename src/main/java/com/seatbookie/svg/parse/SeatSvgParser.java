package com.seatbookie.svg.parse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class SeatSvgParser {

	private static final String SPACE_ELEMENT_NAME = "svg";
	private static final String SPACE_WIDTH_ATTRIBUTE_NAME = "width";
	private static final String SPACE_HEIGHT_ATTRIBUTE_NAME = "height";
	private static final String SEAT_ELEMENT_NAME = "path";
	private static final String SEAT_ID_ATTRIBUTE_NAME = "id";
	private static final String SEAT_CLASS_ATTRIBUTE_NAME = "class";

	private InputStream svgInputStream;
	private XMLer xmLer;
	private HashSet<Seat> seats = new HashSet<Seat>();
	private Space space = new Space();

	public SeatSvgParser(InputStream svgInputStream) {
		super();
		this.svgInputStream = svgInputStream;
	}

	public void load() throws ParserConfigurationException, SAXException,
			IOException {
		this.xmLer = new XMLer(svgInputStream);
	}

	public void parse() {
		if (xmLer == null)
			throw new NotLoadSvgException();
		parseSpaceXML();
		parseSeatsXML();
	}

	private void parseSpaceXML() {
		String width = xmLer.getTagAttributeValue(SPACE_WIDTH_ATTRIBUTE_NAME);
		String height = xmLer.getTagAttributeValue(SPACE_HEIGHT_ATTRIBUTE_NAME);
		space.setWidth(width);
		space.setHeight(height);
	}


	private void parseSeatsXML() {
		ArrayList<XMLer> paths = xmLer.findXMLers(SEAT_ELEMENT_NAME);
		injectSeats(paths);
	}

	private void injectSeats(ArrayList<XMLer> paths) {
		for (XMLer x : paths) {
			injectASeat(x);
		}
	}

	private void injectASeat(XMLer x) {
		Seat seat = new Seat();
		String seatNo = getSeatNo(x);
		seat.setSeatNo(seatNo);
		seats.add(seat);
	}

	public HashSet<Seat> getSeats() {
		return seats;
	}
	
	public Space getSpace() {
		return space;
	}

	private String getSeatNo(XMLer x) {
		try {
			String _cls = x.getTagAttributeValue(SEAT_CLASS_ATTRIBUTE_NAME);
			return _cls.split(" ")[1];
		} catch (Exception e) {
			return x.getTagAttributeValue(SEAT_ID_ATTRIBUTE_NAME);
		}
	}

}
