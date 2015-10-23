package springbook.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import springbook.user.domain.Group;

public class GroupDao {

private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Group> groupMapper =
			new RowMapper<Group>() {
				public Group mapRow(ResultSet rs, int rowNum)
					throws SQLException {
					Group group = new Group();
					group.setId(rs.getInt("id"));
					group.setGroupName(rs.getString("groupName"));
					group.setToolName(rs.getString("toolName"));
					group.setRoleName(rs.getString("roleName"));
					return group;
				}
	};
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void add(final Group group) {		
		this.jdbcTemplate.update("insert into groups (groupName, toolName, roleName) values (?,?,?)", 
				group.getGroupName(), group.getToolName(), group.getRoleName());
	}
	
	public void deleteAll() {
		this.jdbcTemplate.update("delete from groups");
	}
	
	public int getCount() {
		return this.jdbcTemplate.queryForInt("select count(*) from groups");
	}
	
	public List<Group> searchGroupName(String groupName) {
		return this.jdbcTemplate.query("select * from groups where groupName = ?", 
				new Object[] {groupName}, this.groupMapper);
	}
}
