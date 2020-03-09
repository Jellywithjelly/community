package life.manong.community.service;



import life.manong.community.dto.PaginationDTO;
import life.manong.community.dto.QuestionDTO;
import life.manong.community.mapper.QuestionMapper;
import life.manong.community.mapper.UserMapper;
import life.manong.community.model.Question;
import life.manong.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;


    public PaginationDTO list(Integer page, Integer size){
        PaginationDTO paginationDTO  = PaginationDTO.builder().build();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        Integer offset = size * (page-1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = QuestionDTO.builder().build();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);
        return  paginationDTO;
    }

    public PaginationDTO listByUserId(Integer id, Integer page, Integer size) {
        PaginationDTO paginationDTO  = PaginationDTO.builder().build();
        Integer totalCount = questionMapper.countByUserId(id);
        paginationDTO.setPagination(totalCount,page,size);
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }
        Integer offset = size * (page-1);
        List<Question> questions = questionMapper.listByUserId(id,offset,size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = QuestionDTO.builder().build();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOS);
        return  paginationDTO;
    }
}
