package com.blueberry.inventorywebapp.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.blueberry.inventorywebapp.model.Asset;
import com.blueberry.inventorywebapp.model.AssetRowMapper;
import com.blueberry.inventorywebapp.model.AssetType;
import com.blueberry.inventorywebapp.model.AssetTypeRowMapper;


@Repository("AssetDao")
public class AssetDaoImpl implements AssetDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {    // "jdbcTemplate" bean will be supplied by the Spring container
	    this.jdbcTemplate = jdbcTemplate;
	}
	
//	@Autowired
//	public AssetDaoImpl (DataSource dataSource) {    
//	    this.jdbcTemplate = new JdbcTemplate(dataSource);
//	}


	// Method to get an Asset
	public Asset getAsset(int assetId) throws EmptyResultDataAccessException {
		
		Asset asset = null;
		
		try {
			
			String sql = "SELECT asset_id, make, model, asset.asset_type_id, asset_type_desc FROM asset JOIN asset_type on asset.asset_type_id = asset_type.asset_type_id\n" +
			  "WHERE asset_id = ?";
			
		//	String sql = "SELECT asset_id, make, model FROM asset WHERE asset_id = ?";
			return this.jdbcTemplate.queryForObject(sql, new Object[] {assetId}, new AssetRowMapper());
		
		
		}  catch(Exception e) {
			System.out.println(e);
			asset = null;
		}
		  return asset;
	}
	
	
   // Method to get All Assets
   public List<Asset> getAssets() {
	   	   
	   String sql = "SELECT asset_id, make, model, asset.asset_type_id, asset_type_desc FROM asset JOIN asset_type on asset.asset_type_id = asset_type.asset_type_id LIMIT 13";
   	   List<Asset> assets = jdbcTemplate.query(sql, new AssetRowMapper());
   	   
   	   
   	   
       return assets;
   }
  
   
	// Method to Add an Asset
    public int addAsset(Asset asset) {
		
		//insert into the database, the fields of the asset object
    	
   
		String sql = "INSERT INTO asset(make, model, asset_type_id) VALUES(?,?,?)";
		return jdbcTemplate.update(sql, new Object[] {asset.getMake(), asset.getModel(), asset.getAssetTypeId()});
	}
	
	
	// Method to Delete an Asset
	public int deleteAsset(int assetId) {
		int result;
		String sql = "DELETE FROM asset WHERE asset_id = ?";
		result = jdbcTemplate.update(sql, new Object[] {assetId});
		return result;
	}
	
	
	// Method to Update an Asset
	public int updateAsset(Asset asset) {
		int result;
		String sql = "UPDATE asset SET make = ?, model = ?, asset_type_id = ? WHERE asset_id = ?";
		result = jdbcTemplate.update(sql, new Object[] {asset.getMake(), asset.getModel(), asset.getAssetTypeId(), asset.getAssetId()});
		return result;
	}
	
	// Method to return All Asset Types	
	   public List<AssetType> getAssetTypes() {
		   
		   String sql = "SELECT * FROM asset_type ORDER BY asset_type_desc ASC";
		   List<AssetType> assetTypeList = jdbcTemplate.query(sql, new AssetTypeRowMapper());
		   
		   return assetTypeList;
	   } 
		
	   	
	  // Get the Asset Type object / details based on an Asset Type ID submitted
	  public AssetType getAssetType(int assetTypeId) throws EmptyResultDataAccessException {
		  
		  AssetType assetType;
	  
		  try {
			  
			  String sql = "SELECT asset_type_id, asset_type_desc FROM asset_type where asset_type_id = ?";
			  assetType = jdbcTemplate.queryForObject(sql, new Object[] {assetTypeId}, new AssetTypeRowMapper());	
			  return assetType;
		  } 
		  
		  catch(Exception e) {
				System.out.println(e);
				assetType = null;
		  }
		  return assetType;
	   }
}
