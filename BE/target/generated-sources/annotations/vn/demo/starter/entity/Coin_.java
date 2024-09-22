package vn.demo.starter.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Coin.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Coin_ extends vn.demo.starter.entity.AbstractAuditingEntity_ {

	
	/**
	 * @see vn.demo.starter.entity.Coin#symbol
	 **/
	public static volatile SingularAttribute<Coin, String> symbol;
	
	/**
	 * @see vn.demo.starter.entity.Coin#image
	 **/
	public static volatile SingularAttribute<Coin, String> image;
	
	/**
	 * @see vn.demo.starter.entity.Coin#assets
	 **/
	public static volatile ListAttribute<Coin, Asset> assets;
	
	/**
	 * @see vn.demo.starter.entity.Coin#name
	 **/
	public static volatile SingularAttribute<Coin, String> name;
	
	/**
	 * @see vn.demo.starter.entity.Coin#displayOrder
	 **/
	public static volatile SingularAttribute<Coin, Integer> displayOrder;
	
	/**
	 * @see vn.demo.starter.entity.Coin#id
	 **/
	public static volatile SingularAttribute<Coin, Long> id;
	
	/**
	 * @see vn.demo.starter.entity.Coin
	 **/
	public static volatile EntityType<Coin> class_;

	public static final String SYMBOL = "symbol";
	public static final String IMAGE = "image";
	public static final String ASSETS = "assets";
	public static final String NAME = "name";
	public static final String DISPLAY_ORDER = "displayOrder";
	public static final String ID = "id";

}

