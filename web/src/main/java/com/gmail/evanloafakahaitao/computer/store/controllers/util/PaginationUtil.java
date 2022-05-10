package com.gmail.evanloafakahaitao.computer.store.controllers.util;

import com.gmail.evanloafakahaitao.computer.store.controllers.properties.PageProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtil {

    private static final Logger logger = LogManager.getLogger(PaginationUtil.class);

    private final PageProperties pageProperties;

    @Autowired
    public PaginationUtil(PageProperties pageProperties) {
        this.pageProperties = pageProperties;
    }

    public int getStartPosition(int page) {
        logger.info("Calculating start position for page : {}", page);
        return (page - 1) * pageProperties.getPaginationMaxResults();
    }

    public int getPageNumerationStart(int page) {
        logger.info("Calculating start position for page numeration at page : {}", page);
        return getStartPosition(page) + 1;
    }

    public int[] getPageNumbers(int objectCount) {
        logger.info("Calculating displayed page numbers for {} objects", objectCount);
        if (objectCount == 0) {
            return new int[] {1};
        } else {
            int pagesCount = (objectCount + pageProperties.getPaginationMaxResults() - 1) / pageProperties.getPaginationMaxResults();
            int[] pageNumbers = new int[pagesCount];
            for (int i = 1; i <= pagesCount; i++) {
                pageNumbers[i - 1] = i;
            }
            return pageNumbers;
        }
    }
}
