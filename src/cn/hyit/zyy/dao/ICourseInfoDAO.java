package cn.hyit.zyy.dao;

import java.util.List;

import cn.hyit.zyy.vo.CourseInfo;

public interface ICourseInfoDAO {
	public boolean doCreate(CourseInfo course) throws Exception;

	public List<Integer> findById(int teacherid) throws Exception;

	public CourseInfo findGroup(int teacherid, int subjectid) throws Exception;

	public boolean findByClass(int subjectid, int groupid) throws Exception;

	public List<Integer> findAllGroup(int teacherid, int subjectid)
			throws Exception;
}
