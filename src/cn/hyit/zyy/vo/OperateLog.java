package cn.hyit.zyy.vo;

import java.sql.Time;
import java.sql.Date;

public class OperateLog {
	private int teacherid;
	private String operate;
	private Time time;

	public int getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	private Date date;
}
