package filters;

import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterPreReqTran implements Filter{
	private FilterConfig config;
	private Pattern patternBlockKeyword;
	
	/**
	 * Constructor (default)
	 */
	public FilterPreReqTran() {
		super();
		this.patternBlockKeyword = null;
	}
	
	/**
	 * init : 필터 객체가 생성되고, 준비 작업을 위해 딱 한번 호출되는 메서드 (필터의 초기 설정 정보를 얻어와 인스턴스 변수에 저장할 수 있음)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		
		// 필터의 초기 설정 정보에서, 금칙어 관련 정보가 있는 경우, 금칙어 패턴 설정		
		if( config.getInitParameter("blockKeywords") != null 
		&& !(config.getInitParameter("blockKeywords").toString().isEmpty()) )
			setPatternBlockKeyword(config.getInitParameter("blockKeywords").toString());
	}

	/**
	 * doFilter : 필터에 매핑된 URL에 대한 요청 발생 시마다, 호출되는 메서드
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain nextFilter)
			throws IOException, ServletException {
		
		/* 서블릿이 실행되기 전에 해야 할 작업 */

		// 1. Request에 대한 인코딩 설정
		req.setCharacterEncoding(config.getInitParameter("encoding"));
		
		// 2. get 요청인 경우, 요청 Parameter에서 특정 금지 키워드가 있는 지 확인
		HttpServletRequest httpReq = (HttpServletRequest) req;
		String methodNm = httpReq.getMethod();
		if(methodNm.equalsIgnoreCase("GET"))
		{
			// 요청 Parameter에서 특정 금지 키워드가 있는 지 확인하여, 금지 키워드가 발견된 경우, 에러페이지 출력
			String detectedBlockKeyword = checkBlockKeyword(httpReq);
			if(!detectedBlockKeyword.isEmpty())
			{
				System.out.println("Block Keyword was found!. -> " + detectedBlockKeyword);
				ServletContext sc = req.getServletContext();
				sc.getRequestDispatcher("/html/forbiddenAccess.html").forward(req, resp);
//				HttpServletResponse httpResp = (HttpServletResponse)resp;
//				httpResp.sendRedirect(httpReq.getContextPath() + "/html/forbiddenAccess.html");
				// 새로운 요청을 요구한 상태이므로, 이후 로직은 타면 안됨
				return;
			}
		}
					
		/* 다음 필터를 호출, 더 이상 필터가 없다면 서블릿의 service가 호출됨 */
		nextFilter.doFilter(req, resp);
		
		/* 서블릿이 실행한 후, 클라이언트에게 응답하기 전에 해야 할 작업 */
	}	
	
	/**
	 * 금칙어에 대한 패턴 조립 및 세팅
	 * @param blockKeywords : 금칙어들 (ex> update, delete 등..)
	 */
	private void setPatternBlockKeyword(String blockKeywords)
	{
		// 정규식 조립
		String regex = String.format("\\b(%s)\\b", blockKeywords);
		
		// 패턴 생성 및 세팅 (대소문자 무시 [Pattern.CASE_INSENSITIVE])
		this.patternBlockKeyword = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	}
	
	/**
	 * http 요청의 파라미터 값(value)에, 사용되면 안되는 금칙어가 포함되있는지 검사 
	 * @param httpReq : 검사할 Request
	 * @return detectedBlockKeyword: 요청 파라미터에서 발견된 금칙어 
	 */
	private String checkBlockKeyword(HttpServletRequest httpReq)
	{
		String detectedBlockKeyword = "";
		
		// 검사해야할 패턴이 있는 경우, 검사 수행
		if(patternBlockKeyword != null)
		{
			Enumeration<String> paramNames = httpReq.getParameterNames();
			while(paramNames.hasMoreElements())
			{
				String name	= paramNames.nextElement().toString();
				String value = httpReq.getParameter(name);
				Matcher matcher = patternBlockKeyword.matcher(value);
				while(matcher.find())
				{
					detectedBlockKeyword = matcher.group();
					return detectedBlockKeyword;
				}
			}
		}

		return detectedBlockKeyword;		
	}

}
