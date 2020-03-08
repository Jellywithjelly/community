package life.manong.community.dto;

import life.manong.community.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionDTO {

    private Long id; //问题id
    private String title; // 问题标题
    private String description; //问题描述
    private Long getCreate; //创建时间
    private Long getModified; //修改时间
    private Long creator; //创建人
    private int commentCount; //评论数
    private int viewCount; // 阅读数
    private int likeCount; //点赞数
    private String tag; //标签
    private User user;

}
