package model.pojo;

import javafx.beans.property.SimpleStringProperty;

public class User {
	 private final SimpleStringProperty username;
	 private final SimpleStringProperty password;
	 private final SimpleStringProperty address;
	
	
	public User(String username, String password, String addr) {
		this.username = new SimpleStringProperty(username);
		this.password = new SimpleStringProperty(password);;
		this.address = new SimpleStringProperty(addr);;
	}
	public String getUsername() {
		return username.get();
	}
	
	public String getPassword() {
		return password.get();
	}
	
	public String getAddr() {
		return address.get();
	}
	
	
}
