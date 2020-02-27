package com.zinnia.springmvc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xpath.internal.operations.And;




@Controller
public class HomeController {
	
	@Autowired    // dependency injection.. spring managed bean.. no need to "new up" a bean below
	AssetDao assetDao;
	
	@Autowired	//  dependency injection.. spring managed bean.. no need to "new up" a bean below
	Asset asset;
	

	@RequestMapping("/")
	public String showPage() {
		return "main-menu";
	}
	
	
	
	// developing pagination .. USE THIS.. WRITE DOWN dEV STEPS..
	
	// Method to display all assets with pagination developed
		@RequestMapping(value="/viewassets/{pageId}")
		public String getAllAssets2(@PathVariable("pageId") int pageId, Model model) {

		
			// Retrieve all assets by page from method in AssetDao and return it here
			int offset;
			int numRecordsPerPage = 10;  // should be passed in as an argument to the AsetDAO method below
			
			if(pageId == 1) {
				offset = 0;
			} else {
				 offset = (pageId * numRecordsPerPage) - numRecordsPerPage;
			}
			
			List<Asset> allAssetsByPageList = assetDao.getAllAssetsByPage(offset);

			
			// Get list size of all rows/objects retrieved from query
			List<Asset> allAssetsList = assetDao.getAllAssets();
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
			model.addAttribute("allAssetsByPageList", allAssetsByPageList);
			
			//send number of page links/numbers to the View
			model.addAttribute("numOfPages", numOfPages);  // if you want to display total # of pages in the VIEW e.g. page 1 of 10, 2 of 10 etc.
			
			//Send the list of page numbers to the View
			model.addAttribute("pageNumbersList", listOfPageNumbers);
			
			
			//Generate Navigation links
			//Get NEXT and PREV page IDs
			int nextPageId = pageId + 1;
			int prevPageId = pageId - 1;
	
			
			//Create a Next_Prev hyperlink list 
			ArrayList<String> prevNextList = new ArrayList<>();
			
			if (numRecords <= numRecordsPerPage) {
				
			} else if (pageId > 1 && pageId < numOfPages) {
				prevNextList.add("<a href=" + (pageId - 1) + ">PREV_Page</a>");
				prevNextList.add("<a href=" + (pageId + 1) + ">NEXT_Page</a>");
				
			} else if (pageId == 1) {
				prevNextList.add("<a href=" + (pageId + 1) + ">NEXT_Page</a>");
				
			} else {
				prevNextList.add("<a href=" + (pageId - 1) + ">PREV_Page</a>");
			}
			
			//send the Page Number List to the View
			model.addAttribute("prevNextList", prevNextList);
			
			return "viewassets";
		}

		
	// Controller method to display "All Assets" page
	@RequestMapping("/getallassets")
	public String getAllAssets(Model model) {
		
		// retrieve all assets from method in AssetDao and return it here
		List<Asset> allAssetList = assetDao.getAllAssets();
		
		model.addAttribute("allAssetList", allAssetList);
			
		return "allassets";
	}
	
	
	/* Method to display "Edit an Asset" page
	 It opens the record for the given id in edit Asset page */
	@RequestMapping("/editasset/{id}")  
	public String showEditAssetForm(@PathVariable("id") int id, Model model){  
		
		//get the asset object based on asset ID passed to the edit page and send it to the View
		Asset asset=assetDao.getAssetById(id);  
		model.addAttribute("asset", asset);
		
		//retrieve asset type ID from Asset
		int assetTypeId = asset.getAssetTypeId();
		
		//Get the Asset Type of Asset Type Retrieved
		AssetType assetType = assetDao.getAssetTypeInfo(assetTypeId);
		
		//Send the asset type to view to be "selected" item to display in option/drop-down menu
		model.addAttribute("assetType",assetType);
		
		//Get a list of assetType objects and send it to the View  
		List<AssetType>assetTypeList = assetDao.getAllAssetTypes();
		
		model.addAttribute("assetTypeList",assetTypeList);
		
		return "editasset";
	}
	
	
	@RequestMapping("editasset/editassetx")
	public String processEditAssetForm(HttpServletRequest request, Model model) {
		
		//get form data
		int assetId = Integer.parseInt(request.getParameter("assetid"));
		String make = request.getParameter("make");
		String modl = request.getParameter("model");
		 
		// the actual "VALUE" of the SELECT drop-down form is retrieved here, not the LABEL that is displayed 
		int assetTypeId = Integer.parseInt(request.getParameter("assettype")); 
		
		
		// Add data to Asset bean
		asset.setAssetId(assetId);
		asset.setMake(make);
		asset.setModel(modl);
		asset.setAssetTypeId(assetTypeId);
		
		int result = assetDao.updateAsset(asset);
		
		// if valid object returned, add its data to the MODEL, to be passed to the VIEW to be displayed
		if(result >= 1) {
			model.addAttribute("message", "The Asset was updated successfully");
			return "editassetx";
		
		} else {
			model.addAttribute("pageTitle", "Updating an Asset");
			model.addAttribute("errorMsg", "An error occurred updating Asset ID: " + assetId);
			
			return "errorpage";
		}
	}
	
		
	/* Method to display "Delete an Asset" page
	 It opens the record for the given id in Delete Asset page */
	@RequestMapping("/deleteasset/{id}")  
	public String showDeleteAssetForm(@PathVariable("id") int id, Model model){  
		
		// Get the asset object based on asset ID passed to the edit page and send it to the View
		Asset asset=assetDao.getAssetById(id);  
		model.addAttribute("asset", asset);
		
		
		// Retrieve asset type ID from Asset
		int assetTypeId = asset.getAssetTypeId();
		
		// Get the Asset Type of Asset Type ID retrieved
		AssetType assetType = assetDao.getAssetTypeInfo(assetTypeId);
		
		// Send the Asset Type to view to be displayed
		model.addAttribute("assetType",assetType);
		
		return "deleteasset";
	}
	
	
	@RequestMapping("deleteasset/deleteassetx")
	public String processDeleteAssetForm(HttpServletRequest request, Model model) {

		
		//get form data
		int assetId = Integer.parseInt(request.getParameter("assetid"));
	//	String make = request.getParameter("make");
	//	String modl = request.getParameter("model");
		 
		// the actual "VALUE" of the SELECT drop-down form is retrieved here, not the LABEL that is displayed 
	//	int assetTypeId = Integer.parseInt(request.getParameter("assettype")); 
		
		
		// Add data to Asset bean
		asset.setAssetId(assetId);
	//	asset.setMake(make);
	//	asset.setModel(modl);
	//	asset.setAssetTypeId(assetTypeId);
		
		int result = assetDao.deleteAsset(asset);

		
		// if valid object returned, add its data to the MODEL to be passed to the VIEW to be displayed
		if(result >= 1) {
			model.addAttribute("message", "The Asset was deleted successfully");
			return "deleteassetx";
		
		} else {
			model.addAttribute("pageTitle", "Updating an Asset");
			model.addAttribute("errorMsg", "An error occurred deleting Asset ID: " + assetId);
			
			return "errorpage";
		}
	}
	
	
	
