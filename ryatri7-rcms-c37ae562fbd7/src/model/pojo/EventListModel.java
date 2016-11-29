package model.pojo;

public class EventListModel {
	int sn;
	int event_id;
	String event_title;
	String event_desc;
	String trained_by;
	String date;
	String timefrom;
	String timeTo;
	
	
	public int getSn() {
		return sn;
	}
	public EventListModel(int sn, int event_id, String event_title, String event_desc, String trained_by, String date,
			String timefrom, String timeTo) {
		this.sn = sn;
		this.event_id = event_id;
		this.event_title = event_title;
		this.event_desc = event_desc;
		this.trained_by = trained_by;
		this.date = date;
		this.timefrom = timefrom;
		this.timeTo = timeTo;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public String getEvent_title() {
		return event_title;
	}
	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}
	public String getEvent_desc() {
		return event_desc;
	}
	public void setEvent_desc(String event_desc) {
		this.event_desc = event_desc;
	}
	public String getTrained_by() {
		return trained_by;
	}
	public void setTrained_by(String trained_by) {
		this.trained_by = trained_by;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTimefrom() {
		return timefrom;
	}
	public void setTimefrom(String timefrom) {
		this.timefrom = timefrom;
	}
	public String getTimeTo() {
		return timeTo;
	}
	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}
	@Override
	public String toString() {
		return "EventListModel [sn=" + sn + ", event_id=" + event_id + ", event_title=" + event_title + ", event_desc="
				+ event_desc + ", trained_by=" + trained_by + ", date=" + date + ", timefrom=" + timefrom + ", timeTo="
				+ timeTo + "]";
	}
	
	

}
