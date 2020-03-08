package life.manong.community.mapper;

import life.manong.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuestionMapper {
    @Insert("insert into question(title,description,get_create,get_modified,creator,comment_count,view_count,like_count,tag) values(#{title}," +
            "#{description},#{getCreate},#{getModified},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag})")
     void insert(Question question);


    @Select("select id,title,description,get_create,get_modified,creator,comment_count,view_count,like_count,tag from question  limit #{offset} , #{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();


}
