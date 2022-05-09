package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.services.dto.NewsDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleNewsDTO;

import java.util.List;

public interface NewsService {

    NewsDTO save(NewsDTO newsDTO);

    List<SimpleNewsDTO> findAll(Integer firstResult, Integer maxResults);

    NewsDTO findById(SimpleNewsDTO simpleNewsDTO);

    NewsDTO update(NewsDTO newsDTO);

    void deleteById(SimpleNewsDTO simpleNewsDTO);

    Long countAll();
}
