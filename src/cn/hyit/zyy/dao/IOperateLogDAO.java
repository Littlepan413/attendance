package cn.hyit.zyy.dao;

import java.sql.Date;
import java.util.List;

import cn.hyit.zyy.vo.OperateLog;

public interface IOperateLogDAO {
	public boolean doCreate(OperateLog log) throws Exception;

	public List<OperateLog> findAll() throws Exception;

	public OperateLog findByDate(Date date) throws Exception;
}
