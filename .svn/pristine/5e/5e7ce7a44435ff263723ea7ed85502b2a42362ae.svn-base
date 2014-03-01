package com.seatbookie.svg.upload.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.fileupload.FileItem;

import com.seatbookie.svg.parse.SeatSvgInsertDbServiceUtil;

public class SvgInfoStore {

	private static final String SVG_CONTENT_TYPE = "image/svg+xml";
	private static final String SVG_FILE_EXTENSIONS = ".svg";

	private String fieldName;
	private String fileName;
	private String contentType;
	private boolean isInMemory;
	private long sizeInBytes;
	private boolean formField;
	private FileItem fileItem;
	private File svgFile;
	private boolean success = false;
	private Long graphId;
	private Exception exception;

	public SvgInfoStore(FileItem fileItem) {
		super();
		this.graphId = System.currentTimeMillis();
		this.fileItem = fileItem;
	}

	public void parseAndSaveFile(String prefixFilePath) {
		try {
			parseFileInfo();
			if (formField)
				throw new IsFormFieldException();
			saveFile(prefixFilePath);
			insertToDb();
			success = true;
		} catch (Exception e) {
			exception = e;
		}

	}

	private void insertToDb() throws FileNotFoundException {
		SeatSvgInsertDbServiceUtil ssidu = SeatSvgInsertDbServiceUtil
				.getInstance();
		FileInputStream fis = new FileInputStream(svgFile);
		ssidu.insetAll(graphId, fis);
	}

	private void saveFile(String prefixFilePath) throws Exception {

		if (!SVG_CONTENT_TYPE.equalsIgnoreCase(contentType))
			throw new NotSvgTypeException();

		if (fileName.lastIndexOf("\\") >= 0) {
			svgFile = new File(prefixFilePath + graphId + SVG_FILE_EXTENSIONS);
		} else {
			svgFile = new File(prefixFilePath + +graphId + SVG_FILE_EXTENSIONS);
		}
		fileItem.write(svgFile);

	}

	private void parseFileInfo() {
		fieldName = fileItem.getFieldName();
		fileName = fileItem.getName();
		contentType = fileItem.getContentType();
		isInMemory = fileItem.isInMemory();
		sizeInBytes = fileItem.getSize();
		formField = fileItem.isFormField();
	}

	public File getSvgFile() {
		return svgFile;
	}

	public boolean isSuccess() {
		return success;
	}

	public Exception getException() {
		return exception;
	}

	public Long getGraphId() {
		return graphId;
	}

}
