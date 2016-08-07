package top.hulva.demo.spring.spring_demo.transaction.declarative;

import java.util.List;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionException;
import top.hulva.demo.spring.spring_demo.transaction.IStudentDAO;
import top.hulva.demo.spring.spring_demo.transaction.StudentMarks;
import top.hulva.demo.spring.spring_demo.transaction.StudentMarksMapper;

public class StudentJDBCTemplate implements IStudentDAO {
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void insert(String name, Integer age, Integer marks, Integer year) {
		try {
			String sql1 = "INSERT INTO Student (name, age) values (?, ?)";
			jdbcTemplateObject.update(sql1, name, age);

			String sql2 = "SELECT MAX(id) FROM Student";
			int studentId = (Integer) jdbcTemplateObject.queryForList(sql2).get(0).get("MAX(id)");

			String sql3 = "INSERT INTO Marks(sid, marks, year) VALUES (?, ?, ?)";
			jdbcTemplateObject.update(sql3, studentId, marks, year);

			System.out.println("创建了一条新纪录： Name = " + name + ", Age = " + age);
			// 模拟一个异常
			//throw new RuntimeException("simulate Error condition") ;

		} catch (DataAccessException e) {
			System.out.println("创建新纪录是出错了，将回滚事务。。。");
			throw e;
		} catch (TransactionException e) {
			System.out.println("创建新纪录是出错了，将回滚事务。。。");
			throw e;
		}
	}

	@Override
	public List<StudentMarks> listStudents() {
		String SQL = "SELECT * FROM Student, Marks where Student.id=Marks.sid";

		List<StudentMarks> studentMarks = jdbcTemplateObject.query(SQL, new StudentMarksMapper());
		return studentMarks;
	}

}
