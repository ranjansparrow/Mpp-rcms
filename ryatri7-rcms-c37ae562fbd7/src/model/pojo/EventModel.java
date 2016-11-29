package model.pojo;

public class EventModel {
	int event_id;
	String event_title;
	String description;
	int trainer_id;
	int booked_by_id;
	String date;
	String timeFrom;
	String timeTo;
	
	public EventModel(){
		
	}
	
	
//	customized constructor
	public EventModel(int event_id,String event_title,String event_date,String trainer,String status) {
		this.event_id = event_id;
		this.event_title = event_title;
		this.date = event_date;
		this.description = trainer;
		this.timeFrom= status;
	}


	
	

	public int getEvent_id() {
		return event_id;
	}


	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}


	/**
	 * @return the trainer_id
	 */
	public int getTrainer_id() {
		return trainer_id;
	}
	/**
	 * @param trainer_id the trainer_id to set
	 */
	public void setTrainer_id(int trainer_id) {
		this.trainer_id = trainer_id;
	}
	/**
	 * @return the booked_by_id
	 */
	public int getBooked_by_id() {
		return booked_by_id;
	}
	/**
	 * @param booked_by_id the booked_by_id to set
	 */
	public void setBooked_by_id(int booked_by_id) {
		this.booked_by_id = booked_by_id;
	}
	/**
	 * @return the timeFrom
	 */
	public String getTimeFrom() {
		return timeFrom;
	}
	/**
	 * @param timeFrom the timeFrom to set
	 */
	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}
	/**
	 * @return the timeTo
	 */
	public String getTimeTo() {
		return timeTo;
	}
	/**
	 * @param timeTo the timeTo to set
	 */
	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}
	/**
	 * @return the event_title
	 */
	public String getEvent_title() {
		return event_title;
	}
	/**
	 * @param event_title the event_title to set
	 */
	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
}
