package cn.hyit.zyy.dao;

import java.sql.Date;
import java.util.List;

import cn.hyit.zyy.vo.LoginLog;

public interface ILoginLogDAO {

	public boolean doCreate(LoginLog log) throws Exception;

	public List<LoginLog> findAll(int teacherid) throws Exception;

	public LoginLog findByDate(Date date) throws Exception;
}
