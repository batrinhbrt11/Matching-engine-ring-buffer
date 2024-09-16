package vn.demo.starter.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserDetail.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class UserDetail_ extends vn.demo.starter.entity.AbstractAuditingEntity_ {

	
	/**
	 * @see vn.demo.starter.entity.UserDetail#firstName
	 **/
	public static volatile SingularAttribute<UserDetail, String> firstName;
	
	/**
	 * @see vn.demo.starter.entity.UserDetail#lastName
	 **/
	public static volatile SingularAttribute<UserDetail, String> lastName;
	
	/**
	 * @see vn.demo.starter.entity.UserDetail#id
	 **/
	public static volatile SingularAttribute<UserDetail, Long> id;
	
	/**
	 * @see vn.demo.starter.entity.UserDetail
	 **/
	public static volatile EntityType<UserDetail> class_;
	
	/**
	 * @see vn.demo.starter.entity.UserDetail#user
	 **/
	public static volatile SingularAttribute<UserDetail, User> user;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String ID = "id";
	public static final String USER = "user";

}

