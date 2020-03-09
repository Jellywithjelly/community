package life.manong.community.controller;

import life.manong.community.dto.PaginationDTO;
import life.manong.community.model.User;
import life.manong.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("profile/{action}")
    public String profile(@PathVariable("action")String action, Model model, HttpServletRequest request,
                          @RequestParam(value = "page",defaultValue = "1") Integer page,
                          @RequestParam(value = "size",defaultValue = "5") Integer size){
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        User user = (User)request.getSession().getAttribute("user");
        /*获取问题列表*/
        PaginationDTO paginationDTO = questionService.listByUserId(user.getId(),page,size);
        model.addAttribute("paginationDTO",paginationDTO);
        return  "profile";
    }





}
