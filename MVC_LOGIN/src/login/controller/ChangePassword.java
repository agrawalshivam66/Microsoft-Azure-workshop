package login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.db.Users;
import login.model.UsersDao;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/login.controller.ChangePassword")
public class ChangePassword extends HttpServlet {

       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opass = request.getParameter("opass");
		String npass = request.getParameter("npass");
		String cpass = request.getParameter("cpass");
	
		Users u = new Users();
		
		HttpSession session = request.getSession();
		u.setEmail((String)session.getAttribute("email"));
		u.setPassword(opass);
		
		int status = UsersDao.validate(u);
		
		if(status>0)
		{
			if(npass.equals(cpass))
			{
				u.setPassword(npass);
				int status2 =  UsersDao.updatePassword(u);
				if(status2>0)
				{
					response.sendRedirect("changePassword.jsp?msg=Password Sucessfully Changed");
				}
				else
				{
					response.sendRedirect("changePassword.jsp?msg=Password not Changed");
				}
			}
			
			else {
				response.sendRedirect("changePassword.jsp?msg=New password and confoirm password mismatch");
			}
			
		}
		else
			response.sendRedirect("changePassword.jsp?msg=Invalid Current Password");
			
	}

}
