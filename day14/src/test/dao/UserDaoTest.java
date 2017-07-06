package test.dao;

import org.junit.Test;

import com.github.user.dao.UserDao;
import com.github.user.domain.User;

/**
 * UserDao的测试
 * @author Administrator
 *
 */
public class UserDaoTest {
	@Test
	public void testFindByUsername(){
		UserDao ud =new UserDao();
		User user=new User();
		user=ud.findByUsername("李四");
		System.out.println(user);
	}
	@Test
	public void testAdd(){
		UserDao ud =new UserDao();
		User user=new User();
		user.setUsername("王五");
		user.setPassword("123456");
		ud.add(user);
	}
}
