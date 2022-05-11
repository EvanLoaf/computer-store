package com.gmail.evanloafakahaitao.computer.store.controllers;

import com.gmail.evanloafakahaitao.computer.store.controllers.model.PaginationDetails;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.PageProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.properties.WebProperties;
import com.gmail.evanloafakahaitao.computer.store.controllers.util.FieldTrimmer;
import com.gmail.evanloafakahaitao.computer.store.controllers.util.PaginationUtil;
import com.gmail.evanloafakahaitao.computer.store.services.FeedbackService;
import com.gmail.evanloafakahaitao.computer.store.services.dto.FeedbackDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/feedback")
public class FeedbackController {

    private static final Logger logger = LogManager.getLogger(FeedbackController.class);

    private final FeedbackService feedbackService;
    private final Validator feedbackValidator;
    private final PageProperties pageProperties;
    private final PaginationUtil paginationUtil;
    private final FieldTrimmer fieldTrimmer;

    @Autowired
    public FeedbackController(
            FeedbackService feedbackService,
            @Qualifier("feedbackValidator") Validator feedbackValidator,
            PageProperties pageProperties,
            PaginationUtil paginationUtil,
            FieldTrimmer fieldTrimmer
    ) {
        this.feedbackService = feedbackService;
        this.feedbackValidator = feedbackValidator;
        this.pageProperties = pageProperties;
        this.paginationUtil = paginationUtil;
        this.fieldTrimmer = fieldTrimmer;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_feedback')")
    public String getFeedback(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        logger.debug("Executing Feedback Controller method GET : getFeedback, page : {}", page);
        logger.info("Viewing Feedback");
        List<FeedbackDTO> feedbackList = feedbackService.findAll(
                paginationUtil.getStartPosition(page),
                pageProperties.getPaginationMaxResults()
        );
        modelMap.addAttribute("feedback", feedbackList);
        PaginationDetails pagination = new PaginationDetails();
        pagination.setPage(page);
        pagination.setPageNumbers(
                paginationUtil.getPageNumbers(feedbackService.countAll().intValue())
        );
        pagination.setStartPosition(paginationUtil.getPageNumerationStart(page));
        modelMap.addAttribute("pagination", pagination);
        return pageProperties.getFeedbackPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_feedback')")
    public String createFeedback(
            @ModelAttribute("feedback") FeedbackDTO feedback,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing Feedback Controller method POST : createFeedback");
        logger.info("Creating Feedback");
        feedback = fieldTrimmer.trim(feedback);
        feedbackValidator.validate(feedback, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("feedback", feedback);
            return pageProperties.getFeedbackCreatePagePath();
        } else {
            feedbackService.save(feedback);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/users/profile" + "?feedback=true";
        }
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('create_feedback')")
    public String createFeedbackPage(ModelMap modelMap) {
        logger.debug("Executing Feedback Controller method GET : createFeedbackPage");
        logger.info("Entering Feedback creation page");
        modelMap.addAttribute("feedback", new FeedbackDTO());
        return pageProperties.getFeedbackCreatePagePath();
    }

    @PostMapping(value = "/delete")
    @PreAuthorize("hasAuthority('delete_feedback')")
    public String deleteFeedback(
            @RequestParam("ids") Long[] ids
    ) {
        logger.debug("Executing Feedback Controller method : deleteFeedback with id's : {}", Arrays.toString(ids));
        logger.info("Deleting feedback");
        for (Long id : ids) {
            FeedbackDTO feedbackDTO = new FeedbackDTO();
            feedbackDTO.setId(id);
            feedbackService.deleteById(feedbackDTO);
        }
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/feedback";
    }
}
