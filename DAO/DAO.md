# DAO

## @Repository
DAO임을 지정한다. DB에 접근하면서 발생하는 DB Exception를 DataAccessException로 바꿔준다.
```java
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.my.project.model.dto.PasswordInfo;
import com.my.project.model.dto.User;
@Mapper
@Repository
public interface UserDAO {
	
	// 회원정보 insert
	boolean insert(User user);
```
## @Mapper
인터페이스와 동일한 이름의 xml을 통해 mapper를 사용한다.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.my.project.model.dao.UserDAO">
	
	<insert id="insert" parameterType="User">
		insert into happy_member (userid, username, userpwd, emailid,emaildomain) 
		values(#{userId},#{userName},#{userPwd},#{emailId},#{emailDomain})
	</insert>
   ............. 생략 ..............  
```

## DAO가 리턴하는 값의 수에따른 xml 설정
```java
	// 회원정보 select
	User select(String userId);
  
  // 회원정보 전부 select
	List<User> selectAll();
```
회원정보를 1개가져오는 경우와 회원정보의 리스트를 가져오는 경우. 
```xml
	<select id="select" parameterType="string" resultType="User">
		select * 
		from happy_member 
		where userid = #{value}
	</select>

	<select id="selectAll" resultType="User">
		select * 
		from happy_member 
		where userid = admin
	</select>
```
resultType은 동일하다. 반환되는 갯수와 상관없이 반환형태만 지정해주면 알아서 처리한다.
