package top.hulva.demo.spring.spring_demo.transaction;

import java.util.List;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class StudentJDBCTemplate implements IStudentDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private PlatformTransactionManager transactionManager;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(this.dataSource);
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Override
	public void insert(String name, Integer age, Integer marks, Integer year) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			String sql1 = "INSERT INTO Student (name, age) values (?, ?)";
			jdbcTemplateObject.update(sql1, name, age);

			String sql2 = "SELECT MAX(id) FROM Student";
			int studentId = (Integer) jdbcTemplateObject.queryForList(sql2).get(0).get("MAX(id)");

			String sql3 = "INSERT INTO Marks(sid, marks, year) VALUES (?, ?, ?)";
			jdbcTemplateObject.update(sql3, studentId, marks, year);

			System.out.println("创建了一条新纪录： Name = " + name + ", Age = " + age);

			transactionManager.commit(status);
		} catch (DataAccessException e) {
			System.out.println("创建新纪录是出错了，将回滚事务。。。");
			transactionManager.rollback(status);
			throw e;
		} catch (TransactionException e) {
			System.out.println("创建新纪录是出错了，将回滚事务。。。");
			transactionManager.rollback(status);
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
