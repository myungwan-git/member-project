package project.member.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import project.member.domain.member.Member;
import project.member.domain.member.MemberRepository;
import project.member.web.session.SessionConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

  private final MemberRepository memberRepository;


  /**
   * Cookie를 구현한 Home Controller
   */
  /*
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
  */

  @GetMapping("/")
  public String home(@SessionAttribute(name = SessionConfig.LOGIN_SESSION_MEMBER, required = false) Member loginSessionMember,
                     HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
    log.info(" >>> 로그인 여부 - loginSessionMember = {} ", loginSessionMember);

    if (loginSessionMember == null) {
      return "home";
    }

    long sessionCreateTime = request.getSession(false).getCreationTime();
    log.info(" >>> Session이 생성된 시간 = {} ", sessionCreateTime);
    model.addAttribute("memberNickname", loginSessionMember.getNickname());

    return "home";
  }


}
