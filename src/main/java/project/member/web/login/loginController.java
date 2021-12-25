package project.member.web.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.member.domain.login.Login;
import project.member.domain.login.LoginService;
import project.member.domain.member.Member;
import project.member.domain.member.MemberRepository;
import project.member.web.cookie.CookieConfig;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequiredArgsConstructor
public class loginController {

  private final LoginService loginService;
  private final CookieConfig cookieConfig;

  @GetMapping("/login")
  public String login(@ModelAttribute Login login) {

    return "login/login";
  }

  @PostMapping("/login")
  public String loginValidation(@Validated @ModelAttribute Login login, BindingResult bindingResult, HttpServletResponse response) {
    log.info(" >>> login Validation 실행 !!");

    if (bindingResult.hasErrors()) {
      log.error(" >>> login BindingResult ERROR = {}", bindingResult);
      return "login/login";
    }

    Member member = loginService.login(login.getId(), login.getPassword());
    if (member == null) {
      log.error(" >>> login ID or Password ERROR ID = {}", login.getId());
      bindingResult.reject("loginGlobalError", null);
      return "login/login";
    }

//    Cookie cookie = new Cookie("memberIdx", String.valueOf(member.getIdx()));
//    response.addCookie(cookie);
    cookieConfig.cookieCreate(member, response);


    log.info(" >>> 로그인 성공 !! ID = {}", login.getId());
    return "redirect:/";
  }

  @PostMapping("/logout")
  public String logout(@CookieValue(name = "memberIdx", required = false) Long memberIdx, HttpServletResponse response) {
    log.info(" >>> logout 클릭 !! Cookie(memberIdx) = {} ", memberIdx);

    if (memberIdx == null) {
      log.info(" >>> logout 버튼이 노출되었는데 Cookie값이 없다. 브라우저에서 임의로 바꾼건가? 확인 필요 !!");
      return "home";
    }

    cookieConfig.cookieDelete("memberIdx", response);
    return "redirect:/";
  }
}
