package com.gmail.evanloafakahaitao.computer.store.services;

import com.gmail.evanloafakahaitao.computer.store.services.dto.CommentDTO;

public interface CommentService {

    CommentDTO save(CommentDTO commentDTO);

    void deleteById(CommentDTO commentDTO);
}
