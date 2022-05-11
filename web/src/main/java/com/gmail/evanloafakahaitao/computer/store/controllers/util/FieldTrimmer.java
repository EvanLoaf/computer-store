package com.gmail.evanloafakahaitao.computer.store.controllers.util;

import com.gmail.evanloafakahaitao.computer.store.services.dto.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class FieldTrimmer {

    private static final Logger logger = LogManager.getLogger(FieldTrimmer.class);

    public UserDTO trim(UserDTO user) {
        logger.info("Trimming User DTO");
        logger.debug("Trimming User DTO : {}", user);
        if (user.getFirstName() != null) {
            user.setFirstName(user.getFirstName().trim());
        }
        if (user.getLastName() != null) {
            user.setLastName(user.getLastName().trim());
        }
        if (user.getEmail() != null) {
            user.setEmail(user.getEmail().trim());
        }
        if (user.getPassword() != null) {
            user.setPassword(user.getPassword().trim());
        }
        if (user.getProfile() != null && user.getProfile().getAddress() != null) {
            user.getProfile().setAddress(user.getProfile().getAddress().trim());
        }
        if (user.getProfile() != null && user.getProfile().getPhoneNumber() != null) {
            user.getProfile().setPhoneNumber(user.getProfile().getPhoneNumber().trim());
        }
        return user;
    }

    public ItemDTO trim(ItemDTO item) {
        logger.info("Trimming Item DTO");
        logger.debug("Trimming Item DTO : {}", item);
        if (item.getVendorCode() != null) {
            item.setVendorCode(item.getVendorCode().trim());
        }
        if (item.getName() != null) {
            item.setName(item.getName().trim());
        }
        if (item.getDescription() != null) {
            item.setDescription(item.getDescription().trim());
        }
        return item;
    }

    public NewsDTO trim(NewsDTO news) {
        logger.info("Trimming News DTO");
        logger.debug("Trimming News DTO : {}", news);
        if (news.getTitle() != null) {
            news.setTitle(news.getTitle().trim());
        }
        if (news.getContent() != null) {
            news.setContent(news.getContent().trim());
        }
        return news;
    }

    public CommentDTO trim(CommentDTO comment) {
        logger.info("Trimming Comment DTO");
        logger.debug("Trimming Comment DTO : {}", comment);
        if (comment.getContent() != null) {
            comment.setContent(comment.getContent().trim());
        }
        return comment;
    }

    public FeedbackDTO trim(FeedbackDTO feedback) {
        logger.info("Trimming Feedback DTO");
        logger.debug("Trimming Feedback DTO : {}", feedback);
        if (feedback.getMessage() != null) {
            feedback.setMessage(feedback.getMessage().trim());
        }
        return feedback;
    }

    public BusinessCardDTO trim(BusinessCardDTO businessCard) {
        logger.info("Trimming Business Card DTO");
        logger.debug("Trimming Business Card DTO : {}", businessCard);
        if (businessCard.getTitle() != null) {
            businessCard.setTitle(businessCard.getTitle().trim());
        }
        if (businessCard.getName() != null) {
            businessCard.setName(businessCard.getName().trim());
        }
        if (businessCard.getPhoneNumber() != null) {
            businessCard.setPhoneNumber(businessCard.getPhoneNumber().trim());
        }
        return businessCard;
    }
}
