package cn.xidian.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.xidian.dao.MajorTargetValueDao;
import cn.xidian.entity.MajorTargetValue;

@Component("majorTargetValueDaoImpl")
public class MajorTargetValueDaoImpl implements MajorTargetValueDao{

	private SessionFactory sessionFactory;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public MajorTargetValue selectByPointAndTerm(String currentTerm,
			Integer pointId) {
		MajorTargetValue mtv = new MajorTargetValue();
		String sql = "from MajorTargetValue where point.indPointId=? and term=?";
		Query query = currentSession().createQuery(sql).setInteger(0,
				pointId).setString(1, currentTerm);
		mtv = (MajorTargetValue)query.uniqueResult();
		return mtv;
	}

	@Override
	public boolean add(MajorTargetValue mtv) {
		currentSession().save(mtv);
		return true;
	}

	@Override
	public boolean delete(MajorTargetValue mtv) {
		currentSession().delete(mtv);
		return false;
	}

}
