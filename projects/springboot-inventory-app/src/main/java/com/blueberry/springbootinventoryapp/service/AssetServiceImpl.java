package com.blueberry.springbootinventoryapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blueberry.springbootinventoryapp.dao.AssetDao;
import com.blueberry.springbootinventoryapp.model.Asset;
import com.blueberry.springbootinventoryapp.model.AssetType;


@Service
public class AssetServiceImpl implements AssetService{
		
	@Autowired
	AssetDao assetDao;
	
	public Asset getAsset(int assetId) {
		return assetDao.getAsset(assetId);
	}
	

	public int addAsset(Asset asset) {
		return assetDao.addAsset(asset);
	}
	
	
	public List<AssetType> getAssetTypes() {
		return assetDao.getAssetTypes();
	}
	
	
	public AssetType getAssetType(int assetTypeId) {
		return assetDao.getAssetType(assetTypeId);
	}
	

	public List<Asset> getAssets() {
		return assetDao.getAssets();
		
	}
	
	
	public List<Asset> getAssetsByOffset(int offset) {
		return assetDao.getAssetsByOffset(offset);
		
	}
	
	
	public int updateAsset(Asset asset) {
		return assetDao.updateAsset(asset);
	}
	
	
	public int deleteAsset(int assetId) {
		return assetDao.deleteAsset(assetId);
	}
	
}
