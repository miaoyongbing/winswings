package com.dcone.dtss.model;

public class luck {
	int lid;
	int amount;
	String tip;
	public int getLid() {
		return lid;
	}
	@Override
	public String toString() {
		return "luck [lid=" + lid + ", amount=" + amount + ", tip=" + tip + "]";
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
}
