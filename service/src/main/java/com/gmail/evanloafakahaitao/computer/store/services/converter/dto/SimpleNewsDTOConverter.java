package com.gmail.evanloafakahaitao.computer.store.services.converter.dto;

import com.gmail.evanloafakahaitao.computer.store.dao.model.News;
import com.gmail.evanloafakahaitao.computer.store.services.converter.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleNewsDTO;

public class SimpleNewsDTOConverter implements DTOConverter<SimpleNewsDTO, News> {

    @Override
    public SimpleNewsDTO toDto(News entity) {
        SimpleNewsDTO news = new SimpleNewsDTO();
        news.setId(entity.getId());
        news.setTitle(entity.getTitle());
        news.setCreated(entity.getCreated());
        return news;
    }
}
