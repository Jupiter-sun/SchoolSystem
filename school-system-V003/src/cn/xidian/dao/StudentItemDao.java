package cn.xidian.dao;

import java.util.List;

import cn.xidian.entity.StudentItem;

public interface StudentItemDao {

	boolean deleteById(Integer id);

	boolean add(StudentItem item);

	List<StudentItem> selectByStuNum(String stuNum);

	boolean judgeStuItem(StudentItem item);
}
