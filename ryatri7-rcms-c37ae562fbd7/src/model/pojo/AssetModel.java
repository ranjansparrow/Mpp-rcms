package model.pojo;

public class AssetModel {
	int assets_id;
	private int database_id;
	String assets_name;
	int available_qty;
	int actual_qty;
	
	
	public AssetModel(int database_id, String asset_name, int actual_qty,int available_qty) {
		this.assets_id = database_id;
		this.assets_name = asset_name;
		this.available_qty = available_qty;
		this.actual_qty = actual_qty;
	}
	public AssetModel(String asset_name, int available_qty) {
		this.assets_name = asset_name;
		this.available_qty = available_qty;
	}
	
	public AssetModel() {
		// TODO Auto-generated constructor stub
	}

	public int getDatabase_id() {
		return database_id;
	}

	public void setDatabase_id(int database_id) {
		this.database_id = database_id;
	}

	
	/**
	 * @return the assets_id
	 */
	public int getAssets_id() {
		return assets_id;
	}
	/**
	 * @param assets_id the assets_id to set
	 */
	public void setAssets_id(int assets_id) {
		this.assets_id = assets_id;
	}
	/**
	 * @return the assets_name
	 */
	public String getAssets_name() {
		return assets_name;
	}
	/**
	 * @param assets_name the assets_name to set
	 */
	public void setAssets_name(String assets_name) {
		this.assets_name = assets_name;
	}
	/**
	 * @return the available_qty
	 */
	public int getAvailable_qty() {
		return available_qty;
	}
	/**
	 * @param available_qty the available_qty to set
	 */
	public void setAvailable_qty(int available_qty) {
		this.available_qty = available_qty;
	}
	/**
	 * @return the actual_qty
	 */
	public int getActual_qty() {
		return actual_qty;
	}
	/**
	 * @param actual_qty the actual_qty to set
	 */
	public void setActual_qty(int actual_qty) {
		this.actual_qty = actual_qty;
	}
	
	

}
