package video.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/video/registVideo")
public class RegistVideoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>   ");
		out.println("<html lang='en'>  ");
		
		// 1. head 출력
		out.println(HtmlTemplate.getHeadHtml());
		
		// 2. body 출력
		out.println("  <body>          ");
		// 상단 메뉴 부분 출력 
		out.println(HtmlTemplate.getMenuHtml());
		// 메인 (동영상 등록) 부분 출력
		out.println(getRegistMainHtml());
		out.println("  </body>         ");
		out.println("</html>           ");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String videoName = "", videoLink = "";
		if(req.getParameter("videoName") != null)
			videoName = req.getParameter("videoName").toString();
		if(req.getParameter("videoLink") != null)
			videoLink = req.getParameter("videoLink").toString();
		
		// 요청에서 얻어온 Video 정보를 Servlet Context 단에 저장
		String errMsg = registVideoInfo(videoName, videoLink);
		
		// 제대로 저장된 경우->등록되었다는 정보를 출력 후, 다시 등록 페이지로 이동
		if(errMsg.isEmpty())
		{
			// 1) 등록되었는다는 정보를 화면에 출력
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<!DOCTYPE html>   ");
			out.println("<html lang='en'>  ");
			// 1. head 출력
			out.println(HtmlTemplate.getHeadHtml());
			// 2. body 출력
			out.println("  <body>          ");
			// 상단 메뉴 부분 출력 
			out.println(HtmlTemplate.getMenuHtml());
			// 메인 (동영상 등록 성공) 부분 출력
			out.println(getSucssMainHtml());
			out.println("  </body>         ");
			out.println("</html>           ");
			
			// 2) 처음의 등록 페이지로 이동
			resp.addHeader("Refresh", "2;url=registVideo");
		}
		// 제대로 저장되지 않은 경우->등록이 잘 안되었다는 정보를 출력 후, 다시 등록 페이지로 이동
		else
		{
			// 1) 등록되었는다는 정보를 화면에 출력
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<!DOCTYPE html>   ");
			out.println("<html lang='en'>  ");
			// 1. head 출력
			out.println(HtmlTemplate.getHeadHtml());
			// 2. body 출력
			out.println("  <body>          ");
			// 상단 메뉴 부분 출력 
			out.println(HtmlTemplate.getMenuHtml());
			// 메인 (동영상 등록 에러) 부분 출력
			out.println(getErrorMainHtml(errMsg));
			out.println("  </body>         ");
			out.println("</html>           ");
			
			// 2) 처음의 등록 페이지로 이동
			resp.addHeader("Refresh", "2;url=registVideo");
		}
	}
	
	// 요청에서 얻어온 Video 정보를 Servlet Context 단에 저장
	private String registVideoInfo(String videoName, String videoLink)
	{
		String errMsg = "";
		
		// 동영상 제목 값을 제대로 입력하지 않은 경우
		if(videoName.isEmpty())
		{
			errMsg = "동영상의 제목을 입력해주세요.";
		}
		// 동영상 링크 값을 제대로 입력하지 않은 경우
		else if(videoLink.isEmpty())
		{
			errMsg = "동영상의 링크를 입력해주세요.";
		}
		// Input 값을 제대로 입력한 경우
		else
		{
			// 저장되있는 동영상 개수 확인
			ServletContext sc = this.getServletContext();
			int videoCnt = 0;
			if( sc.getAttribute("videoCnt") != null 
			&& !(sc.getAttribute("videoCnt").toString().isEmpty()) )
				videoCnt = Integer.parseInt(sc.getAttribute("videoCnt").toString());
			
			// Video 정보를 Servlet Context에 저장
			int newVideoIdx = videoCnt;
			sc.setAttribute("videoTitle" + newVideoIdx, videoName);
			sc.setAttribute("videoLink" + newVideoIdx, videoLink);
			
			// Video 개수를 증가시킴
			sc.setAttribute("videoCnt", videoCnt + 1);
		}
		
		return errMsg;
	}
	
	// 메인 (동영상 등록) 부분 출력
	private String getRegistMainHtml()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("    <div class='container'>                                                                                                                                                                                                  ")
		  .append("	  <form action='registVideo' method='post'>                                                                                                                                                                                 ")
		  .append("	    <div class='form-group'>                                                                                                                                                                                                ")
		  .append("          <label for='inputVideoTitle'>동영상 제목</label>                                                                                                                                                                       ")
		  .append("          <input type='text' class='form-control' id='inputVideoTitle' name='videoName' placeholder='Enter Video Title' style='width:320px'>                                                                                 ")
		  .append("		</div>                                                                                                                                                                                                                  ")
		  .append("		<div class='form-group' style='display:inline'>                                                                                                                                                                         ")
		  .append("          <label for='inputVideoLink' style='display:block'>동영상 경로</label>                                                                                                                                                  ")
		  .append("          <input type='text' class='form-control' id='inputVideoLink' name='videoLink' placeholder='Enter Link (ex> https://www.youtube.com/embed/ob6fQeLnKOo)' style='width:480px;display:inline;margin:0px 15px 20px 0px'> ")
		  .append("		</div>                                                                                                                                                                                                                  ")
		  .append("        <button type='submit' class='btn btn-primary'>등록</button>                                                                                                                                                            ")
		  .append("	  </form>                                                                                                                                                                                                                   ")
		  .append("	</div>                                                                                                                                                                                                                      ");
		  
		return sb.toString();
	}
	
	// 메인 (동영상 등록 성공) 부분 출력
	private String getSucssMainHtml()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<div class='bs-component'>                                           ")
		  .append(" <div class='jumbotron'>                                             ")
		  .append("  <h1 class='display-4' style='font-size:2rem'>동영상을 등록하였습니다.</h1> ")
		  .append("  <hr class=\"my-4\">                                                ")
		  .append("  <p> 2초후, 등록 페이지로 이동합니다.</p>                                     ") 
		  .append(" </div>                                                              ")
		  .append("</div>                                                               ");
		return sb.toString();
	}
	
	// 메인 (동영상 등록 에러) 부분 출력
	private String getErrorMainHtml(String errMsg)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<div class='bs-component'>                                              ")
		  .append(" <div class='jumbotron'>                                                ")
		  .append("  <h1 class='display-4' style='font-size:2rem'>동영상을 등록할 수 없습니다.</h1> ")
		  .append("  <hr class=\"my-4\">                                                   ")
		  .append("  <p>                                                                   ") 
		  .append(errMsg)
		  .append(  "</p>                                                                  ")
		  .append(" </div>                                                                 ")
		  .append("</div>                                                                  ");
		return sb.toString();
	}
}
