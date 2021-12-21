package project.member.domain.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import project.member.domain.member.Member;
import project.member.domain.member.MemberRepository;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

  private final MemberRepository memberRepository;

  @Override
  public Member login(String id, String password) {
    //findByLoginId에서 ID를 던져 있으면 Member를 반환받고 없으면 null을 반환한다.
    return memberRepository.findByLoginId(id).filter(member -> member.getPassword().equals(password)).orElse(null);
  }

}
