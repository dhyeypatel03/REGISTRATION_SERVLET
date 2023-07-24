package com.dhyey.register;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		String EncUpwd = MD5.getMD5EncryptedValue(upwd);
		String uemail = request.getParameter("uemail");

				
		UserService userService = new UserService();
		
		
        Random random = new Random();
        int temp = 100000 + random.nextInt(900000);
        String val =  String.valueOf(temp);
        try {
			@SuppressWarnings("unused")
			OTP otp = new OTP(val, uemail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        HttpSession session = request.getSession();
        session.setAttribute("uemail", uemail);
        
        SendingOTP.sendEmail(uemail, uname, val);

        String generatedOTP = val;
        HttpSession session1 = request.getSession();
        session.setAttribute("uname", uname);
        session.setAttribute("EncUpwd", EncUpwd);
        session.setAttribute("uemail", uemail);
        
        response.sendRedirect("AuthEmail.html");
	
        out.println("</h1></body></html>");
    }
}
