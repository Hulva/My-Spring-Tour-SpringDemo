## Spring JDBC
* 创建一个XXDAO接口
* 创建一个XXJDBCTemplate类实现XXDAO接口
* 创建实体类和实体类Mapper
* 在配置文件中初始化dataSource以及定义xxJDBCTemplate bean

## 关于 NamedParameterJdbcTemplate 和 SimpleJdbcTemplate
// TODO

## SQL 存储过程
> SimpleJdbcCall 类可用来调用一个存储过程通过IN和OUT参数
```
	DELIMITER $$
	
	DROP PROCEDURE IF EXISTS `TEST`.`getRecord` $$
	CREATE PROCEDURE `TEST`.`getRecord` (
	IN in_id INTEGER,
	OUT out_name VARCHAR(20),
	OUT out_age  INTEGER)
	BEGIN
	   SELECT name, age
	   INTO out_name, out_age
	   FROM Student where id = in_id;
	END $$
	
	DELIMITER ;
```