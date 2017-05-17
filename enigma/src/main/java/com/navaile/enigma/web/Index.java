/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navaile.enigma.web;

import com.navaile.enigma.Enigma4K;
import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author navaile
 */
@WebServlet(name = "index", urlPatterns = {"/index", ""})
public class Index extends HttpServlet {
	
	private static final Logger LOG = LoggerFactory.getLogger(Enigma4K.class);

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param req servlet request
	 * @param res servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		BasicConfigurator.configure();
		org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger();
			logger4j.setLevel(Level.ERROR);
		
//		HttpServletRequest httpRequest = (HttpServletRequest) request;

		ServletContext context = getServletContext();
			String path = context.getRealPath("/WEB-INF/directory");
			
		System.out.println("Index.processRequest(): " + path);

		req.setAttribute("rotorCount", Enigma4K.COUNT_MIN);
		req.setAttribute("pbCount", Enigma4K.COUNT_MIN);
		
		req.setAttribute("roCountMax", Enigma4K.RO_COUNT_LOCK);
		req.setAttribute("pbCountMax", Enigma4K.PB_COUNT_LOCK);
		
		if(req.getParameter("encrypt") != null || req.getParameter("decrypt") != null) {

			String rotorCountStr = req.getParameter("rotorCount");
			String pbCountStr = req.getParameter("pbCount");
			String passPhrase = req.getParameter("passPhrase");
			String handle = req.getParameter("handle");
			String text = req.getParameter("text").trim();
			
			System.out.println("Enigma4K.Index rotorCountStr: " + rotorCountStr +
				", pbCountStr: " + pbCountStr + ", passPhrase: " + passPhrase +
				", handle: " + handle + ", text: " + text);
			
			boolean error = false;

			Integer rotorCount = Enigma4K.COUNT_MIN;
			if(rotorCountStr != null && rotorCountStr.length() != 0) {
				
				try {	rotorCount = Integer.parseInt(rotorCountStr);		}
				catch(NumberFormatException ex) {
					req.setAttribute("rotorCountErr", "err");
					error = true;
				}

				req.setAttribute("rotorCount", rotorCount);
			}
			
			Integer pbCount = Enigma4K.COUNT_MIN;
			if(pbCountStr != null && pbCountStr.length() != 0) {
				
				try {	pbCount = Integer.parseInt(pbCountStr);				}
				catch(NumberFormatException ex) {
					req.setAttribute("pbCountErr", "err");
					error = true;
				}

				req.setAttribute("pbCount", pbCount);
			}

			if(passPhrase == null || passPhrase.length() == 0) {
					req.setAttribute("passPhraseErr", "err");
					error = true;
			}
			else {	req.setAttribute("passPhrase", passPhrase);			}
			
			if(handle == null || handle.length() == 0) {
					req.setAttribute("handleErr", "err");
					error = true;
			}
			else {	req.setAttribute("handle", handle);					}
			
			if(text == null || text.length() == 0) {
					req.setAttribute("textErr", "err");
					error = true;
			}
			else {	req.setAttribute("text", text);						}
			
			if(!error)
				if(req.getParameter("encrypt") != null) {
					
					try {

						String msgID = Enigma4K.genMsgID();
						LOG.error("Index.msgID: " + msgID);
						Enigma4K enigma = new Enigma4K(path, passPhrase, handle, msgID, rotorCount, pbCount);
							req.setAttribute("msgID", msgID);

						String encryptText = enigma.encryptText(text);
						req.setAttribute("text", msgID + encryptText);
					}
					catch(Exception e) {
						req.setAttribute("encryptErr", true);
						LOG.error("Encryption Error", e);
					}
				}
				else if(req.getParameter("decrypt") != null) {
					
					try {

						String msgID = text.substring(0, 9);
						LOG.error("Index.msgID: " + msgID);
						String cryptText = text.substring(9, text.length());
						
						Enigma4K enigma = new Enigma4K(path, passPhrase, handle, msgID, rotorCount, pbCount);
						
						String decryString = enigma.decryptText(cryptText);
						req.setAttribute("text", decryString);
						req.setAttribute("msgID", msgID);
					}
					catch(Exception e) {	LOG.error("Decryption Error", e);	}
				}
		}

		req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, res);
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Enigma 4K";
	}// </editor-fold>

}
