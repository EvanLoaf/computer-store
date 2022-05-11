package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.NewsDao;
import com.gmail.evanloafakahaitao.computer.store.dao.UserDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.News;
import com.gmail.evanloafakahaitao.computer.store.dao.model.User;
import com.gmail.evanloafakahaitao.computer.store.services.NewsService;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.NewsDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.SimpleNewsDTO;
import com.gmail.evanloafakahaitao.computer.store.services.exceptions.NewsNotFoundException;
import com.gmail.evanloafakahaitao.computer.store.services.util.CurrentUserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);

    private final NewsDao newsDao;
    private final UserDao userDao;
    private final EntityConverter<NewsDTO, News> newsEntityConverter;
    private final DTOConverter<NewsDTO, News> newsDTOConverter;
    private final DTOConverter<SimpleNewsDTO, News> simpleNewsDTOConverter;

    @Autowired
    public NewsServiceImpl(
            NewsDao newsDao,
            UserDao userDao,
            @Qualifier("newsEntityConverter") EntityConverter<NewsDTO, News> newsEntityConverter,
            @Qualifier("newsDTOConverter") DTOConverter<NewsDTO, News> newsDTOConverter,
            @Qualifier("simpleNewsDTOConverter") DTOConverter<SimpleNewsDTO, News> simpleNewsDTOConverter
    ) {
        this.newsDao = newsDao;
        this.userDao = userDao;
        this.newsEntityConverter = newsEntityConverter;
        this.newsDTOConverter = newsDTOConverter;
        this.simpleNewsDTOConverter = simpleNewsDTOConverter;
    }

    @Override
    public NewsDTO save(NewsDTO newsDTO) {
        logger.info("Saving News");
        logger.debug("Saving News : {}", newsDTO.getTitle());
        User user = userDao.findOne(CurrentUserUtil.getCurrentId());
        News news = newsEntityConverter.toEntity(newsDTO);
        if (news.getCreated() == null) {
            news.setCreated(LocalDateTime.now());
        }
        news.setDeleted(false);
        news.setUser(user);
        newsDao.create(news);
        return newsDTOConverter.toDto(news);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimpleNewsDTO> findAll(Integer firstResult, Integer maxResults) {
        logger.info("Retrieving News");
        List<News> newsList = newsDao.findAll(firstResult, maxResults);
        logger.debug("Retrieved News : {}", newsList);
        if (!newsList.isEmpty()) {
            return simpleNewsDTOConverter.toDTOList(newsList);
        } else return Collections.emptyList();
    }

    @Override
    public NewsDTO findById(SimpleNewsDTO simpleNewsDTO) {
        logger.info("Retrieving News by Id");
        logger.debug("Retrieving News by Id : {}", simpleNewsDTO.getId());
        News news = newsDao.findOne(simpleNewsDTO.getId());
        if (news != null) {
            return newsDTOConverter.toDto(news);
        } else {
            throw new NewsNotFoundException("Piece of news was not found with Id : " + simpleNewsDTO.getId());
        }
    }

    @Override
    public NewsDTO update(NewsDTO newsDTO) {
        logger.info("Updating News");
        logger.debug("Updating News : {}", newsDTO);
        News news = newsDao.findOne(newsDTO.getId());
        if (newsDTO.getContent() != null && !newsDTO.getContent().equals(news.getContent())) {
            news.setContent(newsDTO.getContent());
        }
        if (newsDTO.getTitle() != null && !newsDTO.getTitle().equals(news.getTitle())) {
            news.setTitle(newsDTO.getTitle());
        }
        newsDao.update(news);
        return newsDTOConverter.toDto(news);
    }

    @Override
    public void deleteById(SimpleNewsDTO simpleNewsDTO) {
        logger.info("Deleting News by Id");
        logger.debug("Deleting News by Id : {}", simpleNewsDTO.getId());
        newsDao.deleteById(simpleNewsDTO.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Long countAll() {
        logger.info("Counting all News");
        return newsDao.countAll();
    }
}
