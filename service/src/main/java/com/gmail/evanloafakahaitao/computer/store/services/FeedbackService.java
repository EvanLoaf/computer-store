package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.services.dto.FeedbackDTO;

import java.util.List;

public interface FeedbackService {

    FeedbackDTO save(FeedbackDTO feedbackDTO);

    List<FeedbackDTO> findAll(Integer firstResult, Integer maxResults);

    void deleteById(FeedbackDTO feedbackDTO);

    Long countAll();
}
