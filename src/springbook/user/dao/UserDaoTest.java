package springbook.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
@DirtiesContext
public class UserDaoTest {
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private UserDao dao;
	
	private User user1, user2, user3;
	
	@Before
	public void setUp() {
		//ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		//this.dao = context.getBean("userDao", UserDao.class);
		
		user1 = new User("gyumee", "박성철", "springno1");
		user2 = new User("leegw700", "이길원", "springno2");
		user3 = new User("bumjin", "박범진", "springno3");
	}
	
	@Test
	public void andAndGet() throws ClassNotFoundException, SQLException {
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		User userget1 = dao.get(user1.getId());
		assertThat(userget1.getName(), is(user1.getName()));
		assertThat(userget1.getPassword(), is(user1.getPassword()));

		User userget2 = dao.get(user2.getId());
		assertThat(userget2.getName(), is(user2.getName()));
		assertThat(userget2.getPassword(), is(user2.getPassword()));
	}
	
	@Test
	public void count() throws ClassNotFoundException, SQLException {
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws ClassNotFoundException, SQLException {
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unknown_id");
	}
	
	//@Test(expected=DataAccessException.class)
	@Test
	public void duplicateKey() {
		dao.deleteAll();
		
		dao.add(user1);
		dao.add(user1);
	}
	
	public static void main(String[] args) {
		JUnitCore.main("springbook.user.dao.UserDaoTest");
	}
}
