package vn.demo.starter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.demo.starter.entity.LogActionResponseDto;
import vn.demo.starter.entity.LogCategoryResponseDto;
import vn.demo.starter.service.criteria.ActivityCriteria;
import vn.demo.starter.service.dto.ActivityDto;
import vn.demo.starter.service.dto.LogActivityDto;

import java.util.List;

public interface ActivityService {

    Page<ActivityDto> getActivities(ActivityCriteria criteria, Pageable pageable);
    List<ActivityDto> export(ActivityCriteria criteria);
    List<LogActionResponseDto> getActionList();
    List<LogCategoryResponseDto> getCategoryList();
    void logActivity(LogActivityDto activityDto);
    void logLogin(Long userId, String detail);
    void logLoginFailed(Long userId, String detail);
    void logRegister(Long userId);
    void logLogout(Long userId);
    void logChangePassword(Long userId);
    void logResetPassword(Long userId);
}

