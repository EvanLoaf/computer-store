package com.gmail.evanloafakahaitao.computer.store.services.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.NewsDao;
import com.gmail.evanloafakahaitao.computer.store.dao.UserDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.*;
import com.gmail.evanloafakahaitao.computer.store.services.NewsService;
import com.gmail.evanloafakahaitao.computer.store.services.converters.DTOConverter;
import com.gmail.evanloafakahaitao.computer.store.services.converters.EntityConverter;
import com.gmail.evanloafakahaitao.computer.store.services.dto.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
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
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            News news = newsEntityConverter.toEntity(newsDTO);
            User user = userDao.findOne(news.getUser().getId());
            news.setUser(user);
            news.setCreated(LocalDateTime.now());
            newsDao.create(news);
            NewsDTO savedNews = newsDTOConverter.toDto(news);
            transaction.commit();
            return savedNews;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save News", e);
        }
        return null;
    }

    @Override
    public List<SimpleNewsDTO> findAll() {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<News> newsList = newsDao.findAll();
            List<SimpleNewsDTO> newsDTOList = simpleNewsDTOConverter.toDTOList(newsList);
            transaction.commit();
            return newsDTOList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve all News", e);
        }
        return Collections.emptyList();
    }

    @Override
    public NewsDTO findById(SimpleNewsDTO simpleNewsDTO) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            News news = newsDao.findOne(simpleNewsDTO.getId());
            NewsDTO foundNews = newsDTOConverter.toDto(news);
            transaction.commit();
            return foundNews;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve News by id", e);
        }
        return null;
    }

    @Override
    public NewsDTO update(NewsDTO newsDTO) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            News news = newsDao.findOne(newsDTO.getId());
            if (newsDTO.getContent() != null) {
                news.setContent(newsDTO.getContent());
            }
            if (newsDTO.getTitle() != null) {
                news.setTitle(newsDTO.getTitle());
            }
            newsDao.update(news);
            NewsDTO updatedNews = newsDTOConverter.toDto(news);
            transaction.commit();
            return updatedNews;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to update news", e);
        }
        return null;
    }

    @Override
    public void deleteById(SimpleNewsDTO simpleNewsDTO) {
        Session session = newsDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            newsDao.deleteById(simpleNewsDTO.getId());
            transaction.commit();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete news by id", e);
        }
    }
}
