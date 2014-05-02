package cn.hyit.zyy.dao;

import java.util.List;

import cn.hyit.zyy.vo.StudentInfo;

public interface IStudentInfoDAO {

	public boolean doCreate(StudentInfo student) throws Exception;

	public StudentInfo findByName(String name) throws Exception;

	public StudentInfo findById(int studentid) throws Exception;

	public List<StudentInfo> findByClass(String classname) throws Exception;

	public List<String> findClassName() throws Exception;
}
