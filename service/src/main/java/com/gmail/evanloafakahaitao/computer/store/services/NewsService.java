package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.services.dto.NewsDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleNewsDTO;

import java.util.List;

public interface NewsService {

    NewsDTO save(NewsDTO newsDTO);

    List<SimpleNewsDTO> findAll();

    NewsDTO findById(SimpleNewsDTO simpleNewsDTO);

    NewsDTO update(NewsDTO newsDTO);

    void deleteById(SimpleNewsDTO simpleNewsDTO);
}
