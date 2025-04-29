package com.EmployeeSystem.model;

import java.io.Serializable;

public class EmployeeSystemModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int empid;
    private String name;
    private String password;
    private int age;
    private String contact;
    private int posid;
    private String position;
    private int deptid;
    private String department;
    private int conid;
    private String conperiod;

    public EmployeeSystemModel() {}

	public EmployeeSystemModel(int empid, String name, String password, int age, String contact, int posid,
			String position, int deptid, String department, int conid, String conperiod) {
		super();
		this.empid = empid;
		this.name = name;
		this.password = password;
		this.age = age;
		this.contact = contact;
		this.posid = posid;
		this.position = position;
		this.deptid = deptid;
		this.department = department;
		this.conid = conid;
		this.conperiod = conperiod;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String string) {
		this.contact = string;
	}

	public int getPosid() {
		return posid;
	}

	public void setPosid(int posid) {
		this.posid = posid;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getConid() {
		return conid;
	}

	public void setConid(int conid) {
		this.conid = conid;
	}

	public String getConperiod() {
		return conperiod;
	}

	public void setConperiod(String conperiod) {
		this.conperiod = conperiod;
	}

    
}

    