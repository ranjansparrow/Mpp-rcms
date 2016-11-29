package model.pojo;

public class Trainer {
	private int member_id;
	private int database_id;
	private String trainer_name;
	private String specializedIn;
	private String availabiltyTime;
	private String timePeriod;

	public Trainer(int member_id, int database_id, String trainer_name, String specializedIn, String availabiltyTime,
			String timePeriod) {
		this.member_id = member_id;
		this.database_id = database_id;
		this.trainer_name = trainer_name;
		this.specializedIn = specializedIn;
		this.availabiltyTime = availabiltyTime;
		this.timePeriod = timePeriod;
	}
	
	public Trainer(int member_id,  String trainer_name, String specializedIn, String availabiltyTime,
			String timePeriod) {
		this.member_id = member_id;
		this.trainer_name = trainer_name;
		this.specializedIn = specializedIn;
		this.availabiltyTime = availabiltyTime;
		this.timePeriod = timePeriod;
	}

	public int getDatabase_id() {
		return database_id;
	}

	public void setDatabase_id(int database_id) {
		this.database_id = database_id;
	}

	public String getTrainer_name() {
		return trainer_name;
	}

	public void setTrainer_name(String trainer_name) {
		this.trainer_name = trainer_name;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getSpecializedIn() {
		return specializedIn;
	}

	public void setSpecializedIn(String specializedIn) {
		this.specializedIn = specializedIn;
	}

	public String getAvailabiltyTime() {
		return availabiltyTime;
	}

	public void setAvailabiltyTime(String availabiltyTime) {
		this.availabiltyTime = availabiltyTime;
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	@Override
	public String toString() {
		return "Trainer [member_id=" + member_id + ", specializedIn=" + specializedIn + ", availabiltyTime="
				+ availabiltyTime + ", timePeriod=" + timePeriod + "]";
	}

}
