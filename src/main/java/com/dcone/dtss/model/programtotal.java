package com.dcone.dtss.model;

import com.dcone.dtss.dao.P_PocketDAO;

public class programtotal {
	int pid,rank,amount;
	String p_name,actor,department;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "programtotal [pid=" + pid + ", rank=" + rank + ", amount=" + amount + ", p_name=" + p_name + ", actor="
				+ actor + ", department=" + department + "]";
	}
}
