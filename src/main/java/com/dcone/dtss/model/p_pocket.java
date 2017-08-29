package com.dcone.dtss.model;

public class p_pocket {
	int pid;
	int amount;
	String p_name;
	String actor;
	String tip;
	public int getPid() {
		return pid;
	}
	@Override
	public String toString() {
		return "p_pocket [pid=" + pid + ", amount=" + amount + ", p_name=" + p_name + ", actor=" + actor + ", tip="
				+ tip + "]";
	}
	public void setPid(int pid) {
		this.pid = pid;
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
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
}
