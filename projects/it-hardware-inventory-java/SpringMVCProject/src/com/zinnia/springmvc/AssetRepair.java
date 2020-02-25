package com.zinnia.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssetRepair {
	
	Asset asset;		// this is a dependency object
	String date;
	int cost;
	
	
	public int getCost() {
		return cost;
	}


	public void setCost(int cost) {
		this.cost = cost;
	}


	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	
	
	public Asset getAsset() {
		return this.asset;
	}
	
		
	public String getDate() {
		return date;
	}
	
		
	public void setDate(String date) {
		this.date = date;
	}
		

	public String repairEntryCreated() {
		
		return "repair entry created";
	}
	
	
	@Autowired
	public AssetRepair(Asset asset) {  // dependency injection here
		this.asset= asset;
	
	}

	
	
	// At DAO level .. we create a method to add a new repair of an asset to the database
	// so we are in receipt of the relevant details that are wrapped in a "Repaired Service" Object created at the Servlet level
	// in JSP all object creation were done at the servelet level, except when we retrieve all USERS in this case we create a new Array List at UserDAO level
	// user same practice/principle in Spring..
	// repair service is a simple POJO.. dont read nothing into it.... what can be do at servelet level with it?? Create a repair, delete a repair, show all repairs, find a repair
	// keep it simple.. objects will be created as needed .. and it is at those point you place the dependency injections / just mirror JSP/Servlet
	// 
	

}
