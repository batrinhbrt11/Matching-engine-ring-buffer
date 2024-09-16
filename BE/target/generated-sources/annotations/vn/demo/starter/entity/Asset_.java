package vn.demo.starter.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Asset.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Asset_ extends vn.demo.starter.entity.AbstractAuditingEntity_ {

	
	/**
	 * @see vn.demo.starter.entity.Asset#nativeToken
	 **/
	public static volatile SingularAttribute<Asset, Boolean> nativeToken;
	
	/**
	 * @see vn.demo.starter.entity.Asset#chain
	 **/
	public static volatile SingularAttribute<Asset, Chain> chain;
	
	/**
	 * @see vn.demo.starter.entity.Asset#deleted
	 **/
	public static volatile SingularAttribute<Asset, Boolean> deleted;
	
	/**
	 * @see vn.demo.starter.entity.Asset#externalAssetId
	 **/
	public static volatile SingularAttribute<Asset, String> externalAssetId;
	
	/**
	 * @see vn.demo.starter.entity.Asset#contractAddress
	 **/
	public static volatile SingularAttribute<Asset, String> contractAddress;
	
	/**
	 * @see vn.demo.starter.entity.Asset#id
	 **/
	public static volatile SingularAttribute<Asset, Long> id;
	
	/**
	 * @see vn.demo.starter.entity.Asset
	 **/
	public static volatile EntityType<Asset> class_;
	
	/**
	 * @see vn.demo.starter.entity.Asset#coin
	 **/
	public static volatile SingularAttribute<Asset, Coin> coin;

	public static final String NATIVE_TOKEN = "nativeToken";
	public static final String CHAIN = "chain";
	public static final String DELETED = "deleted";
	public static final String EXTERNAL_ASSET_ID = "externalAssetId";
	public static final String CONTRACT_ADDRESS = "contractAddress";
	public static final String ID = "id";
	public static final String COIN = "coin";

}

