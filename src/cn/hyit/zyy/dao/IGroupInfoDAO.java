package cn.hyit.zyy.dao;

import java.util.List;

import cn.hyit.zyy.vo.GroupInfo;

public interface IGroupInfoDAO {

	public boolean doCreate(GroupInfo group) throws Exception;

	public List<GroupInfo> findAll(int groupid) throws Exception;

	public boolean removeStudent(GroupInfo group) throws Exception;

	public GroupInfo findById(int groupid, int studentid) throws Exception;

	public List<GroupInfo> findByName(String groupname) throws Exception;
}
