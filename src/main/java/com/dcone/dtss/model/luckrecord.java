package com.dcone.dtss.model;

public class luckrecord {
	int rid;
	int wid;
	int lid;
	int volume;
	String tradetime;
	String tip;
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getWid() {
		return wid;
	}
	@Override
	public String toString() {
		return "luckrecord [rid=" + rid + ", wid=" + wid + ", lid=" + lid + ", volume=" + volume + ", tradetime="
				+ tradetime + ", tip=" + tip + "]";
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
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
