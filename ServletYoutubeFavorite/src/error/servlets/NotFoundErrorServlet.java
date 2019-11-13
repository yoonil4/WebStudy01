package error.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/error/pageNotFound")
public class NotFoundErrorServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String root = req.getContextPath();
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>                                                                ");
		out.println("<html lang='en'>                                                               ");
		out.println("  <head>                                                                       ");
		out.println("    <meta charset='utf-8'>                                                     ");
		out.println("    <title>Youtube Favorite</title>                                            ");
		out.println("    <meta name='viewport' content='width=device-width, initial-scale=1'>       ");
		out.println("    <meta http-equiv='X-UA-Compatible' content='IE=edge' />                    ");
		out.println("    <link rel='stylesheet' href='"+ root +"/css/bootstrap.css' media='screen'> ");
		out.println("    <link rel='stylesheet' href='"+ root +"/css/custom.min.css'>               ");
		out.println("    <script src='"+ root +"/js/jquery.min.js'></script>                        ");
		out.println("    <script src='"+ root +"/js/popper.min.js'></script>                        ");
		out.println("    <script src='"+ root +"/js/bootstrap.min.js'></script>                     ");
		out.println("    <script src='"+ root +"/js/custom.js'></script>                            ");
		out.println("  </head>                                                                      ");
		out.println(" <body style='padding-top:70px'>	                                            ");
		out.println("  <div class='container'>                                                      ");
		out.println("   <div class='alert alert-dismissible alert-danger'>                          ");
		out.println("    <h5><strong>Oops!,Sorry, Page Not Found!</strong></h5>                     ");
		out.println("    The page you requested cannot be found.                                    ");
		out.println("   </div>                                                                      ");
		out.println("  </div>                                                                       ");
		out.println(" </body>	                                                                    ");
		out.println("</html>                                                                        ");
	}
}
