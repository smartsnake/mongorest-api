package com.david.mongorest.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@Document(collection = "invoice")
public class Invoice {
	@Id
	private String id;
	public Invoice setid(String id) {
		this.id = id;
		return this;
	}
	public String getid() {
		return this.id;
	}
	
	private int lookupID;
	public Invoice setLookupID(int lookupID) {
		this.lookupID = lookupID;
		return this;
	}
	public int getLookupID() {
		return this.lookupID;
	}

	private int customerID;
	public Invoice setCustomerID(int customerID) {
		this.customerID = customerID;
		return this;
	}
	public int getCustomerID(){
		return this.customerID;
	}

	private String date;
	public Invoice setDate(String date) {
		this.date = date;
		return this;
	}
	public String getDate(){
		return this.date;
	}

	private int totalCost;
	public Invoice setTotalCost(int cost) {
		this.totalCost = cost;
		return this;
	}
	public int getTotalCost(){
		return this.totalCost;
	}
	
	private int totalSale;
	public Invoice setTotalSale(int totalSale) {
		this.totalSale = totalSale;
		return this;
	}
	public int getTotalSale() {
		return this.totalSale;
	}

	private Item[] items;
	public Invoice setItems(Item[] items) {
		this.items = items;
		return this;
	}
	public Item[] getItems() {
		return this.items;
	}
	
	public Invoice() {
		this.id = new ObjectId().toString();
		this.lookupID = Integer.MAX_VALUE;
		this.customerID = Integer.MAX_VALUE;
		this.date = LocalDate.now().toString();
		this.totalCost = 0;
		this.items = new Item[0];
		this.totalSale = 0;
		for (Item item : this.items) {
			this.totalSale += (item.getSellPrice() * item.getQuantity());
		}
	}

	//Copy contructer
	public Invoice(Invoice b) {
		this.id = b.id;
		this.lookupID = b.lookupID;
		this.customerID = b.customerID;
		this.date = b.date;
		this.totalCost = b.totalCost;
		for (Item item : b.items) {
			this.totalSale += (item.getSellPrice() * item.getQuantity());
		}
		this.items = b.items;
	}

	public Invoice(String id, 
			int lookupID, 
			String date,
			int customerID,
			int totalCost,
            Item[] items
			) {
		this.id = id;
		this.lookupID = lookupID;
		this.customerID = customerID;
		this.date = LocalDate.parse(date).toString();
		this.totalCost = totalCost;
		for (Item item : items) {
			this.totalSale += (item.getSellPrice() * item.getQuantity());
		}
		this.items = items;
	}


	public Invoice loadFromJSON(JSONObject rawJSONObject) {
		this.id = rawJSONObject.optString("id");
		this.lookupID = rawJSONObject.optInt("lookupID");
		this.customerID = rawJSONObject.optInt("customerID");
		this.date = rawJSONObject.optString("date");
		this.totalCost = rawJSONObject.optInt("totalCost");
		this.totalSale = rawJSONObject.optInt("totalSale");
		JSONArray itemJSONArray = rawJSONObject.optJSONArray("items");
		Item[] itemArray = new Item[itemJSONArray.length()];
		if (itemJSONArray.length() > 0) {
			for (int i = 0; i < itemJSONArray.length(); i++) {
				JSONObject itemJSON;
				itemJSON = itemJSONArray.getJSONObject(i);
				Item item = new Item();
				item = item.loadFromJSON(itemJSON);
				itemArray[i] = item;
			}
		}
		this.items = itemArray;
		return this;
	}
	
	//returns JSON object from the invoice.
	public JSONObject createJSONObject() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("id", this.id);
			obj.put("lookupID", Integer.toString(this.lookupID));
			obj.put("customerID", Integer.toString(this.customerID));
			obj.put("date",this.date);
			obj.put("totalCost", Integer.toString(this.lookupID));
			obj.put("totalSale", this.totalSale);
			JSONArray itemsArr = new JSONArray();
			for (Item item : this.items) {
				JSONObject i = item.createJSONObject();
				itemsArr.put(i);
			}
			obj.put("items", itemsArr);
		}catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Invoice))
			return false;
		Invoice b = (Invoice) o;
		boolean isSame = false;
		if(this.id.equals(b.getid()) && this.customerID == b.getCustomerID()
				&& this.lookupID == b.lookupID && this.date.equals(b.getDate())
				&& this.totalCost == b.getTotalCost()) {
			if(this.getItems().length == b.getItems().length) {
				
				if(Arrays.equals(this.getItems(), b.getItems())) {
					isSame = true;
				}
			}
			
		}
		return isSame;
	}
	
	@Override
	public String toString(){
		String invoice = "id: " + this.id + ", lookupID: " + this.lookupID
				+ ", date: " + this.date + ", totalCost: " + this.totalCost
				+ ", Items: {";
		for(Item item: this.items) {
			invoice += item.toString();
		}
		invoice += "}\n";
		
		return invoice;
	}
}
