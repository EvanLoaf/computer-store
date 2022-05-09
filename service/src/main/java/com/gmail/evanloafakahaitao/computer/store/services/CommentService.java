package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.services.dto.CommentDTO;
import com.gmail.evanloafakahaitao.computer.store.services.dto.NewsDTO;

public interface CommentService {

    CommentDTO save(NewsDTO newsDTO);

    void deleteById(NewsDTO newsDTO);
}
