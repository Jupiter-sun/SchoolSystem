package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.StudentItem;

public interface StudentItemService {

	boolean deleteById(Integer id);

	boolean add(StudentItem item);

	List<StudentItem> selectByStuNum(String stuNum);
}
