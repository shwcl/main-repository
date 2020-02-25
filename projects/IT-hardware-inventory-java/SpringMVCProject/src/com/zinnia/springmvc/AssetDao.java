package com.zinnia.springmvc;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.zinnia.springmvc.Asset;
import com.zinnia.springmvc.AssetRowMapper;


@Repository
public class AssetDao {
	
	
	// we can use s[ring container to instantiate the bean .. dependency???
	
	JdbcTemplate jdbcTemplate;
	

	//Asset asset;
	
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	    this.jdbcTemplate = jdbcTemplate;
	}
	
	
	// Method to Add an Asset
	public int addAsset(Asset asset) {
		
		//return some value to indicate that the insert/update was successful or not/... defensive programming??

		//get the asset Type object that contains data on the asset type associated with the asset  
		
		
		// AssetType assetType = this.getAssetTypeObj(asset);
		
		
		//insert into the database, the fields of the asset object
		String sql = "INSERT INTO asset(make, model, asset_type_id) VALUES(?,?,?)";
		return jdbcTemplate.update(sql, new Object[] {asset.getMake(), asset.getModel(), asset.getAssetTypeId()});


	}
	
	// Method to Update an Asset
	public int updateAsset(Asset asset) {
		
		int result;
		
	//	AssetType assetType = this.getAssetTypeObj(asset);
	//	System.out.println("some details: " + assetType.getAssetTypeId());
	//	System.out.println("some details: " + assetType.getAssetTypeName());

		
		//insert into the database, the fields of the asset object
		String sql = "UPDATE asset SET make = ?, model = ?, asset_type_id = ? WHERE asset_id = ?";
		
		result = jdbcTemplate.update(sql, new Object[] {asset.getMake(), asset.getModel(), asset.getAssetTypeId(), asset.getAssetId()});
		return result;
		
		//jdbcTemplate.update(sql, new Object[] {"editMake", "editModel", 1, 1});
		
	}
	
	
	// Method to Delete an Asset
	public int deleteAsset(Asset asset) {
		
		int result;
		
		// query to delete an asset
		String sql = "DELETE FROM asset WHERE asset_id = ?";
		
		result = jdbcTemplate.update(sql, new Object[] {asset.getAssetId()});
		return result;
		
	}
	
	
 
  // Method to get an Asset
  public Asset getAssetById(int assetId) throws EmptyResultDataAccessException {

	  Asset asset = null;
	  
	  try {
		  
		  String sql = "SELECT asset_id, make, model, asset.asset_type_id, asset_type_desc FROM asset JOIN asset_type on asset.asset_type_id = asset_type.asset_type_id\n" +
				  "WHERE asset_id = ?";
		  return jdbcTemplate.queryForObject(sql, new Object[] {assetId}, new AssetRowMapper());	
	  } 
	  
	  catch(Exception e) {
			System.out.println(e);
			asset = null;
	  }
	  
	  return asset;
   }
  
   
   // Method to get All Assets
   public List<Asset> getAllAssets() {
	   
	   String sql = "SELECT asset_id, make, model, asset.asset_type_id, asset_type_desc FROM asset JOIN asset_type on asset.asset_type_id = asset_type.asset_type_id";
   	   List<Asset> assets = jdbcTemplate.query(sql, new AssetRowMapper());
      	return assets;
  	
   }
   
   
   // Method to get All Assets with a limit of 10 Assets per page
   public List<Asset> getAllAssetsByPage(int offset) {
	   
	   //int myLimit = 5;
	   
	   String sql = "SELECT asset_id, make, model, asset.asset_type_id, asset_type_desc FROM asset LEFT JOIN asset_type on asset.asset_type_id = asset_type.asset_type_id LIMIT ?, 10";
   	   List<Asset> assets = jdbcTemplate.query(sql, new Object[] {offset}, new AssetRowMapper());
      	return assets;
      	
      	// LIMIT [x], [y] 
      	// LIMIT [offset - at what record number to start retrieving the records from], [limit / # of rows to display]
  	
   }
   
   
      
   // Method to return All Asset Types	
   public List<AssetType> getAllAssetTypes() {
	  	
	   	String sql = "SELECT * FROM asset_type ORDER BY asset_type_desc ASC";
	   	List<AssetType> assetTyleList = jdbcTemplate.query(sql, new AssetTypeRowMapper());
	      	return assetTyleList;
	  	
	   }
	
   	
   	  // Get the Asset Type object / details based on an Asset Type ID submitted
      // is code necessary...??? yes!!!
	  public AssetType getAssetTypeInfo(int assetTypeId) throws EmptyResultDataAccessException {
		 
		 AssetType assetType;
		  
	  
		  try {
			  
			  String sql = "SELECT asset_type_id, asset_type_desc FROM asset_type where asset_type_id = ?";
			  assetType = jdbcTemplate.queryForObject(sql, new Object[] {assetTypeId}, new AssetTypeRowMapper());	
			  
		//	  System.out.println("print: " + assetType.getAssetTypeId());
		//	  System.out.println("print: " + assetType.getAssetTypeName());
			  return assetType;
		  } 
		  
		  catch(Exception e) {
				System.out.println(e);
				assetType = null;
		  }
		  
		  return assetType;
	   }

	
}
