package com.seatbookie.svg.parse;

import java.io.InputStream;

public class SeatSvgInsertDbServiceUtil {

	private static final SeatSvgInsertDbServiceUtil instance = new SeatSvgInsertDbServiceUtil();
	private ConnectFirebase connectFirebase = new ConnectFirebase();

	private SeatSvgInsertDbServiceUtil() {
	}

	public void insetAll(Long graphId, InputStream io) {
		SeatSvgParser seatSvgParser = new SeatSvgParser(io);
		try {
			seatSvgParser.load();
		} catch (Exception e) {
			throw new LoadSvgFailException();
		}
		seatSvgParser.parse();
		insertToDb(graphId, seatSvgParser);
	}

	private void insertToDb(Long graphId, SeatSvgParser seatSvgParser) {
		String[] namies = new String[seatSvgParser.getSeats().size()];
		int i = 0;
		for (Seat s : seatSvgParser.getSeats()) {
			namies[i++] = s.getSeatNo();
		}
		connectFirebase.addGraphAndSeat(graphId, seatSvgParser.getSpace()
				.getWidth(), seatSvgParser.getSpace().getHeight(), namies);
	}

	public static SeatSvgInsertDbServiceUtil getInstance() {
		return instance;
	}

}
