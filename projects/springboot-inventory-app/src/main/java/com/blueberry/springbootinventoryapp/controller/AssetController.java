package com.blueberry.springbootinventoryapp.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.constraints.ISBN;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blueberry.springbootinventoryapp.model.Asset;
import com.blueberry.springbootinventoryapp.model.AssetType;
import com.blueberry.springbootinventoryapp.service.AssetService;

import ch.qos.logback.core.subst.Token.Type;


@Controller
public class AssetController {
	
	@Autowired
	AssetService assetService;
	
	@Autowired
	Asset asset;
	
	
	@GetMapping("/")
	public String hello(Model model) {
		
		return "index";
	}
	
	
	// Method to display "Add an Asset" form
	@GetMapping("/addasset")
	public String addAssetForm(Model model) {
		
		//return all asset types
		List<AssetType> assetTypeList = assetService.getAssetTypes();
		model.addAttribute("assetTypeList",assetTypeList);
	
		return "addasset";

	}
	
	
	
	//Controller method to process "Add an Asset" form
		@PostMapping("/addassetx")
		public String processAddAsset(@RequestParam("make") String make, @RequestParam("model") String modl, @RequestParam("assettype") int assetTypeId, Model model) {
			
			
			
			asset.setMake(make);
			asset.setModel(modl);
			asset.setAssetTypeId(assetTypeId); // the actual "VALUE" of the SELECT drop-down form is retrieved here, not the LABEL that is displayed 
			
	
		
			// Do not create New AssetDao()... already @autowired .. i.e. bean will be provided by Spring container
			int numRowsAffected = assetService.addAsset(asset);
			
			if(numRowsAffected == 1) {
				model.addAttribute("message", "The Asset record was added successfully!");
				
				return "addassetx";
				
			} else {
				
				model.addAttribute("message", "An error occurred adding the Asset.");
				return "addassetx";
			}
		}
	
	
	
	@GetMapping("/getasset")
	public String showGetAssetForm() {
		return "getasset";
	}
	
	
	@GetMapping("/getassetx") 
	public String getAsset(@RequestParam("assetid") int assetId, Model model) {
		
				
		Asset asset = assetService.getAsset(assetId);
		model.addAttribute("asset", asset);
		return "getassetx";

	}
	
	
	// developing pagination .. USE THIS.. WRITE DOWN dEV STEPS..
	
	// Method to display all assets with pagination developed
	@GetMapping("/getassets/{pageId}")
	public String getAllAssets2(@PathVariable("pageId") int pageId, Model model) {

	
		// Retrieve all assets by page from method in AssetDao and return it here
		int offset;
		int numRecordsPerPage = 10;  // should be passed in as an argument to the AsetDAO method below
		
		if(pageId == 1) {
			offset = 0;
		} else {
			 offset = (pageId * numRecordsPerPage) - numRecordsPerPage;
		}
		
		List<Asset> assetsByOffsetList = assetService.getAssetsByOffset(offset);
		
		
		// Get list size of all rows/objects retrieved from query
		List<Asset> allAssetsList = assetService.getAssets();
		int numRecords =  allAssetsList.size();
		
		
		// Calculate number of page links to be displayed
		int numOfPages;
		if (numRecords%numRecordsPerPage != 0) 
			numOfPages = (numRecords/numRecordsPerPage) + 1;
		else numOfPages = (numRecords/numRecordsPerPage);
		
		
		// Create a list to store the page numbers
		ArrayList<Integer> listOfPageNumbers = new ArrayList<>();
		
		
		//Generate page numbers and add them to the array
		for(int i=1; i <= numOfPages; i++) {
			listOfPageNumbers.add(i);
		}
	
		//Send the Asset list to the View
		model.addAttribute("assetsByOffsetList", assetsByOffsetList);
		
		//send number of page links/numbers to the View
		model.addAttribute("numOfPages", numOfPages);  // if you want to display total # of pages in the VIEW e.g. page 1 of 10, 2 of 10 etc.
		
		//Send the list of page numbers to the View
		model.addAttribute("pageNumbersList", listOfPageNumbers);
		
		
		//Generate Navigation links
		//Get NEXT and PREV page IDs
		int nextPageId = pageId + 1;
		int prevPageId = pageId - 1;

		
		//Create a Next_Prev hyperlink list 
		ArrayList<Integer> prevNextList = new ArrayList<>();
		
		if (numRecords <= numRecordsPerPage) {
			
		} else if (pageId > 1 && pageId < numOfPages) {
			
			
			prevNextList.add(pageId - 1);
			prevNextList.add(pageId + 1);
			
		} else if (pageId == 1) {
			prevNextList.add(pageId + 1);
			model.addAttribute("label", "Next");
			
			
		} else {
			prevNextList.add(pageId - 1);
			model.addAttribute("label", "Prev");
		}
		
		//send the Page Number List to the View
		model.addAttribute("prevNextList", prevNextList);
		
		return "getassets";
	}

	
	// Controller method to display "All Assets" page
	@GetMapping("/getassets")
	public String getAllAssets(Model model) {
		
		// retrieve all assets from method in AssetDao and return it here
		List<Asset> allAssetList = assetService.getAssets();
		
		model.addAttribute("allAssetList", allAssetList);
			
		return "getassets";
	}
	
	
	
