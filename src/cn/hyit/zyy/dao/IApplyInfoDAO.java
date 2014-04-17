package cn.hyit.zyy.dao;

import java.util.List;

import cn.hyit.zyy.vo.ApplyInfo;

;
public interface IApplyInfoDAO {
	/**
	 * �������Ӳ���
	 * 
	 * @param apply_info Ҫ���ӵ����ݶ���
	 * @return �Ƿ����ӳɹ��ı��
	 * @throw Exception ���쳣���������ô�����
	 */
	public boolean doCreate(ApplyInfo apply) throws Exception;

	/**
	 * ��ѯȫ��������
	 * 
	 * @param keyWord ��ѯ�ؼ���
	 * @return ����ȫ���Ĳ�ѯ�����ÿһ��apply�����ʾ���һ�м�¼
	 * @throw Exception ���쳣���������ô�����
	 */
	public List<ApplyInfo> findAll(String keyWord) throws Exception;

	/**
	 * ���ݽ�ʦ��Ų�ѯ�������Ϣ
	 * 
	 * @param apply ��ʦ�ı��
	 * @return �����vo����
	 * @throw Exception ���쳣���������ô�����
	 */
	public ApplyInfo findByid(int teacherid) throws Exception;
}
