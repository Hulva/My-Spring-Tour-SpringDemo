package top.hulva.demo.spring.spring_demo.transaction;

import java.util.List;

import javax.sql.DataSource;

public interface IStudentDAO {
	public void setDataSource(DataSource dataSource);
	
	public void insert(String name, Integer age, Integer marks, Integer year);
	
	public List<StudentMarks> listStudents();
}
