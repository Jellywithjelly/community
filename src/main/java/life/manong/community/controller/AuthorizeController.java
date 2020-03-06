package life.manong.community.controller;

import life.manong.community.Provider.GithubProvider;
import life.manong.community.dto.AccessTokenDTO;
import life.manong.community.dto.GithubUser;
import life.manong.community.mapper.UserMapper;
import life.manong.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.redirect_uri}")
    private String redirect_uri;

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request, HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser!=null){
            /*登陆成功*/
            User user =  User.builder().name(githubUser.getName()).accountId(String.valueOf(githubUser.getId())).
                    token(UUID.randomUUID().toString()).getCreate(System.currentTimeMillis()).
                    getModified(System.currentTimeMillis()).bio(githubUser.getBio()).build();
            userMapper.insert(user);
            response.addCookie(new Cookie("token",user.getToken()));
            return "redirect:/";
        }else{
            /*登陆失败*/
            return "redirect:/";
        }

    }


}
