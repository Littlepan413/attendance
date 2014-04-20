import java.util.List;
import java.util.ListIterator;

import cn.hyit.zyy.dao.factory.DAOFactory;
import cn.hyit.zyy.vo.CourseInfo;
import cn.hyit.zyy.vo.GroupInfo;

public class TestDAOQuery {

	/**
	 * Êý¾Ý¿â²Ù×÷²âÊÔ
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args)throws Exception {
		/*List<Integer> in = DAOFactory.getICourseInfoDAOInstance().findAllGroup(10086, 1000000004);
		ListIterator<Integer> iter  = in.listIterator();
		while(iter.hasNext()){
			Integer i = iter.next();
			System.out.println(i.intValue());
		}*/
		DAOFactory.getICourseInfoDAOInstance().findAllGroup(1, 1);
	}

}
