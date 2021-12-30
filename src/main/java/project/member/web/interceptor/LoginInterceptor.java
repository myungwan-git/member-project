package project.member.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

  private final String uuid = UUID.randomUUID().toString();

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String requestURI = request.getRequestURI();
    log.info(" >>> preHandle() UUID = {} , requestURI = {} ", uuid, requestURI);

    HttpSession session = request.getSession(false);
    if (session == null) {
      log.info(" >>> preHandle() Have not session UUID = {} , requestURI = {} ", uuid, requestURI);
      response.sendRedirect("/login?redirectURL="+requestURI);
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    String requestURI = request.getRequestURI();
    log.info(" >>> postHandle() UUID = {} , requestURI = {} ", uuid, requestURI);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    String requestURI = request.getRequestURI();

    if (ex != null) {
      log.error("afterCompletion ERROR = {} , UUID = {} , requestURI = {} ", ex, uuid, requestURI);
    } else {
      log.info(" >>> afterCompletion() UUID = {} , requestURI = {} ", uuid, requestURI);
    }
  }
}
