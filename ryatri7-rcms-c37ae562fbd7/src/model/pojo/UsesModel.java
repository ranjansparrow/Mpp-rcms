package model.pojo;

public class UsesModel {
	int sn;
	int uses_id;
	String member;
	String assets;
	String from_date;
	String to_date;
	String status;
	int statusint;
	
	
	public UsesModel(int sn, int uses_id, String member, String assets, String from_date, String to_date,String status,int statusint) {	
		this.sn = sn;
		this.uses_id = uses_id;
		this.member = member;
		this.assets = assets;
		this.from_date = from_date;
		this.to_date = to_date;
		this.status = status;
		this.statusint = statusint;
	}
	
	
	
	
	public int getStatusint() {
		return statusint;
	}




	public void setStatusint(int statusint) {
		this.statusint = statusint;
	}




	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	public int getUses_id() {
		return uses_id;
	}
	public void setUses_id(int uses_id) {
		this.uses_id = uses_id;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getAssets() {
		return assets;
	}
	public void setAssets(String assets) {
		this.assets = assets;
	}
	public String getFrom_date() {
		return from_date;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	
	

}
