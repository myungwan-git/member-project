package project.member.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LoginFilter implements Filter {

  private final String uuid = UUID.randomUUID().toString();

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    String requestURI = httpServletRequest.getRequestURI();
    log.info(" >>> Filter doFilter UUID = {} , requestURI = {}", uuid, requestURI);

    try {
      HttpSession session = httpServletRequest.getSession(false);
      if (session == null) {
        log.info(" >>> 로그인 하지 않은 사용자의 접근 requestURI = {} ", requestURI);
        // 로그인한 사용자만 볼수있는 페이지에 접근시 로그인 화면으로 보내버림. 로그인이 완료하면 로그인 화면으로 보내진곳으로 다시 보내기 위해 redirectURI를 같이 보낸다.
        httpServletResponse.sendRedirect("/login?redirectURL="+requestURI);
        return;
      }
      chain.doFilter(request, response);
    } catch (Exception e) {
      throw e;
    } finally {
      log.info(" >>> Filter doFilter finally UUID = {} , requestURI = {} ", uuid, requestURI);
    }
  }
}
