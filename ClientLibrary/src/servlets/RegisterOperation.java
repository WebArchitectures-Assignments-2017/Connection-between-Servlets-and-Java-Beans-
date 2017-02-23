package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

public class RegisterOperation  extends HttpServlet {

    String inputuser;
    String inputpswd;
    private static final String appName = "LibraryServerSession";
	 private static final String moduleName = "LibraryServerSession-ejb";
	 private static final String accountName = "AccountManager";
	 private static final String accountView = "beans.AccountManagerRemote";
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException {
    	
    	 HttpSession session = request.getSession();
         inputuser = request.getParameter("Inputname");
         inputpswd = request.getParameter("InputPassword");
         
         
         Properties jndiProperties = new Properties();
         jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
         jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
         jndiProperties.put("jboss.naming.client.ejb.context", true);
         final Context ctx = new InitialContext(jndiProperties);
                         
         AccountManagerRemote am = (AccountManagerRemote) ctx.lookup(appName + "/" + moduleName + "/" + accountName + "!" + accountView);
         
         am.registerAccount(inputuser, inputpswd);
       
         request.setAttribute("Registration", true);
         
         javax.servlet.RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.jsp");
	      dispatcher.forward(request, response);
      
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


