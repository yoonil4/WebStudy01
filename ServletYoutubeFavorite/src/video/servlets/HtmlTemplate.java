package video.servlets;

public class HtmlTemplate{
	
	// Head Html 조립, 반환
	public static String getHeadHtml()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("  <head>                                                                 ")
		  .append("    <meta charset='utf-8'>                                               ")
		  .append("    <title>Youtube Favorite</title>                                      ")
		  .append("    <meta name='viewport' content='width=device-width, initial-scale=1'> ")
		  .append("    <meta http-equiv='X-UA-Compatible' content='IE=edge' />              ")
		  .append("    <link rel='stylesheet' href='../css/bootstrap.css' media='screen'>   ")
		  .append("    <link rel='stylesheet' href='../css/custom.min.css'>                 ")
		  .append("    <script src='../js/jquery.min.js'></script>                          ")
		  .append("    <script src='../js/popper.min.js'></script>                          ")
		  .append("    <script src='../js/bootstrap.min.js'></script>                       ")
		  .append("    <script src='../js/custom.js'></script>                              ")
		  .append("  </head>                                                                ");

		return sb.toString();
	}
	
	// Menu Html 조립, 반환
	public static String getMenuHtml()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("    <div class='navbar navbar-expand-lg fixed-top navbar-dark bg-primary'>                                                                                                                      ")
		  .append("      <div class='container'>                                                                                                                                                                   ")
		  .append("        <a href='getVideoList' class='navbar-brand'>Youtube Favorite</a>                                                                                                                        ")
		  .append("		<button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarResponsive' aria-controls='navbarResponsive' aria-expanded='false' aria-label='Toggle navigation'> ")
		  .append("          <span class='navbar-toggler-icon'></span>                                                                                                                                             ")
		  .append("        </button>                                                                                                                                                                               ")
		  .append("        <div class='collapse navbar-collapse' id='navbarResponsive'>                                                                                                                            ")
		  .append("          <ul class='navbar-nav'>                                                                                                                                                               ")
		  .append("		    <!-- <li class='nav-item active'> -->                                                                                                                                                  ")
		  .append("		    <li class='nav-item'>                                                                                                                                                                  ")
		  .append("              <a class='nav-link' href='getVideoList'>동영상 목록</a>                                                                                                                               ")
		  .append("            </li>                                                                                                                                                                               ")
		  .append("            <li class='nav-item'>                                                                                                                                                               ")
		  .append("			  <a class='nav-link' href='registVideo'>동영상 등록</a>                                                                                                                                   ")
		  .append("            </li>                                                                                                                                                                               ")
		  .append("            <li class='nav-item'>                                                                                                                                                               ")
		  .append("              <a class='nav-link' href='getRealTimeView'>실시간 보기</a>                                                                                                                            ")
		  .append("            </li>                                                                                                                                                                               ")
		  .append("			<li class='nav-item dropdown'>                                                                                                                                                         ")
		  .append("              <a class='nav-link dropdown-toggle' data-toggle='dropdown' href='#' id='userMng'>사용자 정보 관리 <span class='caret'></span></a>                                                       ")
		  .append("              <div class='dropdown-menu'>                                                                                                                                                       ")
		  .append("                <a class='dropdown-item' target='_blank' href='https://jsfiddle.net/bootswatch/m0nv7a0o/'>사용자 등록</a>                                                                           ")
		  .append("				<a class='dropdown-item' target='_blank' href='https://jsfiddle.net/bootswatch/m0nv7a0o/'>사용자 삭제</a>                                                                              ")
		  .append("                <div class='dropdown-divider'></div>                                                                                                                                            ")
		  .append("                <a class='dropdown-item' href='#'>admin 페이지</a>                                                                                                 ")
		  .append("              </div>                                                                                                                                                                            ")
		  .append("            </li>                                                                                                                                                                               ")
		  .append("	      </ul>                                                                                                                                                                                    ")
		  .append("        </div>                                                                                                                                                                                  ")
		  .append("      </div>                                                                                                                                                                                    ")
		  .append("    </div>                                                                                                                                                                                      ");
		return sb.toString();
	}	
}
