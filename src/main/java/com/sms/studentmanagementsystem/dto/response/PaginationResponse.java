package com.sms.studentmanagementsystem.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class PaginationResponse<T> {
    private List<T> data;
    private int currentPage;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;
}
