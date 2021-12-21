package project.member.domain.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Login {

  @NotEmpty
  private String id;

  @NotEmpty
  private String password;
}
