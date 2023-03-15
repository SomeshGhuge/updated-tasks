package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Teacher;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer>{

	public Teacher findByemail(String email);
	public String findBypassword(String password);
	public String getBypassword(String password);
	
	@Query( value=" select password from teacher where email=?1", nativeQuery=true)
	public String getPasswordByEmail(String Email);
	
	@Query(value= "select *from teacher where email = ?1 And password = ?2",nativeQuery=true)
	public Teacher getByemailAndpasswordr(String email,String password );
	
	
}
