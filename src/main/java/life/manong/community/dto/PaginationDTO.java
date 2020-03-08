package life.manong.community.dto;


import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class PaginationDTO {
    private List<QuestionDTO> questionDTOS;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages;
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        pages = new ArrayList<>();
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        this.page = page;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            /*在当前页向前显示三个页码*/
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            /*在当前页向后显示三个页码*/
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        /*判断是否展示上一页*/
        if (page != 1) {
            showPrevious = true;
        } else {
            showFirstPage = false;
        }
        /*判断是否展示下一页*/
        if (page != totalPage) {
            showNext = true;
        } else {
            showNext = false;
        }
        /*判断是否展示第一页*/
        if (!pages.contains(1)) {
            showFirstPage = true;
        } else {
            showFirstPage = false;
        }
        /*判断是否展示最后一页*/
        if (!pages.contains(totalPage)) {
            showEndPage = true;
        } else {
            showEndPage = false;
        }


    }


}
