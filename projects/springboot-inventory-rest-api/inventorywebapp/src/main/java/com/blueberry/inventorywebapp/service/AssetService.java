package com.blueberry.inventorywebapp.service;

import java.util.List;

import com.blueberry.inventorywebapp.model.Asset;
import com.blueberry.inventorywebapp.model.AssetType;

public interface AssetService {
	
	public Asset getAsset(int assetId);
	public List<Asset> getAssets();
	public int addAsset(Asset asset);
	public int deleteAsset(int assetId);
	public int updateAsset(Asset asset);
	public List<AssetType> getAssetTypes();
	public AssetType getAssetType(int assetTypeId);

}
