package vn.demo.starter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.demo.starter.constant.MessageConstant;
import vn.demo.starter.entity.User;
import vn.demo.starter.entity.UserDetail_;
import vn.demo.starter.entity.User_;
import vn.demo.starter.exception.BadRequestException;
import vn.demo.starter.service.UserService;
import vn.demo.starter.repository.UserRepository;
import vn.demo.starter.service.criteria.UserCriteria;
import vn.demo.starter.service.dto.UserDto;
import vn.demo.starter.service.mapper.UserMapper;
import vn.demo.starter.service.query.QueryService;
import vn.demo.starter.service.query.filter.BooleanFilter;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl extends QueryService<User> implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public User getUser(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new BadRequestException(MessageConstant.USER_NOT_EXIST));
    }

    @Override
    public UserDto getUserDto(Long userId) {
        User user = getUser(userId);
        return userMapper.toDto(user);
    }

    @Override
    public boolean isActive(Long userId) {
        return this.getUser(userId).isActive();
    }

    @Override
    public Page<UserDto> getUsers(Pageable pageable, UserCriteria criteria) {
        Specification<User> specification = createSpecification(criteria);
        return userRepository.findAll(specification, pageable).map(userMapper::toDto);
    }

    private Specification<User> createSpecification(UserCriteria criteria) {
        Specification<User> specification = Specification.where(null);

        if (criteria != null) {
            if (Objects.nonNull(criteria.getEmail())) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), User_.email));
            }
            if (Objects.nonNull(criteria.getName())) {
                Specification<User> specificationCustom = buildSpecification(criteria.getName(),
                        root -> root.get(User_.userDetail).get(UserDetail_.firstName));
                specificationCustom = specificationCustom.or(buildSpecification(criteria.getName(),
                        root -> root.get(User_.userDetail).get(UserDetail_.lastName)));

                specification = specification.and(specificationCustom);
            }
            if (Objects.nonNull(criteria.getStatus())) {
                specification = specification.and(buildSpecification(criteria.getStatus(), User_.status));
            }
            if (Objects.nonNull(criteria.getRole())) {
                specification = specification.and(buildSpecification(criteria.getRole(), User_.role));
            }
            if (Objects.nonNull(criteria.getUserId())) {
                specification = specification.and(buildSpecification(criteria.getUserId(), User_.id));
            }

        }
        return specification;
    }
}
