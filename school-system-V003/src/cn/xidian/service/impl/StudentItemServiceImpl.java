package cn.xidian.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.StudentItemDao;
import cn.xidian.entity.StudentItem;
import cn.xidian.service.StudentItemService;

@Component("studentItemServiceImpl")
public class StudentItemServiceImpl implements StudentItemService {

	private StudentItemDao studentItemDao;

	@Resource(name = "studentItemDaoImpl")
	public void setStudentItemDao(StudentItemDao studentItemDao) {
		this.studentItemDao = studentItemDao;
	}

	@Override
	public boolean deleteById(Integer id) {
		return studentItemDao.deleteById(id);
	}

	@Override
	public boolean add(StudentItem item) {
		return studentItemDao.add(item);
	}

	@Override
	public List<StudentItem> selectByStuNum(String stuNum) {
		return studentItemDao.selectByStuNum(stuNum);
	}

}
