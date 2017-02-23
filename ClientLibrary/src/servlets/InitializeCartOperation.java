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
import javax.servlet.http.HttpSession;

import beans.AccountManagerRemote;
import beans.BookManagerRemote;
import beans.CartRemote;

public class InitializeCartOperation extends HttpServlet {

    String inputuser;
    String inputpswd;
	private static final String appName = "LibraryServerSession";
    private static final String moduleName = "LibraryServerSession-ejb";
    private static final String bookName = "BookManager";
    private static final String bookView = "beans.BookManagerRemote";
    private static final String cartName = "CartBean";
    private static final String cartView = "beans.CartRemote";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException {
    	
        HttpSession session = request.getSession();
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        jndiProperties.put("jboss.naming.client.ejb.context", true);

        final Context ctx = new InitialContext(jndiProperties);
        CartRemote cm = (CartRemote) ctx.lookup(appName + "/" +moduleName + "/" + cartName + "!" + cartView); 
        BookManagerRemote bm = (BookManagerRemote) ctx.lookup(appName + "/" +moduleName + "/" +bookName + "!" + bookView);
        List <String> books= bm.listBook(); 
        session.setAttribute("bookslist", books);
        cm.initialize();
      
        request.getSession().setAttribute("CartManager", cm);
       
        javax.servlet.RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ListBook.jsp");
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
    @Override
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
    @Override
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
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}