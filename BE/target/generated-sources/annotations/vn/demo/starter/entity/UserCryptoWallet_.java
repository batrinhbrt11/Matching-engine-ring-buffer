package vn.demo.starter.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(UserCryptoWallet.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class UserCryptoWallet_ extends vn.demo.starter.entity.AbstractAuditingEntity_ {

	
	/**
	 * @see vn.demo.starter.entity.UserCryptoWallet#id
	 **/
	public static volatile SingularAttribute<UserCryptoWallet, Long> id;
	
	/**
	 * @see vn.demo.starter.entity.UserCryptoWallet#freezeBalance
	 **/
	public static volatile SingularAttribute<UserCryptoWallet, BigDecimal> freezeBalance;
	
	/**
	 * @see vn.demo.starter.entity.UserCryptoWallet#asset
	 **/
	public static volatile SingularAttribute<UserCryptoWallet, Asset> asset;
	
	/**
	 * @see vn.demo.starter.entity.UserCryptoWallet
	 **/
	public static volatile EntityType<UserCryptoWallet> class_;
	
	/**
	 * @see vn.demo.starter.entity.UserCryptoWallet#user
	 **/
	public static volatile SingularAttribute<UserCryptoWallet, User> user;
	
	/**
	 * @see vn.demo.starter.entity.UserCryptoWallet#availableBalance
	 **/
	public static volatile SingularAttribute<UserCryptoWallet, BigDecimal> availableBalance;

	public static final String ID = "id";
	public static final String FREEZE_BALANCE = "freezeBalance";
	public static final String ASSET = "asset";
	public static final String USER = "user";
	public static final String AVAILABLE_BALANCE = "availableBalance";

}

