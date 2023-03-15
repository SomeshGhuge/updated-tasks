package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Student;




@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

	
	public Student findByhallticketnumber(String hallticketnumber);
	public Student findBybirthdateyear(String birthdateyear);
	public Student getByhallticketnumber(String hallticketnumber);
	public Student findByname(String name);
	
	@Query(value= "select *from student where hallticketnumber = ?1 And birthdateyear = ?2",nativeQuery=true)
	public Student getByhallticketnumberAndbirthdateyear(String hallticketnumber,String birthdateyear );
	




}
