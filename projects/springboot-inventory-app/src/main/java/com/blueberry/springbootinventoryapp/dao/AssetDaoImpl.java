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
	
	
	// Method to Add an Asset
	public int addAsset(Asset asset) {
		
	
		// AssetType assetType = this.getAssetTypeObj(asset);
		
		//insert into the database, the fields of the asset object
		String sql = "INSERT INTO asset(make, model, asset_type_id) VALUES(?,?,?)";
		return jdbcTemplate.update(sql, new Object[] {asset.getMake(), asset.getModel(), asset.getAssetTypeId()});
	}
	
		
	
	
	
	// Method to get an Asset
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
	
   
	   
   // Method to get All Assets
   public List<Asset> getAssets() {
	   
	   String sql = "SELECT asset_id, make, model, asset.asset_type_id, asset_type_desc FROM asset LEFT JOIN asset_type on asset.asset_type_id = asset_type.asset_type_id";
	   	//	+ " JOIN asset_type on asset.asset_type_id = asset_type.asset_type_id";
   	   List<Asset> assets = jdbcTemplate.query(sql, new AssetRowMapper());
      	return assets;
   }
   
   
   
   
   // Method to get All Assets with a limit of 10 Assets per page
   public List<Asset> getAssetsByOffset(int offset) {
	   
	   int recordsPerPage = 10;
	   
	   String sql = "SELECT asset_id, make, model, asset.asset_type_id, asset_type_desc FROM asset LEFT JOIN asset_type on asset.asset_type_id = asset_type.asset_type_id LIMIT ?, ?";
   	   List<Asset> assets = jdbcTemplate.query(sql, new Object[] {offset, recordsPerPage}, new AssetRowMapper());
       return assets;
   }
	   
	   
	// Method to Update an Asset
	public int updateAsset(Asset asset) {
		
		
		
	//	AssetType assetType = this.getAssetTypeObj(asset);
	//	System.out.println("some details: " + assetType.getAssetTypeId());
	//	System.out.println("some details: " + assetType.getAssetTypeName());


		String sql = "UPDATE asset SET make = ?, model = ?, asset_type_id = ? WHERE asset_id = ?";
		
		return this.jdbcTemplate.update(sql, new Object[] {asset.getMake(), asset.getModel(), asset.getAssetTypeId(), asset.getAssetId()});
		
		
	}
	
	
	 // Method to return All Asset Types	
	   public List<AssetType> getAssetTypes() {
		   
		   String sql = "SELECT * FROM asset_type ORDER BY asset_type_desc ASC";
		   List<AssetType> assetTypeList = jdbcTemplate.query(sql, new AssetTypeRowMapper());
		   
		   return assetTypeList;
	   } 
		
	   	
	  // Get the Asset Type object / details based on an Asset Type ID submitted
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
	  
	  
	  public int deleteAsset(int assetId) {
		  
		 int result;
		  
		String sql = "DELETE FROM asset WHERE asset_id = ?";
		
		result = jdbcTemplate.update(sql, new Object[] {assetId});
		
		return result;
		  
		  
		  
	  }
	

}
