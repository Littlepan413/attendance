import java.util.List;
import java.util.ListIterator;

import cn.hyit.zyy.dao.factory.DAOFactory;

public class TestDAOQuery {

	/**
	 * ���ݿ��������
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args)throws Exception {
		List<Integer> in = DAOFactory.getICourseInfoDAOInstance().findById(10000);
		ListIterator<Integer> iter  = in.listIterator();
		while(iter.hasNext()){
			Integer i = iter.next();
			System.out.println(i.intValue());
		}
	}

}
