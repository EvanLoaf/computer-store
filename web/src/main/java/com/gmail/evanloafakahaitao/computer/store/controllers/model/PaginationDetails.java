package com.gmail.evanloafakahaitao.computer.store.controllers.model;

public class PaginationDetails {

    private int page;
    private int startPosition;
    private int[] pageNumbers;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int[] getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(int[] pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }
}
