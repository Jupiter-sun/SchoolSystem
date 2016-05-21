package cn.xidian.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xidian.dao.CourseDao;
import cn.xidian.dao.TeacherCourseDao;
import cn.xidian.entity.Course;
import cn.xidian.entity.TeacherCourse;
import cn.xidian.service.TeacherCourseService;

@Component("teacherCourseServiceImpl")
public class TeacherCourseServiceImpl implements TeacherCourseService {

	private TeacherCourseDao teacherCourseDao;

	@Resource(name = "teacherCourseDaoImpl")
	public void setTeacherCourseDao(TeacherCourseDao teacherCourseDao) {
		this.teacherCourseDao = teacherCourseDao;
	}

	private CourseDao courseDao;

	@Resource(name = "courseDaoImpl")
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}
	
	@Override
	public List<Course> selectByNumAndTerm(String tchrNum) {
		List<Course> curs = new LinkedList<Course>();
		List<TeacherCourse> tchrCursList = teacherCourseDao.findByNumAndTerm(tchrNum);
		List<Integer> cursId = new LinkedList<Integer>();
		if (tchrCursList != null) {
			if (tchrCursList.size() > 0) {
				for (int i = 0; i < tchrCursList.size(); i++) {
					cursId.add(Integer.parseInt(tchrCursList.get(i).getCourse()
							.getCursId().toString()));
				}
			}
		}
		if(cursId.size()==0)curs=null;
		else curs = courseDao.findById(cursId);
		return curs;
	}

}
