package com.gmail.evanloafakahaitao.computer.store.services.converter.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.News;
import com.gmail.evanloafakahaitao.computer.store.services.converter.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleNewsDTO;

public class SimpleNewsEntityConverter implements EntityConverter<SimpleNewsDTO, News> {

    @Override
    public News toEntity(SimpleNewsDTO dto) {
        News news = new News();
        if (dto.getId() != null) {
            news.setId(dto.getId());
        }
        if (dto.getTitle() != null) {
            news.setTitle(dto.getTitle());
        }
        return news;
    }
}
