package com.example.board.result;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageResult<T> {

    private Pageable pageable;
    private long totalElement;
    private int totalPages;
    private boolean hasPrevious;
    private boolean hasNext;
    private boolean isFirst;
    private boolean isLast;
    private List<T> content;

    public PageResult(Page<?> page, List<T> content) {
        this.pageable = page.getPageable();
        this.totalElement = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.hasPrevious = page.hasPrevious();
        this.hasNext = page.hasNext();
        this.isFirst = page.isFirst();
        this.isLast = page.isLast();
        this.content = content;
    }
}