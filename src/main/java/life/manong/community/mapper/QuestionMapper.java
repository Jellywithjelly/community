package life.manong.community.mapper;

import life.manong.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface QuestionMapper {
    @Insert("insert into question(title,description,get_create,get_modified,creator,comment_count,view_count,like_count,tag) values(#{title}," +
            "#{description},#{getCreate},#{getModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
     void insert(Question question);


}
