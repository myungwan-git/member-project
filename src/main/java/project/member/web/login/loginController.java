package project.member.web.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.member.domain.login.Login;
import project.member.domain.member.MemberRepository;

@Slf4j
@Controller
@RequiredArgsConstructor
public class loginController {

  private final MemberRepository memberRepository;

  @GetMapping("/login")
  public String login(@ModelAttribute Login login) {

    return "login/login";
  }

  @PostMapping("/login")
  public String loginValidation(@ModelAttribute Login login, BindingResult bindingResult) {
    log.info(" >>> login Validation 실행 !!");

    if (bindingResult.hasErrors()) {
      log.error(" >>> login BindingResult ERROR = {}", bindingResult);

      return "login/login";
    }

    return "/"
  }
}
