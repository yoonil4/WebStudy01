package video.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VideoListServlet extends HttpServlet {
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
		out.println(getVideoListMainHtml());
		out.println("	</div>                   ");
		out.println("  </body>                   ");
		out.println("</html>                     ");
	}
	
	// VideoList 출력 문자열 얻어와, 출력
	private String getVideoListMainHtml()
	{
		StringBuilder sbTotal = new StringBuilder();
		
		// 1. 출력할 동영상 개수 확인
		ServletContext sc = this.getServletContext();
		int videoCnt = 0;
		if( sc.getAttribute("videoCnt") != null 
		&& !(sc.getAttribute("videoCnt").toString().isEmpty()) )
			videoCnt = Integer.parseInt(sc.getAttribute("videoCnt").toString());
		
		// 2. 컨텍스트 영역에서 저장된 동영상 정보 얻어와, 출력할 동영상 html 작성 
		if(videoCnt > 0)
		{
			// 동영상 정보를 출력하는 HTML Format 세팅
			StringBuilder sbFormat = new StringBuilder();
			sbFormat.append("<div class='card bg-light mb-3' style='width:430px;height:300px'>")
					.append(" <div class='card-header'>Title : %s</div>")
					.append(" <div class='card-body'> ")
					.append("  <iframe src='%s' frameborder='0' allow='accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture' allowfullscreen style='width:100%%;height:100%%'></iframe>")
					.append(" </div>")
					.append("</div>");
			
			// HTML Format에, 동영상 정보를 세팅, 출력문에 추가
			for(int videoIdx = 0; videoIdx < videoCnt; videoIdx++)
			{
				String videoTitleAttr = "videoTitle" + videoIdx;
				String videoLinkAttr = "videoLink" + videoIdx;
				String videoTitle = sc.getAttribute(videoTitleAttr).toString();
				String videoLink = sc.getAttribute(videoLinkAttr).toString();
				
				sbTotal.append(String.format(sbFormat.toString(), videoTitle, videoLink));
			}			
		}
		// 출력할 동영상이 없는 경우, 출력할 HTML 작성
		else
		{
			sbTotal.append("<div class='bs-component'>")
				   .append(" <div class='jumbotron'>")
				   .append("  <h1 class='display-4' style='font-size:2rem'>현재 볼수 있는 동영상이 없습니다!</h1>")
				   .append("  <hr class=\"my-4\">")
				   .append("  <p>현재 볼수 있는 동영상이 없습니다. 상단 메뉴의 \"동영상 등록\"을 통해, 동영상을 추가하실 수 있습니다.</p>")
				   .append(" </div>")
				   .append("</div>");
		}
		
		return sbTotal.toString();
	}
}
