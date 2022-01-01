package project.member.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import project.member.domain.member.Member;
import project.member.domain.member.MemberRepository;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

  //@RequiredArgsConstructor를 통한 생성자 생성 ( 생성자가 1개라 자동으로 @AutoWired )
  private final MemberRepository memberRepository;

  @GetMapping("/runtime")
  public void runtimeException() {
    throw new RuntimeException("runtimeException !!");
  }
  @GetMapping("/response")
  public void response() {
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/add")
  public String memberAddView(@ModelAttribute Member member) {
    log.info(" >>> 회원가입 폼 진입");
    return "/member/memberAdd";
  }

  @PostMapping("/add")
  public String memberAdd(@Validated @ModelAttribute Member member, BindingResult bindingResult) {

    if ( (member.getAge() != null) && (member.getValue() != null) && (member.getAge() * member.getValue() < 3000) ) {
      bindingResult.reject("globalValueError", new Object[]{member.getAge()*member.getValue(),3000},null);
    }
    if (bindingResult.hasErrors()) {
      log.error(" >>> BindingResult ERROR = {}", bindingResult);
      return "/member/memberAdd";
    }

    //정상 로직 ( Validation 종류 후 실행되는 정상 로직 )
    memberRepository.save(member);

    return "redirect:/";
  }

  @GetMapping("/list")
  public String memberList(Model model) {
    log.info(" >>> member List 출력 - memberList()");

    List<Member> memberFindAll = memberRepository.findAll();
    model.addAttribute("members", memberFindAll);

    return "/member/memberList";
  }

}
