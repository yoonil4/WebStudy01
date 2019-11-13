package video.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/video/getRealTimeView")
public class RealTimeView extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>             ");
		out.println("<html lang='en'>            ");
		
		// 1. head 출력
		out.println(HtmlTemplate.getHeadHtml());
		
		// 2. body 출력
		out.println("  <body>                    ");
		// 상단 메뉴 부분 출력 
		out.println(HtmlTemplate.getMenuHtml());
		// 메인 (동영상 목록) 부분 출력
		out.println("    <div class='container'> ");
		out.println(getRealTimeMainHtml(""));
		out.println("	</div>                   ");
		out.println("  </body>                   ");
		out.println("</html>                     ");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String videoLink = "";
		if(req.getParameter("videoLink") != null)
			videoLink = req.getParameter("videoLink").toString();
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>             ");
		out.println("<html lang='en'>            ");
		
		// 1. head 출력
		out.println(HtmlTemplate.getHeadHtml());
		
		// 2. body 출력
		out.println("  <body>                    ");
		// 상단 메뉴 부분 출력 
		out.println(HtmlTemplate.getMenuHtml());
		// 메인 (동영상 목록) 부분 출력
		out.println("    <div class='container'> ");
		out.println(getRealTimeMainHtml(videoLink));
		out.println("	</div>                   ");
		out.println("  </body>                   ");
		out.println("</html>                     ");
		
	}
	
	// 메인(실시간 동영상 출력) 부분 출력
	private String getRealTimeMainHtml(String videoLink)
	{
		StringBuilder sbTotal = new StringBuilder();
		
		// 실시간 동영상을 출력하는 HTML Format 세팅
		StringBuilder sbFormat = new StringBuilder();
		sbFormat.append("	  <form action='getRealTimeView' method='post'>                                                                                                                                                                     ")
				.append("		<div class='form-group' style='display:inline'>                                                                                                                                                                 ")
				.append("          <label for='inputVideoLink' style='display:block'>동영상 경로</label>                                                                                                                                            ")
				.append("          <input type='text' class='form-control' id='inputVideoLink' name='videoLink' placeholder='Enter Link (ex> https://www.youtube.com/embed/ob6fQeLnKOo)' value='%s' style='width:420px;display:inline;margin:0px 10px 10px 0px'> ")
				.append("		</div>                                                                                                                                                                                                          ")
				.append("        <button type='submit' class='btn btn-primary'>재생</button>                                                                                                                                                     ")
				.append("	  </form>	                                                                                                                                                                                                        ")
				.append("	                                                                                                                                                                                                                    ")
				.append("     <div class='card bg-light mb-3' style='width:420px;height:310px;margin:10px 0px 0px 0px'>                                                                                                                         ")
				.append("       <div class='card-body'>                                                                                                                                                                                         ")
				.append("         <iframe src='%s' frameborder='0' allow='accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture' allowfullscreen style='width:100%%;height:100%%'></iframe>	                                ")
				.append("       </div>                                                                                                                                                                                                          ")
				.append("     </div>                                                                                                                                                                                                            ");
		sbTotal.append(String.format(sbFormat.toString(), videoLink, videoLink));

		return sbTotal.toString();
	}
}
