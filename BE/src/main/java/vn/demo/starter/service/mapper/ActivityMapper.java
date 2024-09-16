package vn.demo.starter.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.demo.starter.entity.Activity;
import vn.demo.starter.service.dto.ActivityDto;

@Component
@RequiredArgsConstructor
public class ActivityMapper {

    public ActivityDto toDto(Activity activity){
        return new ActivityDto(
                activity.getUserId(),
                activity.getAction(),
                activity.getDetail(),
                activity.getCategory(),
                activity.getUserAgent(),
                activity.getUserIp(),
                activity.getTimestamp()
        );
    }
}