package project.member.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class StartFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

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
      log.info(" >>> Filter doFilter finally UUID = {} ", uuid);
    }
  }

  @Override
  public void destroy() {

  }
}
