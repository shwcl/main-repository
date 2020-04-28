package com.blueberry.inventorywebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blueberry.inventorywebapp.model.Asset;
import com.blueberry.inventorywebapp.model.AssetType;
import com.blueberry.inventorywebapp.dao.AssetDao;


@Service
public class AssetServiceImpl implements AssetService {
	
	
	//inject AssetDAO
	@Autowired
	AssetDao assetDao;
	
	
	public Asset getAsset(int assetId) {
		return assetDao.getAsset(assetId);
	}
	

	public List<Asset> getAssets() {
		return assetDao.getAssets();
	}
	
	
	public int addAsset(Asset asset) {
		return assetDao.addAsset(asset);
	}
	
	
	public int deleteAsset(int assetId) {
		return assetDao.deleteAsset(assetId);
	}
	
	
	public int updateAsset(Asset asset) {
		return assetDao.updateAsset(asset);
	}
	
	public List<AssetType> getAssetTypes() {
		return assetDao.getAssetTypes();
	}
	
	public AssetType getAssetType(int assetTypeId) {
		return assetDao.getAssetType(assetTypeId);
	}
	
}
