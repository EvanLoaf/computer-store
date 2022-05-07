package com.gmail.evanloafakahaitao.computer.store.services.converters.entity;

import com.gmail.evanloafakahaitao.computer.store.dao.model.News;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleNewsDTO;
import org.springframework.stereotype.Component;

@Component("simpleNewsEntityConverter")
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
