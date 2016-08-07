package top.hulva.demo.spring.spring_demo.jdbc;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import top.hulva.demo.spring.spring_demo.jdbc.dao.IStudentDao;

public class StudentJDBCTemplate1 implements IStudentDao {
	private DataSource dataSource;
	private SimpleJdbcCall jdbcCall;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcCall = new SimpleJdbcCall(this.dataSource).withProcedureName("getRecord");
	}

	@Override
	public void insert(String name, Integer age) {
		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(this.dataSource);
		String SQL = "INSERT INTO Student (name, age) VALUES (?, ?)";

		jdbcTemplateObject.update(SQL, name, age);
		System.out.println("添加纪录： Name = " + name + " Age = " + age);
		return;
	}

	@Override
	public Student selectStudentWithId(Integer id) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
		Map<String, Object> out = jdbcCall.execute(in);

		Student student = new Student();
		student.setId(id);
		student.setName((String) out.get("out_name"));
		student.setAge((Integer) out.get("out_age"));

		return student;
	}

	@Override
	public List<Student> selectStudents() {
		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(this.dataSource);

		String SQL = "SELECT * FROM Student";

		List<Student> students = jdbcTemplateObject.query(SQL, new StudentMapper());
		return students;
	}

	@Override
	public void deleteWithId(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAge(Integer id, Integer age) {
		// TODO Auto-generated method stub

	}

}
