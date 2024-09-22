package vn.demo.starter.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(UserWallet.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class UserWallet_ extends vn.demo.starter.entity.AbstractAuditingEntity_ {

	
	/**
	 * @see vn.demo.starter.entity.UserWallet#id
	 **/
	public static volatile SingularAttribute<UserWallet, Long> id;
	
	/**
	 * @see vn.demo.starter.entity.UserWallet#freezeBalance
	 **/
	public static volatile SingularAttribute<UserWallet, BigDecimal> freezeBalance;
	
	/**
	 * @see vn.demo.starter.entity.UserWallet
	 **/
	public static volatile EntityType<UserWallet> class_;
	
	/**
	 * @see vn.demo.starter.entity.UserWallet#user
	 **/
	public static volatile SingularAttribute<UserWallet, User> user;
	
	/**
	 * @see vn.demo.starter.entity.UserWallet#availableBalance
	 **/
	public static volatile SingularAttribute<UserWallet, BigDecimal> availableBalance;

	public static final String ID = "id";
	public static final String FREEZE_BALANCE = "freezeBalance";
	public static final String USER = "user";
	public static final String AVAILABLE_BALANCE = "availableBalance";

}

