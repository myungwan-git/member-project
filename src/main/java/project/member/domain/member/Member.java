package project.member.domain.member;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class Member {


  private Long idx;

  @NotEmpty
  @Pattern(regexp="^[A-Za-z]*$")
  private String id;

  @NotEmpty
  private String password;

  @NotEmpty
  private String nickname;

  @NotNull
  @Range(min = 1, max = 90)
  private Integer age;

  @NotNull
  @Range(min = 1, max = 1000)
  private Integer value;

}
