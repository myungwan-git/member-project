package project.member.domain.member;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Member {


  private Long idx;

  @NotEmpty
  private String id;

  @NotEmpty
  private String password;

  @NotEmpty
  private String nickname;

  @NotNull
  @Range(min = 1, max = 90)
  private Integer age;
}
