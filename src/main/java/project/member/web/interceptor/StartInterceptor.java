package project.member.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

@Slf4j
public class StartInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String uuid = UUID.randomUUID().toString();
    String requestURI = request.getRequestURI();

    if (handler instanceof ResourceHttpRequestHandler) {
      ResourceHttpRequestHandler resourceHttpRequestHandler = (ResourceHttpRequestHandler) handler;
      log.debug(" preHandle() 정적리소스 요청  ResourceHttpRequestHandler  = {} ", resourceHttpRequestHandler);
    }

    log.debug(" >>> preHandle() 호출될 Handler info = {} ", handler);
    log.info(" >>> preHandle() UUID = {} , requestURI = {} ", uuid, requestURI);

    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    String uuid = UUID.randomUUID().toString();
    String requestURI = request.getRequestURI();

    log.info(" >>> postHandle() UUID = {} , requestURI = {} ", uuid, requestURI);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    String uuid = UUID.randomUUID().toString();
    String requestURI = request.getRequestURI();

    if (ex != null) {
      log.error(" >>> afterCompletion() ERROR = {}, UUID = {} , requestURI = {} ", ex, uuid, requestURI);
    } else {
      log.info(" >>> afterCompletion() UUID = {} , requestURI = {} ", uuid, requestURI);
    }

  }
}
