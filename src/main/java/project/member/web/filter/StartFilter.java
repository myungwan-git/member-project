package project.member.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class StartFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    String uuid = UUID.randomUUID().toString();
    log.info(" >>> Filter init() UUID = {} ", uuid);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    String uuid = UUID.randomUUID().toString();

    log.info(" >>> Filter doFilter UUID = {}", uuid);
    try {
      chain.doFilter(request, response);
    } catch (IOException e) {
      throw e;
    } finally {
      //지저분 하지만 일단 로그작성.
      log.info(" >>> Filter doFilter finally UUID = {} ", uuid);
    }
  }

  @Override
  public void destroy() {
    String uuid = UUID.randomUUID().toString();
    log.info(" >>> Filter destroy() UUID = {} ", uuid);
  }
}
