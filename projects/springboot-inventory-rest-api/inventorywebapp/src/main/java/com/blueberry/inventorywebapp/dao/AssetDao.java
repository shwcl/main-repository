package com.blueberry.inventorywebapp.dao;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.blueberry.inventorywebapp.model.Asset;
import com.blueberry.inventorywebapp.model.AssetType;

public interface AssetDao {

	public Asset getAsset(int assetId);
	public List<Asset> getAssets();
	public int deleteAsset(int assetId);
	public int addAsset(Asset asset);
	public int updateAsset(Asset asset);
	public List<AssetType> getAssetTypes();
	public AssetType getAssetType(int assetTypeId);
	

}




