package com.zinnia.springmvc;

import org.springframework.stereotype.Component;

@Component
public class AssetType {
	
	private int assetTypeId;
	private String assetTypeName;
	
	
	public int getAssetTypeId() {
		return assetTypeId;
	}
	public void setAssetTypeId(int assetTypeId) {
		this.assetTypeId = assetTypeId;
	}
	public String getAssetTypeName() {
		return assetTypeName;
	}
	public void setAssetTypeName(String assetType) {
		this.assetTypeName = assetType;
	}

}
