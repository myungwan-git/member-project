package project.member.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import project.member.domain.member.Member;
import project.member.domain.member.MemberRepository;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

  private final MemberRepository memberRepository;

  @GetMapping("/")
  public String home(@CookieValue(name = "memberIdx", required = false) Long memberIdx, Model model) {

    log.info(" >>> 로그인 여부 - memberIdx = {} ", memberIdx);
    if (memberIdx == null) {
      return "home";
    }

    Member getMemberByIdx = memberRepository.findByIdx(memberIdx);

    // 쿠키의 memberIdx 를 가져왔는데 여기ㅏ서 실제 member가 존재하는지 체크하는 이유는
    // 쿠키는 브라우저에서 임의로 값을 바꿀 수 있다. 그러니까 지금은 연습삼아 체크해 주는 중.
    if (getMemberByIdx == null) {
      return "home";
    }

    model.addAttribute("memberNickname", getMemberByIdx.getNickname());

    return "home";
  }
}
