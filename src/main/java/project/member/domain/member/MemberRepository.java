package project.member.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class MemberRepository {

  private Map<Long, Member> store = new ConcurrentHashMap<>();
  private long seq = 0L;

  public Member save(Member member) {
    log.info(" >>> Member save() Execute !!");

    member.setIdx(++seq);
    store.put(member.getIdx(), member);

    return member;
  }

  public Member findByIdx(Long idx) {
    log.info(" >>> Member findByIdx() Execute !!");

    Member member = store.get(idx);
    return member;
  }

  public List<Member> findAll() {
    log.info(" >>> Member findAll() Execute !! ");
    return new ArrayList<>(store.values());
  }

}
