package vn.demo.starter.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(P2PTransaction.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class P2PTransaction_ extends vn.demo.starter.entity.AbstractAuditingEntity_ {

	
	/**
	 * @see vn.demo.starter.entity.P2PTransaction#amount
	 **/
	public static volatile SingularAttribute<P2PTransaction, BigDecimal> amount;
	
	/**
	 * @see vn.demo.starter.entity.P2PTransaction#sellOrder
	 **/
	public static volatile SingularAttribute<P2PTransaction, P2POrder> sellOrder;
	
	/**
	 * @see vn.demo.starter.entity.P2PTransaction#price
	 **/
	public static volatile SingularAttribute<P2PTransaction, BigDecimal> price;
	
	/**
	 * @see vn.demo.starter.entity.P2PTransaction#id
	 **/
	public static volatile SingularAttribute<P2PTransaction, Long> id;
	
	/**
	 * @see vn.demo.starter.entity.P2PTransaction
	 **/
	public static volatile EntityType<P2PTransaction> class_;
	
	/**
	 * @see vn.demo.starter.entity.P2PTransaction#buyOrder
	 **/
	public static volatile SingularAttribute<P2PTransaction, P2POrder> buyOrder;

	public static final String AMOUNT = "amount";
	public static final String SELL_ORDER = "sellOrder";
	public static final String PRICE = "price";
	public static final String ID = "id";
	public static final String BUY_ORDER = "buyOrder";

}

