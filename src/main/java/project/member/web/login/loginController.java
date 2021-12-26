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
import project.member.web.cookie.CookieConfig;
import project.member.web.session.SessionConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class loginController {

  private final LoginService loginService;
//  private final CookieConfig cookieConfig;

  @GetMapping("/login")
  public String login(@ModelAttribute Login login) {

    return "login/login";
  }

  @PostMapping("/login")
  public String loginValidation(@Validated @ModelAttribute Login login, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
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

//    cookieConfig.cookieCreate(member, response);
    HttpSession session = request.getSession();
    session.setAttribute(SessionConfig.LOGIN_SESSION_MEMBER, member);


    log.info(" >>> 로그인 성공 !! ID = {}", login.getId());
    return "redirect:/";
  }

  @PostMapping("/logout")
  public String logout(HttpServletRequest request) {

    HttpSession session = request.getSession(false);
    log.info(" >>> logout 클릭 !! session = {} ", session);
    if (session != null) {
      session.invalidate();
    }

    return "redirect:/";
  }
}
