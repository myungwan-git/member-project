package project.member.domain.login;

import project.member.domain.member.Member;

public interface LoginService {
  Member login(String id, String password);
}
