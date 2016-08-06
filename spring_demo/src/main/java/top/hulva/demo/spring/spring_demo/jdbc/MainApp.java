package top.hulva.demo.spring.spring_demo.jdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans-jdbc.xml");

	      StudentJDBCTemplate studentJDBCTemplate = 
	      (StudentJDBCTemplate)context.getBean("studentJDBCTemplate");
	      
	      System.out.println("------ 添加数据 --------" );
	      studentJDBCTemplate.insert("Zara", 11);
	      studentJDBCTemplate.insert("Nuha", 2);
	      studentJDBCTemplate.insert("Ayan", 15);

	      System.out.println("------ 获取所有数据 --------" );
	      List<Student> students = studentJDBCTemplate.selectStudents();
	      for (Student record : students) {
	         System.out.print("ID : " + record.getId() );
	         System.out.print(", Name : " + record.getName() );
	         System.out.println(", Age : " + record.getAge());
	      }

	      System.out.println("---- 更新id为2的记录 -----" );
	      studentJDBCTemplate.updateAge(2, 20);

	      System.out.println("---- 获取id为2的记录 -----" );
	      Student student = studentJDBCTemplate.selectStudentWithId(2);
	      System.out.print("ID : " + student.getId() );
	      System.out.print(", Name : " + student.getName() );
	      System.out.println(", Age : " + student.getAge());

	}

}
