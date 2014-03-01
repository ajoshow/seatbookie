package com.seatbookie.svg.parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Test {
	private static Long time = 0l;

	public static void main(String args[]) throws Exception {
		time = System.currentTimeMillis();
		File f = new File("D://seat_map_02.svg");
		FileInputStream fis = new FileInputStream(f);
		SeatSvgInsertDbServiceUtil seatSvgInsertDbServiceUtil = SeatSvgInsertDbServiceUtil.getInstance();
		seatSvgInsertDbServiceUtil.insetAll(1l, fis);
	}
}
