package com.example.colegio.dto;


import org.springframework.hateoas.PagedModel;

public class PagedContentDto<T> {
    private T content;
    private PagedModel.PageMetadata page;

    public PagedContentDto(T content, PagedModel.PageMetadata page) {
        this.content = content;
        this.page = page;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public PagedModel.PageMetadata getPage() {
        return page;
    }

    public void setPage(PagedModel.PageMetadata page) {
        this.page = page;
    }
}
