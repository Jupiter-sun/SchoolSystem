package cn.xidian.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.StudentItemDao;
import cn.xidian.entity.StudentItem;

@Component("studentItemDaoImpl")
public class StudentItemDaoImpl implements StudentItemDao{

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public boolean deleteById(Integer id) {
		String hql = "delete from StudentItem s where s.stuItemId=?";
		Query query = currentSession().createQuery(hql).setInteger(0, id);
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean add(StudentItem item) {
		currentSession().save(item);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentItem> selectByStuNum(String stuNum) {
		List<StudentItem> items = new LinkedList<StudentItem>();
		String sql = "from StudentItem s where stuId=(select stuId from Student as stuId where stuSchNum=? and isDelete=1)";
		Query query = currentSession().createQuery(sql).setString(0, stuNum);
		items.addAll(query.list());
		return items;
	}

	@Override
	public boolean judgeStuItem(StudentItem item) {
		// TODO Auto-generated method stub
		String sql = "update StudentItem s set s.itemScore=?,s.itemState=?,s.note=? where s.itemNum=?";
		Query query=currentSession().createQuery(sql);
		query.setString(0, item.getItemScore());
		query.setString(1, item.getItemState());
		query.setString(2, item.getNote());
		query.setString(3, item.getItemNum());
		query.executeUpdate();
		return true;
	}

}
