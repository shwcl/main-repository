package com.zinnia.springmvc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class AssetRowMapper implements RowMapper<Asset> {
	
	@Override
	public Asset mapRow(ResultSet rs, int rowNum) throws SQLException  {
		
		Asset asset = new Asset();  
        asset.setAssetId(rs.getInt(1));  
        asset.setMake(rs.getString(2));  
        asset.setModel(rs.getString(3));  
        asset.setAssetTypeId(rs.getInt(4));
        asset.setAssetType(rs.getString(5));

        return asset;  
		
	}
	
}

