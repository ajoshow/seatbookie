package com.seatbookie.svg.upload.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class FileUploadServlet
 */
public class FileUploadServlet extends HttpServlet {

	private static final String JSON_GRAPHID_KEY = "graphId";
	private static final String JSON_SUCCESS_KEY = "success";
	private static final String JSON_EXCEPTION_KEY = "exception";

	public void init() {
		// Get the file location where it would be stored.
		// filePath = getServletContext().
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		RequestFiliesSaver requestFiliesSaver = new RequestFiliesSaver(request);
		requestFiliesSaver.parseAndSaveFile();
		ArrayList<SvgInfoStore> stores = requestFiliesSaver.getSvgInfoStores();
		returnResponse(response, stores);
//		response.setContentType("application/json");
//		JSONObject jo = new JSONObject();
//		jo.put("kk", "dd");
//		response.getWriter().write(jo.toString());

	}

	private void returnResponse(HttpServletResponse response,
			ArrayList<SvgInfoStore> stores) throws IOException {
		JSONArray ja = new JSONArray();
		for (SvgInfoStore sis : stores) {
			JSONObject jo = getProcessResult(sis);
			ja.put(jo);
		}
		response.getWriter().write(ja.toString());
	}

	private JSONObject getProcessResult(SvgInfoStore sis) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(JSON_GRAPHID_KEY, sis.getGraphId());
		jsonObject.put(JSON_SUCCESS_KEY, sis.isSuccess());
		jsonObject.put(JSON_EXCEPTION_KEY, sis.getException());
		return jsonObject;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		throw new ServletException("GET method used with "
				+ getClass().getName() + ": POST method required.");
	}

}
