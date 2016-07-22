package com.potter;

public class PlayerAccount_Entity{
	private int id;    
	private String name;    
	private String emailAdress;    
	private int sex;// 0=man 1=woman
	
	public PlayerAccount_Entity(int id,String name,String emailAdress,int sex){
		this.id=id;
		this.name=name;
		this.emailAdress=emailAdress;
		this.sex=sex;
	}
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailAdress() {
		return emailAdress;
	}
	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
}