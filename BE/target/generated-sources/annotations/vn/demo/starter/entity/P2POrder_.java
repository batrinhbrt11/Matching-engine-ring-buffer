package vn.demo.starter.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import vn.demo.starter.entity.enumeration.OrderStatus;
import vn.demo.starter.entity.enumeration.OrderType;

@StaticMetamodel(P2POrder.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class P2POrder_ extends vn.demo.starter.entity.AbstractAuditingEntity_ {

	
	/**
	 * @see vn.demo.starter.entity.P2POrder#orderType
	 **/
	public static volatile SingularAttribute<P2POrder, OrderType> orderType;
	
	/**
	 * @see vn.demo.starter.entity.P2POrder#remainingAmount
	 **/
	public static volatile SingularAttribute<P2POrder, BigDecimal> remainingAmount;
	
	/**
	 * @see vn.demo.starter.entity.P2POrder#totalAmount
	 **/
	public static volatile SingularAttribute<P2POrder, BigDecimal> totalAmount;
	
	/**
	 * @see vn.demo.starter.entity.P2POrder#price
	 **/
	public static volatile SingularAttribute<P2POrder, BigDecimal> price;
	
	/**
	 * @see vn.demo.starter.entity.P2POrder#orderStatus
	 **/
	public static volatile SingularAttribute<P2POrder, OrderStatus> orderStatus;
	
	/**
	 * @see vn.demo.starter.entity.P2POrder#id
	 **/
	public static volatile SingularAttribute<P2POrder, Long> id;
	
	/**
	 * @see vn.demo.starter.entity.P2POrder#asset
	 **/
	public static volatile SingularAttribute<P2POrder, Asset> asset;
	
	/**
	 * @see vn.demo.starter.entity.P2POrder
	 **/
	public static volatile EntityType<P2POrder> class_;
	
	/**
	 * @see vn.demo.starter.entity.P2POrder#user
	 **/
	public static volatile SingularAttribute<P2POrder, User> user;

	public static final String ORDER_TYPE = "orderType";
	public static final String REMAINING_AMOUNT = "remainingAmount";
	public static final String TOTAL_AMOUNT = "totalAmount";
	public static final String PRICE = "price";
	public static final String ORDER_STATUS = "orderStatus";
	public static final String ID = "id";
	public static final String ASSET = "asset";
	public static final String USER = "user";

}

