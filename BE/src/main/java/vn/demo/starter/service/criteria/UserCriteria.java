package vn.demo.starter.service.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.demo.starter.entity.enumeration.Role;
import vn.demo.starter.entity.enumeration.UserStatus;
import vn.demo.starter.service.query.Criteria;
import vn.demo.starter.service.query.filter.Filter;
import vn.demo.starter.service.query.filter.LongFilter;
import vn.demo.starter.service.query.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCriteria implements Serializable, Criteria {

    private StringFilter email;
    private StringFilter name;
    private LongFilter userId;
    private UserStatusFilter status;
    private UserRoleFilter role;

    public UserCriteria(UserCriteria other) {
        this.email = Objects.nonNull(other.email) ? other.email : null;
        this.userId = Objects.nonNull(other.userId) ? other.userId : null;
        this.name = Objects.nonNull(other.name) ? other.name : null;
        this.status = Objects.nonNull(other.status) ? other.status : null;
        this.role = Objects.nonNull(other.role) ? other.role : null;
    }

    @Override
    public Criteria copy() {
        return new UserCriteria(this);
    }

    public static class UserStatusFilter extends Filter<UserStatus> implements Serializable {
        private static final long serialVersionUID = 1L;

        public UserStatusFilter() {
        }

        public UserStatusFilter(UserStatusFilter other) {
            super(other);
        }

        @Override
        public Filter<UserStatus> copy() {
            return new UserStatusFilter(this);
        }
    }

    public static class UserRoleFilter extends Filter<Role> implements Serializable {
        private static final long serialVersionUID = 1L;

        public UserRoleFilter() {
        }

        public UserRoleFilter(UserRoleFilter other) {
            super(other);
        }

        @Override
        public Filter<Role> copy() {
            return new UserRoleFilter(this);
        }
    }
}
