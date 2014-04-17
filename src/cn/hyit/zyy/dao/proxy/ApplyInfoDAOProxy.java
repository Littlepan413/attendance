package cn.hyit.zyy.dao.proxy;

import java.util.List;

import cn.hyit.zyy.dao.IApplyInfoDAO;
import cn.hyit.zyy.dao.impl.ApplyInfoDAOimpl;
import cn.hyit.zyy.dbc.DatabaseConnection;
import cn.hyit.zyy.vo.ApplyInfo;

public class ApplyInfoDAOProxy implements IApplyInfoDAO {

	private DatabaseConnection dbc = null;
	private IApplyInfoDAO dao = null;

	public ApplyInfoDAOProxy() throws Exception{
		this.dbc = new DatabaseConnection();
		this.dao = new ApplyInfoDAOimpl(this.dbc.getConnection());
	}
	@Override
	public boolean doCreate(ApplyInfo apply) throws Exception {
		boolean flag = false;
		try {
			if(this.dao.findByid(apply.getTeacherid())==null){
				flag = this.dao.doCreate(apply);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<ApplyInfo> findAll(String keyWord) throws Exception {
		List<ApplyInfo> all = null;
		try {
			all = this.dao.findAll(keyWord);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}

	@Override
	public ApplyInfo findByid(int teacherid) throws Exception {
		ApplyInfo apply = null;
		try {
			apply = this.dao.findByid(teacherid);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return apply;
	}

}
