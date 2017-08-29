package com.dcone.dtss.model;

public class p_trade {
	int tid;
	int pid;
	int wid;
	int volume;
	String tradetime;
	String tip;
	public int getTid() {
		return tid;
	}
	@Override
	public String toString() {
		return "p_trade [tid=" + tid + ", pid=" + pid + ", wid=" + wid + ", volume=" + volume + ", tradetime="
				+ tradetime + ", tip=" + tip + "]";
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public String getTradetime() {
		return tradetime;
	}
	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
}
