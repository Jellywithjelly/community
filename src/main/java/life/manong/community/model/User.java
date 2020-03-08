package life.manong.community.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

  private Integer id;  //用户id
  private String name; //用户名
  private String accountId;
  private String token; // 用户令牌
  private Long getCreate;
  private Long getModified;
  private String bio;
  private String avatarUrl; // 用户头像



}
