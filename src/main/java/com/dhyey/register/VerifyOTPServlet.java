package com.dhyey.register;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerifyOTPServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userOTP = request.getParameter("otp");
        
        HttpSession session = request.getSession();
        String uemail = (String) session.getAttribute("uemail");

        UserService userService = new UserService();
        List<String> storedOTPs = userService.getOTPByEmail(uemail);

        if (userOTP != null && storedOTPs.contains(userOTP)) {
        	
        	HttpSession session1 = request.getSession();
            String uname = (String) session.getAttribute("uname");
            String EncUpwd = (String) session.getAttribute("EncUpwd");
            	
            String status = userService.registration(uname, EncUpwd, uemail);
            response.sendRedirect("loginform.html");
            
            if (status.equals("failure")) {

            }
            else if (status.equals("existed")) {
            	response.getWriter().println("User already existed");
                response.sendRedirect("loginform.html");
            }
       
            }
       
            else
            {
             response.getWriter().println("Invalid OTP. Please try again.");
            }
        	
        }
        
    }


