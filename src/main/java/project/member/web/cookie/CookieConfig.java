package project.member.web.cookie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import project.member.domain.member.Member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@Component
public class CookieConfig {

  public void cookieCreate(Member member, HttpServletResponse response) {
    Long idx = member.getIdx();
//    String uuid = UUID.randomUUID().toString();
    Cookie cookie = new Cookie("memberIdx", String.valueOf(idx));
    response.addCookie(cookie);

    log.info(" >>> Cookie 생성 성공 !! Cookie name = {} ", cookie.getName());
  }

  public void cookieDelete(String memberIdx, HttpServletResponse response) {
    Cookie cookie = new Cookie(memberIdx, null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);

    log.info(" >>> Cookie 삭제 성공 !! Cookie name = {} ", cookie.getName());
  }


}
