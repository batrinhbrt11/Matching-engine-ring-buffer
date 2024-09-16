package vn.demo.starter.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;

@StaticMetamodel(UserSession.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class UserSession_ extends vn.demo.starter.entity.AbstractAuditingEntity_ {

	
	/**
	 * @see vn.demo.starter.entity.UserSession#tokenId
	 **/
	public static volatile SingularAttribute<UserSession, String> tokenId;
	
	/**
	 * @see vn.demo.starter.entity.UserSession#expiredDate
	 **/
	public static volatile SingularAttribute<UserSession, Instant> expiredDate;
	
	/**
	 * @see vn.demo.starter.entity.UserSession#id
	 **/
	public static volatile SingularAttribute<UserSession, Long> id;
	
	/**
	 * @see vn.demo.starter.entity.UserSession
	 **/
	public static volatile EntityType<UserSession> class_;
	
	/**
	 * @see vn.demo.starter.entity.UserSession#user
	 **/
	public static volatile SingularAttribute<UserSession, User> user;

	public static final String TOKEN_ID = "tokenId";
	public static final String EXPIRED_DATE = "expiredDate";
	public static final String ID = "id";
	public static final String USER = "user";

}

