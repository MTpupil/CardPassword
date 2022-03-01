package com.mtpupil.pojo;

/**
 * 眼盛星河 心向远方
 * The eyes are full of stars,and the heart yearns for the distance.
 *
 * @author 木瞳
 * on 2022-01-18  13:10
 */

public class User {
	private String username;
	private String password;
	private int type;
	
	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", type=" + type +
				'}';
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public User() {
	}
	
	public User(String username, String password, int type) {
		this.username = username;
		this.password = password;
		this.type = type;
	}
}
