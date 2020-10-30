package com.io.choozo.model.dataModel.todayDealsModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.io.choozo.model.dataModel.todayDealsModel.Size;

public class AttributesItem{

	@SerializedName("attributeId")
	private int attributeId;

	@SerializedName("sizeId")
	private int sizeId;

	@SerializedName("unit")
	private String unit;

	@SerializedName("createdDate")
	private String createdDate;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("productId")
	private int productId;

	@SerializedName("size")
	private Size size;

	@SerializedName("price")
	private String price;

	@SerializedName("colorId")
	private Object colorId;

	@SerializedName("description")
	private Object description;

	@SerializedName("colors")
	private List<Object> colors;

	@SerializedName("status")
	private Object status;

	public int getAttributeId(){
		return attributeId;
	}

	public int getSizeId(){
		return sizeId;
	}

	public String getUnit(){
		return unit;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public int getQuantity(){
		return quantity;
	}

	public int getProductId(){
		return productId;
	}

	public Size getSize(){
		return size;
	}

	public String getPrice(){
		return price;
	}

	public Object getColorId(){
		return colorId;
	}

	public Object getDescription(){
		return description;
	}

	public List<Object> getColors(){
		return colors;
	}

	public Object getStatus(){
		return status;
	}
}