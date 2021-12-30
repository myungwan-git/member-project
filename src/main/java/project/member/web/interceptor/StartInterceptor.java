package project.member.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@Component
public class StartInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String uuid = UUID.randomUUID().toString();
    String requestURI = request.getRequestURI();

    log.info(" >>> StartInterceptor preHandle() UUID = {} , requestURI = {} ", uuid, requestURI);

    return true;
  }
}
