package cn.xidian.service;

import cn.xidian.entity.Student;

public interface StudentService {

	boolean loginValidate(String stuSchNum, String stuPwd);

	Student selectInfBySchNum(String stuSchNum);

	boolean updateInfBySchNum(Student student);
	
	boolean updateGoal(Student student);
	
	boolean modifyPassword(String schNum,String pwd);

}