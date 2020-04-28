package com.blueberry.springbootinventoryapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.blueberry.springbootinventoryapp.model.Asset;
import com.blueberry.springbootinventoryapp.model.AssetRowMapper;
import com.blueberry.springbootinventoryapp.model.AssetType;
import com.blueberry.springbootinventoryapp.model.AssetTypeRowMapper;

@Repository
public class AssetDaoImpl implements AssetDao {
		
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {    
	    this.jdbcTemplate = jdbcTemplate;
	}
	
	// method to Add an Asset
	public int addAsset(Asset asset) {
		

		//insert into the database, the fields of the asset object
		String sql = "INSERT INTO asset(make, model, asset_type_id) VALUES(?,?,?)";
		return jdbcTemplate.update(sql, new Object[] {asset.getMake(), asset.getModel(), asset.getAssetTypeId()});
	}
	
	
	// method to get an Asset
	public Asset getAsset(int assetId) throws EmptyResultDataAccessException {
		
		try {
			
			String sql = "SELECT asset_id, make, model, asset.asset_type_id, asset_type_desc FROM asset LEFT JOIN asset_type on asset.asset_type_id = asset_type.asset_type_id\n" +
			  "WHERE asset_id = ?";
			
		//	String sql = "SELECT asset_id, make, model FROM asset WHERE asset_id = ?";
			
			return this.jdbcTemplate.queryForObject(sql, new Object[] {assetId}, new AssetRowMapper());

		
		}  catch(Exception e) {
			System.out.println(e);
		}
		  
		  return null;
	}
	
   
	   
	 // method to get All Assets
	 public List<Asset> getAssets() {
		 
		 String sql = "SELECT asset_id, make, model, asset.asset_type_id, asset_type_desc FROM asset LEFT JOIN asset_type on asset.asset_type_id = asset_type.asset_type_id";

		 List<Asset> assets = jdbcTemplate.query(sql, new AssetRowMapper());
		 return assets;
	   }
   
   
	 // method to get All Assets with a limit of 10 assets per page
	 public List<Asset> getAssetsByOffset(int offset) {
		 
		 int psgeSize = 10;

		 String sql = "SELECT asset_id, make, model, asset.asset_type_id, asset_type_desc FROM asset LEFT JOIN asset_type on asset.asset_type_id = asset_type.asset_type_id LIMIT ?, ?";
		 List<Asset> assets = jdbcTemplate.query(sql, new Object[] {offset, pageSize}, new AssetRowMapper());
		   
		 return assets;
	   }
	   
	   
	// Method to Update an Asset
	public int updateAsset(Asset asset) {
		

		String sql = "UPDATE asset SET make = ?, model = ?, asset_type_id = ? WHERE asset_id = ?";
		return this.jdbcTemplate.update(sql, new Object[] {asset.getMake(), asset.getModel(), asset.getAssetTypeId(), asset.getAssetId()});
	}
	
	
	// method to get all Asset Types	
	public List<AssetType> getAssetTypes() {
		
		String sql = "SELECT * FROM asset_type ORDER BY asset_type_desc ASC";
		List<AssetType> assetTypeList = jdbcTemplate.query(sql, new AssetTypeRowMapper());
		   
		return assetTypeList;
	} 
		
	   	
	// method to get the assetType by assetTypeId
	public AssetType getAssetType(int assetTypeId) throws EmptyResultDataAccessException {
		
		try {
			String sql = "SELECT asset_type_id, asset_type_desc FROM asset_type where asset_type_id = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] {assetTypeId}, new AssetTypeRowMapper());	
		}
		  
		catch(Exception e) {
			
			System.out.println(e);
				
		}
		
		return null;
	   }
	  
	  // method to delete an Asset
	  public int deleteAsset(int assetId) {
	  
		String sql = "DELETE FROM asset WHERE asset_id = ?";
		return jdbcTemplate.update(sql, new Object[] {assetId});
 
	  }
}