	/* Method to display "Edit an Asset" page
	 It opens the record for the given id in edit Asset page */
	@GetMapping("/updateasset/{id}")  
	public String showUpdateAssetAssetForm(@PathVariable("id") int id, Model model){  
		
		//get the asset object based on asset ID passed to the edit page and send it to the View
		Asset asset = assetService.getAsset(id);  
		model.addAttribute("asset", asset);
		
		// retrieve asset type ID from Asset
		int assetTypeId = asset.getAssetTypeId();
		
		// get the Asset Type of Asset Type Retrieved
		AssetType assetType =  assetService.getAssetType(assetTypeId);
		
		// send the asset type to view to be "selected" item to display in option/drop-down menu
		model.addAttribute("assetType",assetType);
		
		// get a list of assetType objects and send it to the View  
		List<AssetType>assetTypeList = assetService.getAssetTypes();
		
		model.addAttribute("assetTypeList", assetTypeList);
		
		return "updateasset";
	}
	
	
	@PostMapping("/updateasset/updateassetx")
	public String processUpdateAssetForm(@RequestParam("assetid") int id, @RequestParam("make") String make, @RequestParam("model") String modl, @RequestParam("assettype") int assetTypeId, Model model) {
		
		//get form data to asset bean
		
		asset.setAssetId(id);
		asset.setMake(make);
		asset.setModel(modl);
		asset.setAssetTypeId(assetTypeId);
		
		int result = assetService.updateAsset(asset);
		
		// if valid object returned, add its data to the MODEL, to be passed to the VIEW to be displayed
		
		if(result >= 1) {
			model.addAttribute("message", "The Asset was updated successfully!");
			return "updateassetx";
		
		} else {
			
			model.addAttribute("message", "An error occurred updating the Asset.");
			return "updateassetx";
		}
		
	}
	
	
	@GetMapping("/deleteasset/{id}")
	public String showDeleteAssetForm(@PathVariable("id") int assetId, Model model) {
		
		model.addAttribute("assetid", assetId);
		return "deleteasset";
		
	}
	
	
	@GetMapping("/deleteasset/deleteassetx")
	public String processDeleteAssetForm(@RequestParam("assetid") String assetId, Model model) {
		
		int result = assetService.deleteAsset(Integer.parseInt(assetId));
		
		
		if(result==1) {
			model.addAttribute("message", "The Asset was deleted successfully.");
			return "deleteassetx";
			
		} else {
			
			model.addAttribute("message", "An error occured deleting the asset.");
			return "deleteassetx";
		}
	}
}

