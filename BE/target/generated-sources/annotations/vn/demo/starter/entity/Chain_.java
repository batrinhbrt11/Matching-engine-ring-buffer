package vn.demo.starter.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Chain.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Chain_ extends vn.demo.starter.entity.AbstractAuditingEntity_ {

	
	/**
	 * @see vn.demo.starter.entity.Chain#symbol
	 **/
	public static volatile SingularAttribute<Chain, String> symbol;
	
	/**
	 * @see vn.demo.starter.entity.Chain#image
	 **/
	public static volatile SingularAttribute<Chain, String> image;
	
	/**
	 * @see vn.demo.starter.entity.Chain#addressRegex
	 **/
	public static volatile SingularAttribute<Chain, String> addressRegex;
	
	/**
	 * @see vn.demo.starter.entity.Chain#txnScanUrl
	 **/
	public static volatile SingularAttribute<Chain, String> txnScanUrl;
	
	/**
	 * @see vn.demo.starter.entity.Chain#tagRegex
	 **/
	public static volatile SingularAttribute<Chain, String> tagRegex;
	
	/**
	 * @see vn.demo.starter.entity.Chain#name
	 **/
	public static volatile SingularAttribute<Chain, String> name;
	
	/**
	 * @see vn.demo.starter.entity.Chain#displayOrder
	 **/
	public static volatile SingularAttribute<Chain, Integer> displayOrder;
	
	/**
	 * @see vn.demo.starter.entity.Chain#id
	 **/
	public static volatile SingularAttribute<Chain, Long> id;
	
	/**
	 * @see vn.demo.starter.entity.Chain
	 **/
	public static volatile EntityType<Chain> class_;
	
	/**
	 * @see vn.demo.starter.entity.Chain#addressScanUrl
	 **/
	public static volatile SingularAttribute<Chain, String> addressScanUrl;

	public static final String SYMBOL = "symbol";
	public static final String IMAGE = "image";
	public static final String ADDRESS_REGEX = "addressRegex";
	public static final String TXN_SCAN_URL = "txnScanUrl";
	public static final String TAG_REGEX = "tagRegex";
	public static final String NAME = "name";
	public static final String DISPLAY_ORDER = "displayOrder";
	public static final String ID = "id";
	public static final String ADDRESS_SCAN_URL = "addressScanUrl";

}

