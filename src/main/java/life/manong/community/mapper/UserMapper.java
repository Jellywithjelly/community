package life.manong.community.mapper;

import life.manong.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,get_create,get_modified) values (#{name},#{accountId},#{token},#{getCreate},#{getModified})")
      void insert(User user);

}
