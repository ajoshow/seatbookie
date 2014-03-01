package com.seatbookie.svg.upload.servlet;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class RequestFiliesSaver {

	private static int maxFileSize = 1024 * 1024 * 1024;
	private static int maxMemSize = 1024 * 1024 * 1024;

	private HttpServletRequest request;
	private String servletPath;
	private ServletFileUpload servletFileUpload;
	private ArrayList<SvgInfoStore> svgInfoStores = new ArrayList<SvgInfoStore>();

	public RequestFiliesSaver(HttpServletRequest request) {
		super();
		this.request = request;
		this.servletPath = request.getSession().getServletContext()
				.getRealPath("/");
	}

	public void parseAndSaveFile() {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart)
			parseFile();
		else
			throw new NoFileException();

	}

	private void parseFile() {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		factory.setRepository(new File(servletPath));
		servletFileUpload = new ServletFileUpload(factory);
		servletFileUpload.setSizeMax(maxFileSize);
		saveBatchFile();
	}

	private void saveBatchFile() {
		try {
			Iterator i = parseBathFile();
			saveBatchFile(i);
		} catch (Exception ex) {
			throw new UploadFileParseFailException();
		}
	}

	private void saveBatchFile(Iterator i) throws Exception {
		while (i.hasNext()) {
			FileItem fi = (FileItem) i.next();
			SvgInfoStore store = new SvgInfoStore(fi);
			store.parseAndSaveFile(servletPath);
			svgInfoStores.add(store);
		}
	}

	private Iterator parseBathFile() throws FileUploadException {
		// Parse the request to get file items.
		List fileItems = servletFileUpload.parseRequest(request);
		// Process the uploaded file items
		return fileItems.iterator();

	}

	public ArrayList<SvgInfoStore> getSvgInfoStores() {
		return svgInfoStores;
	}

}
