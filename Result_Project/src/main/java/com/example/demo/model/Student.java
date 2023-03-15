package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	
	@Column(name="hallticketnumber")
	private String hallticketnumber;
	
	@Column(name="birthdateyear")
	private String birthdateyear;
	
	@Column(name="std")
	private String std;
	
	@Column(name="name")
	private String name;
	
	@Column(name="english")
	private String english;
	
	@Column(name="marathi")
	private String marathi;
	
	@Column(name="math")
	private String math;
	
	@Column(name="science")
	private String science;
	
	@Column(name="history")
	private String history;
	
	@Column(name="totatlmarks")
	private String totatlmarks;
	
	@Column(name="result")
	private String result;
	
	
	
	@Column(name="email")
	private String email;

	

	public Student(int id, String std, String hallticketnumber, String name, String english, String marathi,
			String math, String science, String history, String totatlmarks, String result, String birthdateyear,
			String email) {
		
		this.id = id;
		this.std = std;
		this.hallticketnumber = hallticketnumber;
		this.name = name;
		this.english = english;
		this.marathi = marathi;
		this.math = math;
		this.science = science;
		this.history = history;
		this.totatlmarks = totatlmarks;
		this.result = result;
		this.birthdateyear = birthdateyear;
		this.email = email;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getStd() {
		return std;
	}



	public void setStd(String std) {
		this.std = std;
	}



	public String getHallticketnumber() {
		return hallticketnumber;
	}



	public void setHallticketnumber(String hallticketnumber) {
		this.hallticketnumber = hallticketnumber;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEnglish() {
		return english;
	}



	public void setEnglish(String english) {
		this.english = english;
	}



	public String getMarathi() {
		return marathi;
	}



	public void setMarathi(String marathi) {
		this.marathi = marathi;
	}



	public String getMath() {
		return math;
	}



	public void setMath(String math) {
		this.math = math;
	}



	public String getScience() {
		return science;
	}



	public void setScience(String science) {
		this.science = science;
	}



	public String getHistory() {
		return history;
	}



	public void setHistory(String history) {
		this.history = history;
	}



	public String getTotatlmarks() {
		return totatlmarks;
	}



	public void setTotatlmarks(String totatlmarks) {
		this.totatlmarks = totatlmarks;
	}



	public String getResult() {
		return result;
	}



	public void setResult(String result) {
		this.result = result;
	}



	public String getBirthdateyear() {
		return birthdateyear;
	}



	public void setBirthdateyear(String birthdateyear) {
		this.birthdateyear = birthdateyear;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "Student [id=" + id + ", std=" + std + ", hallticketnumber=" + hallticketnumber + ", name=" + name
				+ ", english=" + english + ", marathi=" + marathi + ", math=" + math + ", science=" + science
				+ ", history=" + history + ", totatlmarks=" + totatlmarks + ", result=" + result + ", birthdateyear="
				+ birthdateyear + ", email=" + email + "]";
	}



	public Student() {
		
	}

}