package vn.demo.starter.service.criteria;

import lombok.Data;

import java.time.Instant;

@Data
public class ActivityCriteria {
    private Long userId;
    private String action;
    private String category;
    private Instant fromDate;
    private Instant toDate;
}