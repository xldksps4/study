package com.update.semi.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFilter implements Filter {
   private Logger logger = LoggerFactory.getLogger(LogFilter.class);

   @Override // 필터가 생성될때 설정
   public void init(FilterConfig filterConfig) throws ServletException {

   }

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
         throws IOException, ServletException { // 필터체인 필터 밖과 안을 연결해주는 객체

      HttpServletRequest req = (HttpServletRequest) request; // 형변환

      String url = req.getRequestURL().toString();    // url 정보
      String queryString = req.getQueryString();      // url에 사용된 쿼리스트링 값 
      String sessionID = req.getRequestedSessionId(); // 현재 세션에 사용된 id
      String referer = req.getHeader("referer");       // 이전 url 정보
      String agent = req.getHeader("User-Agent");     // 헤더정보    ex) Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36
 

      StringBuffer sb = new StringBuffer();
      sb.append("url " + url + "\n")
        .append("queryString " + queryString + "\n")
        .append("content-type:" + req.getContentType() + "\n")
        .append("==================== [Log Filter] END ===================" + "\n");
       
      logger.info("\n=================== [Log Filter] START ==================\n" + sb);
      chain.doFilter(req, response);
      
      /*
         # RemoteAddr >> ip주소를 리턴한다.
          
         # URL = 주소값
         # URN = 식별할 수 있는 번호
         # URI = URL + URN 
            URI는 주소 + URN는 식별할 수 있는 번호(해당 주소에서 유일값)
            docid=111이라는 쿼리스트링의 값에 따라 결과가 달라지게됨, 따라서 식별자 역할을 하고 있음
            
            예시) http://test.com/test.pdf?docid=111 ,http://test.com/test.pdf?docid=112는 같은 URL을 가지고 다른 URI를 가짐
            예시 ) http://test.com/test.pdf?docid=111은 주소는 URI이지만 URL은 아니다
         
         # queryString 식별번호(URN)
         # referer 호출한 이전페이지(주소)
         # agent 브라우저 및 사용자정보

       */

   }

   @Override // 해당 필터가 메모리에서 삭제될때
   public void destroy() {

   }

}