package com.blueberry.springbootinventoryapp.dao;

import java.util.List;

import com.blueberry.springbootinventoryapp.model.Asset;
import com.blueberry.springbootinventoryapp.model.AssetType;

public interface AssetDao {
			
	public Asset getAsset(int assetId);
	public List<Asset> getAssets();
	public List<Asset> getAssetsByOffset(int offset);
	public int deleteAsset(int assetId);
	public int addAsset(Asset asset);
	public int updateAsset(Asset asset);
	public List<AssetType> getAssetTypes();
	public AssetType getAssetType(int assetTypeId);

}
