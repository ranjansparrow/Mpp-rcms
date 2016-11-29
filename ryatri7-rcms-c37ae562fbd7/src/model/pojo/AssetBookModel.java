package model.pojo;

public class AssetBookModel {
	int assets_id;
	int member_id;
	String from_time;
	String to_time;
	String Status;
	int assigned_by;
	Double fee;
	
	/**
	 * @return the assigned_by
	 */
	public int getAssigned_by() {
		return assigned_by;
	}
	/**
	 * @param assigned_by the assigned_by to set
	 */
	public void setAssigned_by(int assigned_by) {
		this.assigned_by = assigned_by;
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
	 * @return the member_id
	 */
	public int getMember_id() {
		return member_id;
	}
	/**
	 * @param member_id the member_id to set
	 */
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	/**
	 * @return the from_time
	 */
	public String getFrom_time() {
		return from_time;
	}
	/**
	 * @param from_time the from_time to set
	 */
	public void setFrom_time(String from_time) {
		this.from_time = from_time;
	}
	/**
	 * @return the to_time
	 */
	public String getTo_time() {
		return to_time;
	}
	/**
	 * @param to_time the to_time to set
	 */
	public void setTo_time(String to_time) {
		this.to_time = to_time;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return Status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		Status = status;
	}
	/**
	 * @return the fee
	 */
	public Double getFee() {
		return fee;
	}
	/**
	 * @param fee the fee to set
	 */
	public void setFee(Double fee) {
		this.fee = fee;
	}
	
	
}
