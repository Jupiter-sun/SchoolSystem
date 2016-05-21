package cn.xidian.service;

import java.util.List;

import cn.xidian.entity.GradeCoursePoint;

public interface GradeCoursePointService {

	boolean updateCursPowerByGradeCursPointIdAndGrade(List<GradeCoursePoint> cursPoints,String grade);
	
	List<GradeCoursePoint> selectByPointIdAndGrade(Integer pointId,String grade);
	
	List<GradeCoursePoint> selectByCursIdAndGrade(Integer cursId,String grade);
	
}
