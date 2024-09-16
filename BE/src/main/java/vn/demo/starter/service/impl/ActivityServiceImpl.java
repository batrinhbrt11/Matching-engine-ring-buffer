package vn.demo.starter.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.demo.starter.entity.Activity;
import vn.demo.starter.entity.LogActionResponseDto;
import vn.demo.starter.entity.LogCategoryResponseDto;
import vn.demo.starter.entity.enumeration.ActivityAction;
import vn.demo.starter.entity.enumeration.ActivityCategory;
import vn.demo.starter.repository.ActivityRepository;
import vn.demo.starter.security.SecurityUtils;
import vn.demo.starter.service.ActivityService;
import vn.demo.starter.service.criteria.ActivityCriteria;
import vn.demo.starter.service.dto.ActivityDto;
import vn.demo.starter.service.dto.LogActivityDto;
import vn.demo.starter.service.mapper.ActivityMapper;
import vn.demo.starter.service.query.QueryService;
import vn.demo.starter.util.DateUtils;
import vn.demo.starter.util.LogActivityDetailBuilder;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActivityServiceImpl  extends QueryService<Activity> implements ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;
    private final MongoTemplate mongoTemplate;


    @Override
    public Page<ActivityDto> getActivities(ActivityCriteria criteria,Pageable pageable) {
        Query query = createQuery(criteria);
        if(query.getQueryObject().isEmpty()){
            return activityRepository.findAll(pageable).map(activityMapper::toDto);
        }
        long total = mongoTemplate.count(query, Activity.class);
        List<Activity> activityList = mongoTemplate.find(query.with(pageable), Activity.class);
        return new PageImpl<>(activityList, pageable, total).map(activityMapper::toDto);
    }

    private Query createQuery(ActivityCriteria criteria){
        Query query = new Query();
        if(Objects.nonNull(criteria.getUserId())){
            query.addCriteria(Criteria.where("user_id").is(criteria.getUserId()));
        }
        if(Objects.nonNull(criteria.getCategory())){
            query.addCriteria(Criteria.where("category").is(criteria.getCategory()));
        }
        if(Objects.nonNull(criteria.getAction())){
            query.addCriteria(Criteria.where("action").is(criteria.getAction()));
        }
        if(Objects.nonNull(criteria.getFromDate()) && Objects.nonNull(criteria.getToDate())){
            query.addCriteria(
                    Criteria.where("time_stamp")
                            .gte(criteria.getFromDate())
                            .andOperator(Criteria.where("time_stamp").lte(criteria.getToDate()))
            );
        }
        if(Objects.nonNull(criteria.getFromDate()) && Objects.isNull(criteria.getToDate())){
            query.addCriteria(Criteria.where("time_stamp").gte(criteria.getFromDate()));
        }
        if(Objects.nonNull(criteria.getToDate()) && Objects.isNull(criteria.getFromDate())){
            query.addCriteria(Criteria.where("time_stamp").lte(criteria.getToDate()));
        }
        return query;
    }

    @Override
    public List<ActivityDto> export(ActivityCriteria criteria) {
        Query query = createQuery(criteria);
        if(query.getQueryObject().isEmpty()){
            return activityRepository.findAll().stream().map(activityMapper::toDto).toList();
        }
        return mongoTemplate.find(query, Activity.class).stream().map(activityMapper::toDto).toList();
    }

    @Override
    public List<LogActionResponseDto> getActionList() {
        return ActivityAction.toLogActionResponseDtoList();
    }

    @Override
    public List<LogCategoryResponseDto> getCategoryList() {
        return ActivityCategory.toLogCategoryResponseDtoList();
    }

    @Override
    public void logActivity(LogActivityDto activityDto) {
        try {
            Pair<String, String> clientInfo = SecurityUtils.getCurrentUserAgentAndIpAddress();
            activityRepository.save(Activity.builder()
                    .userId(activityDto.getUserId())
                    .action(activityDto.getAction())
                    .detail(activityDto.getDetail())
                    .category(activityDto.getCategory())
                    .userAgent(clientInfo.getLeft())
                    .userIp(clientInfo.getRight())
                    .timestamp(DateUtils.currentInstant())
                    .build());
        } catch (Exception exception) {
            log.error("Error on log activity for request: {}", activityDto, exception);
        }
    }

    @Override
    public void logLogin(Long userId, String detail) {
        logActivity(LogActivityDto.builder()
                .userId(userId)
                .action(ActivityAction.LOGIN)
                .category(ActivityCategory.AUTHENTICATION)
                .detail(detail)
                .build());
    }

    @Override
    public void logLoginFailed(Long userId, String detail) {
        logActivity(LogActivityDto.builder()
                .userId(userId)
                .action(ActivityAction.LOGIN_FAILED)
                .category(ActivityCategory.AUTHENTICATION)
                .detail(detail)
                .build());
    }

    @Override
    public void logRegister(Long userId) {
        logActivity(LogActivityDto.builder()
                .userId(userId)
                .action(ActivityAction.REGISTER)
                .category(ActivityCategory.USER_ACCOUNT)
                .detail(LogActivityDetailBuilder.registerDetail(userId))
                .build());
    }

    @Override
    public void logLogout(Long userId) {
        logActivity(LogActivityDto.builder()
                .userId(userId)
                .action(ActivityAction.LOGOUT)
                .category(ActivityCategory.AUTHENTICATION)
                .detail(LogActivityDetailBuilder.logoutDetail(userId))
                .build());
    }

    @Override
    public void logChangePassword(Long userId) {
        logActivity(LogActivityDto.builder()
                .userId(userId)
                .action(ActivityAction.CHANGE_PASSWORD)
                .category(ActivityCategory.AUTHENTICATION)
                .detail(LogActivityDetailBuilder.changePasswordDetail(userId))
                .build());
    }

    @Override
    public void logResetPassword(Long userId) {
        logActivity(LogActivityDto.builder()
                .userId(userId)
                .action(ActivityAction.RESET_PASSWORD)
                .category(ActivityCategory.AUTHENTICATION)
                .detail(LogActivityDetailBuilder.resetPasswordDetail(userId))
                .build());
    }
}
