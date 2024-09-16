package vn.demo.starter.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import vn.demo.starter.entity.enumeration.Role;
import vn.demo.starter.entity.enumeration.UserStatus;

@StaticMetamodel(User.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class User_ extends vn.demo.starter.entity.AbstractAuditingEntity_ {

	
	/**
	 * @see vn.demo.starter.entity.User#password
	 **/
	public static volatile SingularAttribute<User, String> password;
	
	/**
	 * @see vn.demo.starter.entity.User#sessions
	 **/
	public static volatile ListAttribute<User, UserSession> sessions;
	
	/**
	 * @see vn.demo.starter.entity.User#role
	 **/
	public static volatile SingularAttribute<User, Role> role;
	
	/**
	 * @see vn.demo.starter.entity.User#deleted
	 **/
	public static volatile SingularAttribute<User, Boolean> deleted;
	
	/**
	 * @see vn.demo.starter.entity.User#blockedCount
	 **/
	public static volatile SingularAttribute<User, Integer> blockedCount;
	
	/**
	 * @see vn.demo.starter.entity.User#blockedDate
	 **/
	public static volatile SingularAttribute<User, Instant> blockedDate;
	
	/**
	 * @see vn.demo.starter.entity.User#phone
	 **/
	public static volatile SingularAttribute<User, String> phone;
	
	/**
	 * @see vn.demo.starter.entity.User#userDetail
	 **/
	public static volatile SingularAttribute<User, UserDetail> userDetail;
	
	/**
	 * @see vn.demo.starter.entity.User#id
	 **/
	public static volatile SingularAttribute<User, Long> id;
	
	/**
	 * @see vn.demo.starter.entity.User
	 **/
	public static volatile EntityType<User> class_;
	
	/**
	 * @see vn.demo.starter.entity.User#email
	 **/
	public static volatile SingularAttribute<User, String> email;
	
	/**
	 * @see vn.demo.starter.entity.User#status
	 **/
	public static volatile SingularAttribute<User, UserStatus> status;

	public static final String PASSWORD = "password";
	public static final String SESSIONS = "sessions";
	public static final String ROLE = "role";
	public static final String DELETED = "deleted";
	public static final String BLOCKED_COUNT = "blockedCount";
	public static final String BLOCKED_DATE = "blockedDate";
	public static final String PHONE = "phone";
	public static final String USER_DETAIL = "userDetail";
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String STATUS = "status";

}

