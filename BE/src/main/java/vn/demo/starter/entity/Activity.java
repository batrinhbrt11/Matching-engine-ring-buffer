package vn.demo.starter.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import vn.demo.starter.entity.enumeration.ActivityAction;
import vn.demo.starter.entity.enumeration.ActivityCategory;

import java.time.Instant;

@Document(value = "activities")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity extends AbstractAuditingEntity  {
    @Id
    private String id;
    @Field(value = "user_id")
    private Long userId;
    @Field
    @Enumerated(EnumType.STRING)
    private ActivityAction action;
    @Field
    @Enumerated(EnumType.STRING)
    private ActivityCategory category;
    @Field
    private String detail;
    @Field(value = "time_stamp")
    private Instant timestamp;
    @Field(value = "user_agent")
    private String userAgent;
    @Field(value = "user_ip")
    private String userIp;
}
