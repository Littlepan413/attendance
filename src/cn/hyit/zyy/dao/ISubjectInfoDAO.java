package cn.hyit.zyy.dao;

import cn.hyit.zyy.vo.SubjectInfo;

public interface ISubjectInfoDAO {
	public boolean doCreate(SubjectInfo subject) throws Exception;

	public SubjectInfo findById(int subjectid) throws Exception;

	public SubjectInfo findByName(String subjectname) throws Exception;
}
