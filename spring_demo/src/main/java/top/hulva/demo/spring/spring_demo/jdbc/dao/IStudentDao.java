package top.hulva.demo.spring.spring_demo.jdbc.dao;

import java.util.List;

import javax.sql.DataSource;

import top.hulva.demo.spring.spring_demo.jdbc.Student;

public interface IStudentDao {
	public void setDataSource(DataSource dataSource);
	
	public void insert(String name, Integer age);
	
	public Student selectStudentWithId(Integer id);
	
	public List<Student> selectStudents();
	
	public void deleteWithId(Integer id);
	
	public void updateAge(Integer id, Integer age);
}
