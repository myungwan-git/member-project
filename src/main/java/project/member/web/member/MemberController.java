package project.member.web.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.member.domain.member.Member;

@Slf4j
@Controller
public class MemberController {

  @GetMapping("/member/add")
  public String memberAddView(@ModelAttribute Member member) {
    log.info(" >>> 회원가입 폼 진입");
    return "/member/memberAdd";
  }

  @PostMapping("/member/add")
  public String memberAdd(@Validated @ModelAttribute Member member, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      log.error(" >>> BindingResult ERROR = {}", bindingResult);

      return "/member/memberAdd";
    }
    return "home";
  }
}
