import java.util.Iterator;
import java.util.List;
import cn.hyit.zyy.dao.factory.DAOFactory;
import cn.hyit.zyy.vo.StudentInfo;

public class TestDAOQuery {

	/**
	 * 数据库操作测试
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args)throws Exception {
		List<StudentInfo> allStudent = null;
		StudentInfo stu = null;
		allStudent = DAOFactory.getIStudentInfoDAOInstance().findByClass("通信1121");
		Iterator<StudentInfo> iter = allStudent.iterator();
		while(iter.hasNext()){
			stu = iter.next();
			System.out.println(stu.getStudentid()+" "+stu.getName()+" "+stu.getClassname());
		}
	}

}
