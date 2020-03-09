package life.manong.community.controller;

import life.manong.community.mapper.QuestionMapper;
import life.manong.community.mapper.UserMapper;
import life.manong.community.model.Question;
import life.manong.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            HttpServletRequest request, Model model) {
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title==null||"".equals(title)){
            model.addAttribute("error","问题标题不能为空!");
            return "publish";
        }
        if(description==null||"".equals(description)){
            model.addAttribute("error","问题补充不能为空!");
            return "publish";
        }
        if(tag==null||"".equals(tag)){
            model.addAttribute("error","问题标签不能为空!");
            return "publish";
        }


        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录!");
            return "publish";
        }
        Question question =  Question.builder().title(title).description(description).tag(tag).creator(user.getId()).
                getCreate(System.currentTimeMillis()).getModified(System.currentTimeMillis()).build();
        questionMapper.insert(question);
        return "redirect:/";
    }

}
