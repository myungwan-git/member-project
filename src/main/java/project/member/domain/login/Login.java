package project.member.domain.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class Login {

  @NotEmpty
  @Pattern(regexp="^[A-Za-z]*$")
  private String id;

  @NotEmpty
  private String password;
}
