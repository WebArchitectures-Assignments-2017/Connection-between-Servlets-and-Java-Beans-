package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CartRemote;

public class ListCartOperation extends HttpServlet{
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException, NamingException {
	    	
	        CartRemote cm = (CartRemote) request.getSession().getAttribute("CartManager");
	        
	        List <String> books= cm.getContents();
	        request.setAttribute("booksOnCart", books);
	        
	        javax.servlet.RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ShowCartContent.jsp");
	        dispatcher.forward(request, response);
	      
	    }

	    
	    /**
	     * Handles the HTTP <code>GET</code> method.
	     *
	     * @param request servlet request
	     * @param response servlet response
	     * @throws ServletException if a servlet-specific error occurs
	     * @throws IOException if an I/O error occurs
	     */
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        try {
				processRequest(request, response);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    /**
	     * Handles the HTTP <code>POST</code> method.
	     *
	     * @param request servlet request
	     * @param response servlet response
	     * @throws ServletException if a servlet-specific error occurs
	     * @throws IOException if an I/O error occurs
	     */
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        try {
				processRequest(request, response);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }

	    /**
	     * Returns a short description of the servlet.
	     *
	     * @return a String containing servlet description
	     */
	    public String getServletInfo() {
	        return "Short description";
	    }// </editor-fold>

}
