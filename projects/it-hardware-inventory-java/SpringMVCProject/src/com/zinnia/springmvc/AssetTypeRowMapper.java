package com.zinnia.springmvc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AssetTypeRowMapper implements RowMapper<AssetType> {
	
	
	@Override
	public AssetType mapRow(ResultSet rs, int rowNum) throws SQLException  {
		
		AssetType assetType = new AssetType();  
		assetType.setAssetTypeId(rs.getInt(1));  
		assetType.setAssetTypeName(rs.getString(2));  

        return assetType;  
		
	}
	
}
