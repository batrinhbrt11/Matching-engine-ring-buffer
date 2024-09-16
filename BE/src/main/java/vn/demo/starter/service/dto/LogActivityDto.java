package vn.demo.starter.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.demo.starter.entity.enumeration.ActivityAction;
import vn.demo.starter.entity.enumeration.ActivityCategory;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogActivityDto {
    private Long userId;
    private ActivityAction action;
    private ActivityCategory category;
    private String detail;
}
