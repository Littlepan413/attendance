package cn.hyit.zyy.dao;

import java.util.List;

import cn.hyit.zyy.vo.ApplyInfo;

;
public interface IApplyInfoDAO {
	/**
	 * 数据增加操作
	 * 
	 * @param apply_info 要增加的数据对象
	 * @return 是否增加成功的标记
	 * @throw Exception 有异常交给被调用处处理
	 */
	public boolean doCreate(ApplyInfo apply) throws Exception;

	/**
	 * 查询全部的数据
	 * 
	 * @param keyWord 查询关键字
	 * @return 返回全部的查询结果，每一个apply对象表示表的一行记录
	 * @throw Exception 有异常交给被调用处处理
	 */
	public List<ApplyInfo> findAll(String keyWord) throws Exception;

	/**
	 * 根据教师编号查询申请的信息
	 * 
	 * @param apply 教师的编号
	 * @return 申请的vo对象
	 * @throw Exception 有异常交给被调用处处理
	 */
	public ApplyInfo findByid(int teacherid) throws Exception;
}
