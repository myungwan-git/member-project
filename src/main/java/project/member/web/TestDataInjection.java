package project.member.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import project.member.domain.member.Member;
import project.member.domain.member.MemberRepository;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestDataInjection {

  private final MemberRepository memberRepository;

  @PostConstruct
  public void init() {
    Member member1 = new Member(1L,"testa","test1","im testA",30,500);
    Member member2 = new Member(2L,"testb","test2","im testB",30,500);
    memberRepository.save(member1);
    memberRepository.save(member2);
  }
}
