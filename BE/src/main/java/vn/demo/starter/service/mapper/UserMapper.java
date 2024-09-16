package vn.demo.starter.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.demo.starter.entity.User;
import vn.demo.starter.service.dto.UserDto;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                Objects.nonNull(user.getUserDetail()) ? user.getUserDetail().getFirstName() : null,
                Objects.nonNull(user.getUserDetail()) ? user.getUserDetail().getLastName() : null,
                user.getStatus()
        );
    }

}
