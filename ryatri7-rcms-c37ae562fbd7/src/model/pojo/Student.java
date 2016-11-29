package model.pojo;

import javafx.beans.property.SimpleStringProperty;

public class Student {
	private final SimpleStringProperty username;
	private final SimpleStringProperty password;
	private final SimpleStringProperty address;
	public Student(String username, String password, String address) {
		this.username = new SimpleStringProperty (username);
		this.password = new SimpleStringProperty(password);
		this.address = new SimpleStringProperty(address);
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username.get();
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password.get();
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address.get();
	}
	
}
