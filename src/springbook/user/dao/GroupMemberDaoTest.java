package springbook.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.domain.GroupMember;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
@DirtiesContext
public class GroupMemberDaoTest {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private GroupMemberDao dao;
	
	private GroupMember member1, member2, member3;
	
	@Before
	public void setUp() {
		member1 = new GroupMember(1,"리니지개발팀_L1Admin", "ca164472-87a8-4912-bb22-8e9851c77373", "박아리나(Arina Park)");
		member2 = new GroupMember(2,"B&S큐레이션운영", "317a58c0-7ff8-4378-b5a8-743357da6d12", "최수정");
		member3 = new GroupMember(3,"B&S큐레이션운영", "317a58c0-7ff8-4378-b5a8-743357da6d12", "이종훈");
	}
	
	@Test
	public void addAndSearch() throws ClassNotFoundException, SQLException {
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(member1);
		dao.add(member2);
		dao.add(member3);
		assertThat(dao.getCount(), is(3));
		
		List<GroupMember> memberList = dao.searchGroupName("B&S큐레이션운영");
		assertThat(memberList.size(), is(2));
		
		dao.deleteAll();		
	}
	
	@Test
	public void count() throws ClassNotFoundException, SQLException {
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(member1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(member2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(member3);
		assertThat(dao.getCount(), is(3));
		
		dao.deleteAll();
	}
	
}
