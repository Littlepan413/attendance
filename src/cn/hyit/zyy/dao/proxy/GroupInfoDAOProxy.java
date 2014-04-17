package cn.hyit.zyy.dao.proxy;

import java.util.List;

import cn.hyit.zyy.dao.IGroupInfoDAO;
import cn.hyit.zyy.dao.impl.GroupInfoDAOimpl;
import cn.hyit.zyy.dbc.DatabaseConnection;
import cn.hyit.zyy.vo.GroupInfo;

public class GroupInfoDAOProxy implements IGroupInfoDAO {

	private DatabaseConnection dbc = null;
	private IGroupInfoDAO dao = null;

	public GroupInfoDAOProxy() throws Exception {
		this.dbc = new DatabaseConnection();
		this.dao = new GroupInfoDAOimpl(this.dbc.getConnection());
	}

	@Override
	public boolean doCreate(GroupInfo group) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findById(group.getGroupid(), group.getStudentid()) == null) {
				flag = this.dao.doCreate(group);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<GroupInfo> findAll(int groupid) throws Exception {
		List<GroupInfo> all = null;
		try {
			all = this.dao.findAll(groupid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return all;
	}

	@Override
	public boolean removeStudent(GroupInfo group) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findById(group.getGroupid(), group.getStudentid()) != null) {
				flag = this.dao.removeStudent(group);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public GroupInfo findById(int groupid, int studentid) throws Exception {
		GroupInfo group = null;
		try {
			group = this.dao.findById(groupid, studentid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return group;
	}

	@Override
	public List<GroupInfo> findByName(String groupname) throws Exception {
		List<GroupInfo> all = null;
		try {
			all = this.dao.findByName(groupname);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}

}
