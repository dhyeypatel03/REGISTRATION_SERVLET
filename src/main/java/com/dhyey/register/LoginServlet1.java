
package com.dhyey.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		String EncUpwd = MD5.getMD5EncryptedValue(upwd);
		UserService userService = null;
		userService = new UserService();
		String status = userService.checkLogin(uname, EncUpwd);
		out.println("<html><body bg color='lightblue'>");
		out.println("<br><br><br><br><br>");
		out.println("<h1 style='color:red;' algn ='center'>");
		if(status.equals("success")) {
			response.sendRedirect("homelogin.html");		}
		else {
			out.println("login failure");
		}
		out.println("</h1></body></html>");
		
	}

}