	// Method to display "Search for an Asset" page
	@RequestMapping(value = "/getasset", method = RequestMethod.GET)
	public String showGetAssetForm() {
		return "getasset";
	}
	

	// Method to process "Search for an Asset" page 
	@RequestMapping("/getassetx")
	public String getAsset(HttpServletRequest request, Model model) {
		
		
		// retrieve asset ID from the form data
		int assetId = Integer.parseInt(request.getParameter("assetid"));
		
		
		// Pass asset Id retrieved to the AssetDao to process to get the corresponding Asset object
		Asset asset = assetDao.getAssetById(assetId);
		
		// Get assetType ID of the Asset
		int assetTypeId = asset.getAssetTypeId();
		
		// Get asset type object to retrieve the "asset type" name
		AssetType assetType = assetDao.getAssetTypeInfo(assetTypeId);
		
		// if valid object returned, add its data to the MODEL, to be passed to the VIEW to be displayed
		if (asset != null && assetType != null) {
			
			model.addAttribute("assetid", asset.getAssetId());
			model.addAttribute("make", asset.getMake());
			model.addAttribute("model", asset.getModel());
			model.addAttribute("type", assetType.getAssetTypeName());
			
			return "getassetx";
		
		} else {
			return "getasset-noresult";
		}
	}
	
	
	// Method to show "Add an Asset" form
	@RequestMapping("/addasset")
	public String showAddAssetForm(Model model) {
		
		//return all asset types
		List<AssetType>assetTypeList = assetDao.getAllAssetTypes();
		model.addAttribute("assetTypeList",assetTypeList);
		
		return "addasset";
	}
	
	
	//Controller method to process "Add an Asset" form
	@RequestMapping("/addassetx")
	public String processAddAsset(HttpServletRequest request, Model model) {

		
		// read form data
		String make = request.getParameter("make");
		String mod = request.getParameter("model");
		
		// the actual "VALUE" of the SELECT drop-down form is retrieved here, not the LABEL that is displayed 
		int assetTypeId = Integer.parseInt(request.getParameter("assettype"));
		
		// do not create new Asset().. already @autowired.. i.e. bean will be provided by Spring container
		// get "asset" Bean from Spring 
		asset.setMake(make);
		asset.setModel(mod);
		asset.setAssetTypeId(assetTypeId);
		
		
		// Assign the returned "assetType" object to a variable
		AssetType assetType = assetDao.getAssetTypeInfo(assetTypeId);
			
		// Do not create New AssetDao()... already @autowired .. i.e. bean will be provided by Spring container
		int numRowsAffected = assetDao.addAsset(asset);
		
		if(numRowsAffected >=1) {
			model.addAttribute("message", "An Asset record was added with the following details: ");
			
			//add message to the model
			model.addAttribute("make", make);
			model.addAttribute("model", mod);
			model.addAttribute("assetType", assetType.getAssetTypeName());
			
			return "addassetx";
			
		} else {
			
			model.addAttribute("pageTitle", "Add an Asset");
			model.addAttribute("errorMsg", "An error occurred adding the Asset");
			return "errorpage";
		}
	}
}
