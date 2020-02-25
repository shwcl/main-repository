package com.zinnia.springmvc;

import org.springframework.stereotype.Component;

@Component
public class AssetCategory {
	
	int assetCategoryId;
	String assetCategoryName;
	
	
	public int getAssetCategoryId() {
		return assetCategoryId;
	}
	public void setAssetCategoryId(int assetCategoryId) {
		this.assetCategoryId = assetCategoryId;
	}
	public String getAssetCategoryName() {
		return assetCategoryName;
	}
	public void setAssetCategoryName(String assetCategoryName) {
		this.assetCategoryName = assetCategoryName;
	}
	
	
}
