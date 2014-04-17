package cn.hyit.zyy.dao;

import java.util.List;

import cn.hyit.zyy.vo.TeacherInfo;

public interface ITeacherInfoDAO {
	public boolean doCreate(TeacherInfo teacher) throws Exception;

	public List<TeacherInfo> findAll(String keyWrod) throws Exception;

	public TeacherInfo findByUser(String account, String password)
			throws Exception;

	public TeacherInfo findByid(String cardid) throws Exception;
}
