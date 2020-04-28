package com.blueberry.inventorywebapp.model;

import org.springframework.stereotype.Component;

@Component
public class Asset {
	
	private int assetId;
	private String make;
	private String model;
	private int assetTypeId;
	private String assetType; 

	
	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public int getAssetTypeId() {
		return assetTypeId;
	}

	public void setAssetTypeId(int assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
		
	public int getAssetId() {
		return assetId;
	}
	
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	
	public String getMake() {
		return make;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	
	public Asset() {
	
	}
}
