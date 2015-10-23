package springbook.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import springbook.user.domain.GroupMember;
import springbook.user.domain.User;

public class GroupMemberDao {

	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<GroupMember> groupMemberMapper =
			new RowMapper<GroupMember>() {
				public GroupMember mapRow(ResultSet rs, int rowNum)
					throws SQLException {
					GroupMember member = new GroupMember();
					member.setId(rs.getInt("id"));
					member.setGroupName(rs.getString("groupName"));
					member.setObjectGuid(rs.getString("objectGuid"));
					member.setMember(rs.getString("member"));
					return member;
				}
	};
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void add(final GroupMember member) {		
		this.jdbcTemplate.update("insert into groupMembers (groupName, objectGuid, member) values (?,?,?)", 
				member.getGroupName(), member.getObjectGuid(), member.getMember());
	}
	
	public void deleteAll() {
		this.jdbcTemplate.update("delete from groupMembers");
	}
	
	public int getCount() {
		return this.jdbcTemplate.queryForInt("select count(*) from groupMembers");
	}
	
	public List<GroupMember> searchGroupName(String groupName) {
		return this.jdbcTemplate.query("select * from groupMembers where groupName = ?", 
				new Object[] {groupName}, this.groupMemberMapper);
	}
}
