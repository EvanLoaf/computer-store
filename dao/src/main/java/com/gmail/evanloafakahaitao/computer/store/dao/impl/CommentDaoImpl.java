package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.CommentDao;
import com.gmail.evanloafakahaitao.computer.store.dao.model.Comment;

public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {

    public CommentDaoImpl() {
        super(Comment.class);
    }
}
