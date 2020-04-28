package com.blueberry.inventorywebapp.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blueberry.inventorywebapp.model.Asset;
import com.blueberry.inventorywebapp.service.AssetService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AssetRestController {
	
	
	@Autowired  // inject AssetService
	AssetService assetService;
	
	
	@GetMapping("/")
	public String getHello() {
		return "hello.. inventory app!";
	}
	
	
	@GetMapping("/assets/{assetId}")
	public Asset getAsset(@PathVariable int assetId) {
		return assetService.getAsset(assetId);
				
	}
	
	
	@GetMapping("/assets")
	public List<Asset> getAssets() {
		return assetService.getAssets();
	}
	
	
	@PostMapping("/assets")
	public String addAsset(@RequestBody Asset asset) {
		int result = assetService.addAsset(asset); 
		if (result == 1) {
			return "The asset was created successfully!";
		}
		
		return "An error occured while adding the asset";
	}
	
	
	@DeleteMapping("/assets/{assetId}")
	public String deleteAsset(@PathVariable int assetId)  {
		assetService.deleteAsset(assetId);
		return "Deleted asset with asset ID: " + assetId; 
	}
	
	
	@PutMapping("/assets")
	public Asset updateAsset(@RequestBody Asset asset) {
		assetService.updateAsset(asset);
		return asset;
	}
	

}
