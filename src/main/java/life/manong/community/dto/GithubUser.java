package life.manong.community.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
    public GithubUser(){

    }

    public GithubUser(String name, Long id, String bio, String avatar_url) {
        this.name = name;
        this.id = id;
        this.bio = bio;
        this.avatar_url = avatar_url;
    }
}
