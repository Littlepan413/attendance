package cn.hyit.zyy.vo;

import java.sql.Time;
import java.util.Date;
public class CheckinInfo {
	private int studentid;
	private int groupid;
	private int absent;
	private int late;
	private int leftearly;
	private Time time;
	private Date date;

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getAbsent() {
		return absent;
	}

	public void setAbsent(int absent) {
		this.absent = absent;
	}

	public int getLate() {
		return late;
	}

	public void setLate(int late) {
		this.late = late;
	}

	public int getLeftearly() {
		return leftearly;
	}

	public void setLeftearly(int leftearly) {
		this.leftearly = leftearly;
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
}
