package com.gmail.evanloafakahaitao.computer.store.dao.impl;

import com.gmail.evanloafakahaitao.computer.store.dao.XmlDao;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class XmlDaoImpl implements XmlDao {

    @Override
    public File getFile(String path) {
        return new File(path);
    }
}
