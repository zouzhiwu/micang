package com.game.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class GameServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("do post");
	}
	
	/*
	 * private Map<String, String> getParameterStringMap(HttpServletRequest request)
	 * { Map<String, String[]> properties = request.getParameterMap(); Map<String,
	 * String> returnMap = new HashMap<String, String>(); String name = ""; String
	 * value = ""; for (Map.Entry<String, String[]> entry : properties.entrySet()) {
	 * name = entry.getKey(); String[] values = entry.getValue(); if (null ==
	 * values) { value = ""; } else if (values.length > 1) { for (int i = 0; i <
	 * values.length; i++) { value = values[i] + ","; } value = value.substring(0,
	 * value.length() - 1); } else { value = values[0]; } returnMap.put(name,
	 * value); } return returnMap; }
	 */
	
}
