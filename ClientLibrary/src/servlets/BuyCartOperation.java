package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BookManagerRemote;
import beans.CartRemote;

public class BuyCartOperation extends HttpServlet{
	 private static final String appName = "LibraryServerSession";
	 private static final String moduleName = "LibraryServerSession-ejb";
	 private static final String bookName = "BookManager";
	 private static final String bookView = "beans.BookManagerRemote";

	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException, NamingException {
	    	 Properties jndiProperties = new Properties();
	         jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
	         jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
	         jndiProperties.put("jboss.naming.client.ejb.context", true);
	         final Context ctx = new InitialContext(jndiProperties);
	        CartRemote cm = (CartRemote) request.getSession().getAttribute("CartManager");
	        cm.buy();
	        request.getSession().setAttribute("CartManager", cm);
	        
	        BookManagerRemote bm = (BookManagerRemote) ctx.lookup(appName + "/" +moduleName + "/" +bookName + "!" + bookView);
	        List <String> books= bm.listBook();
	        
	        request.getSession().setAttribute("bookslist", books);
	        response.sendRedirect("/ClientLibrary/ListBook.jsp");
	      
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
