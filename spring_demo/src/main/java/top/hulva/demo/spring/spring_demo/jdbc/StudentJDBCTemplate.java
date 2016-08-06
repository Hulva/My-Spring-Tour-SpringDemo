package top.hulva.demo.spring.spring_demo.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import top.hulva.demo.spring.spring_demo.jdbc.dao.IStudentDao;

public class StudentJDBCTemplate implements IStudentDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(this.dataSource);
	}

	public void insert(String name, Integer age) {
		String sql = "INSERT INTO STUDENT (name, age) VALUES (?, ?)";
		
		jdbcTemplateObject.update(sql, name, age);
		System.out.println("Created Record : Name = " + name + ", Age = " + age);
		return;
	}

	public Student selectStudentWithId(Integer id) {
		String sql = "SELECT * FROM STUDENT WHERE id = ?";
		Student student = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new StudentMapper());
		return student;
	}

	public List<Student> selectStudents() {
		String sql = "SELECT * FROM STUDNET";
		List<Student> students = jdbcTemplateObject.query(sql, new StudentMapper());
		return students;
	}

	public void deleteWithId(Integer id) {
		String sql = "DELETE FROM STUDENT WHERE id = ?";
		jdbcTemplateObject.update(sql, id);
		System.out.println("Deleted Record: id = " + id);
		return;
	}

	public void updateAge(Integer id, Integer age) {
		String sql = "UPDATE STUDENT SET age = ? where id = ?";
		jdbcTemplateObject.update(sql, age, id);
		System.out.println("Updated Record: id = " + id);
		return;
	}

}
