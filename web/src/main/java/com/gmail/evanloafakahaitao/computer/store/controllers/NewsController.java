package com.gmail.evanloafakahaitao.computer.store.controllers;

import com.gmail.evanloafakahaitao.computer.store.controllers.model.PaginationDetails;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.PageProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.WebProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.util.FieldTrimmer;
import com.gmail.evanloafakahaitao.computer.store.controllers.util.PaginationUtil;
import com.gmail.evanloafakahaitao.computer.store.services.CommentService;
import com.gmail.evanloafakahaitao.computer.store.services.NewsService;
import com.gmail.evanloafakahaitao.computer.store.services.dto.CommentDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.NewsDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleNewsDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Component
@RequestMapping(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/news")
public class NewsController {

    private static final Logger logger = LogManager.getLogger(NewsController.class);

    private final PageProperties pageProperties;
    private final NewsService newsService;
    private final CommentService commentService;
    private final Validator newsValidator;
    private final Validator commentValidator;
    private final PaginationUtil paginationUtil;
    private final FieldTrimmer fieldTrimmer;

    @Autowired
    public NewsController(
            PageProperties pageProperties,
            NewsService newsService,
            CommentService commentService,
            @Qualifier("newsValidator") Validator newsValidator,
            @Qualifier("commentValidator") Validator commentValidator,
            PaginationUtil paginationUtil,
            FieldTrimmer fieldTrimmer
    ) {
        this.pageProperties = pageProperties;
        this.newsService = newsService;
        this.commentService = commentService;
        this.newsValidator = newsValidator;
        this.commentValidator = commentValidator;
        this.paginationUtil = paginationUtil;
        this.fieldTrimmer = fieldTrimmer;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_news')")
    public String getNews(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        logger.debug("Executing News Controller method GET : getNews, page : {}", page);
        logger.info("Viewing all News");
        List<SimpleNewsDTO> news = newsService.findAll(
                paginationUtil.getStartPosition(page),
                pageProperties.getPaginationMaxResults()
        );
        modelMap.addAttribute("news", news);
        PaginationDetails pagination = new PaginationDetails();
        pagination.setPage(page);
        pagination.setPageNumbers(
                paginationUtil.getPageNumbers(newsService.countAll().intValue())
        );
        pagination.setStartPosition(paginationUtil.getPageNumerationStart(page));
        modelMap.addAttribute("pagination", pagination);
        return pageProperties.getNewsPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_news')")
    public String createNews(
            @ModelAttribute("news") NewsDTO news,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing News Controller method POST : createNews");
        logger.info("Creating News");
        news = fieldTrimmer.trim(news);
        newsValidator.validate(news, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("news", news);
            return pageProperties.getNewsCreatePagePath();
        } else {
            newsService.save(news);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/news";
        }
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('create_news')")
    public String createNewsPage(
            ModelMap modelMap
    ) {
        logger.debug("Executing News Controller method GET : createNewsPage");
        logger.info("Entering News creation page");
        modelMap.addAttribute("news", new NewsDTO());
        return pageProperties.getNewsCreatePagePath();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('view_news')")
    public String getNewsPiece(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        logger.debug("Executing News Controller method GET : getNewsPiece with id : {}", id);
        logger.info("Viewing News piece");
        SimpleNewsDTO newsDTO = new SimpleNewsDTO();
        newsDTO.setId(id);
        NewsDTO news = newsService.findById(newsDTO);
        modelMap.addAttribute("news", news);
        modelMap.addAttribute("comment", new CommentDTO());
        return pageProperties.getNewsShowOnePagePath();
    }

    @PostMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('update_news')")
    public String updateNews(
            @PathVariable("id") Long id,
            @ModelAttribute("user") NewsDTO news,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing News Controller method POST : updateNews with id : {}", id);
        logger.info("Updating News");
        news = fieldTrimmer.trim(news);
        news.setId(id);
        newsValidator.validate(news, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("news", news);
            return pageProperties.getNewsShowOnePagePath();
        } else {
            newsService.update(news);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/news/" + id;
        }
    }

    @GetMapping(value = "/{id}/delete")
    @PreAuthorize("hasAuthority('delete_news')")
    public String deleteNewsPiece(
            @PathVariable("id") Long id
    ) {
        logger.debug("Executing News Controller method GET : deleteNewsPiece with id : {}", id);
        logger.info("Deleting News piece");
        SimpleNewsDTO newsDTO = new SimpleNewsDTO();
        newsDTO.setId(id);
        newsService.deleteById(newsDTO);
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/news";
    }

    @PostMapping(value = "/delete")
    @PreAuthorize("hasAuthority('delete_news')")
    public String deleteNews(
            @RequestParam("ids") Long[] ids
    ) {
        logger.debug("Executing News Controller method : deleteNews with id's : {}", Arrays.toString(ids));
        logger.info("Deleting News");
        for (Long id : ids) {
            SimpleNewsDTO newsDTO = new SimpleNewsDTO();
            newsDTO.setId(id);
            newsService.deleteById(newsDTO);
        }
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/news";
    }

    @PostMapping(value = "/{id}/comments")
    @PreAuthorize("hasAuthority('create_comment')")
    public String createComment(
            @PathVariable("id") Long id,
            @ModelAttribute("comment") CommentDTO comment,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing News Controller method : createComment : {}", comment.getContent());
        logger.info("Creating Comment");
        comment = fieldTrimmer.trim(comment);
        commentValidator.validate(comment, result);
        if (result.hasErrors()) {
            modelMap.addAttribute(comment);
            SimpleNewsDTO newsDTO = new SimpleNewsDTO();
            newsDTO.setId(id);
            NewsDTO news = newsService.findById(newsDTO);
            modelMap.addAttribute("news", news);
            modelMap.addAttribute("comment", comment);
            return pageProperties.getNewsShowOnePagePath();
        } else {
            NewsDTO news = new NewsDTO();
            news.setId(id);
            news.getComments().add(comment);
            logger.info(comment);
            commentService.save(news);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/news/" + id;
        }
    }

    @GetMapping(value = "/{newsId}/comments/{commentId}/delete")
    @PreAuthorize("hasAuthority('delete_comments')")
    public String deleteComment(
            @PathVariable("newsId") Long newsId,
            @PathVariable("commentId") Long commentId
    ) {
        logger.debug("Executing News Controller method : deleteComment with id : {}", commentId);
        logger.info("Deleting Comment");
        NewsDTO newsDTO = new NewsDTO();
        CommentDTO commentDTO = new CommentDTO();
        newsDTO.setId(newsId);
        commentDTO.setId(commentId);
        newsDTO.getComments().add(commentDTO);
        commentService.deleteById(newsDTO);
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/news/" + newsId;
    }
}
