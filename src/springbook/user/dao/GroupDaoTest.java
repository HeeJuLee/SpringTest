package springbook.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.domain.Group;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
@DirtiesContext
public class GroupDaoTest {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private GroupDao dao;
	
	private Group group1, group2, group3;
	
	@Before
	public void setUp() {
		group1 = new Group(1, "(MAP)리니지 헤이스트 분석", "MAP", "(MAP) 리니지 헤이스트 분석");
		group2 = new Group(1, "개발팀임시", "GameServerMonitoring", "GSM_게임별모니터링");
		group3 = new Group(1, "개발팀임시", "GameServerMonitoring", "GSM_통계");
	}

	@Test
	public void addAndSearch() throws ClassNotFoundException, SQLException {
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(group1);
		dao.add(group2);
		dao.add(group3);
		assertThat(dao.getCount(), is(3));
		
		List<Group> memberList = dao.searchGroupName("개발팀임시");
		assertThat(memberList.size(), is(2));
		
		dao.deleteAll();
	}
	
	@Test
	public void count() throws ClassNotFoundException, SQLException {
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(group1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(group2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(group3);
		assertThat(dao.getCount(), is(3));
		
		dao.deleteAll();
	}
}
