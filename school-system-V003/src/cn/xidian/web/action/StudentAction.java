package cn.xidian.web.action;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.xidian.entity.Student;
import cn.xidian.entity.StudentActivity;
import cn.xidian.entity.StudentCourse;
import cn.xidian.entity.StudentItem;
import cn.xidian.entity.User;
import cn.xidian.service.StudentActivityService;
import cn.xidian.service.StudentCourseService;
import cn.xidian.service.StudentItemService;
import cn.xidian.service.StudentService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("serial")
@Component(value="StudentAction")
@Scope("prototype")
public class StudentAction extends ActionSupport implements RequestAware{
	private Student s;
	private Map<String, Object> request;
	private String clazz;
	private String password;
	//学生目标
	private String shortGoal;
	private String midGoal;
	private String longGoal;
	//参加活动
	private List<StudentActivity> orgActivities = new LinkedList<StudentActivity>();//组织活动
	private List<StudentActivity> attendActivities = new LinkedList<StudentActivity>();//参与活动
	private List<StudentActivity> socialActivities = new LinkedList<StudentActivity>();//社会实践
	private StudentActivity activity;
	private Integer actId;
	//参与项目及获奖
	private List<StudentItem> items = new LinkedList<StudentItem>();//社会实践
	private StudentItem item;
	private Integer itemId;
	//成绩查询
	private String year;
	private String term;
	private List<StudentCourse> stuCurs;
	
	Map<String,Object> session = ActionContext.getContext().getSession();  
	User tUser = (User)session.get("tUser");
	String schNum = tUser.getSchNum();
	
	private StudentService studentService;
	@Resource(name="studentServiceImpl")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}	
	
	private StudentActivityService studentActivityService;
	@Resource(name="studentActivityServiceImpl")
	public void setStudentActivityService(StudentActivityService studentActivityService) {
		this.studentActivityService = studentActivityService;
	}	
	
	private StudentItemService studentItemService;
	@Resource(name="studentItemServiceImpl")
	public void setStudentItemService(StudentItemService studentItemService) {
		this.studentItemService = studentItemService;
	}	
	
	private StudentCourseService studentCourseService;
	@Resource(name="studentCourseServiceImpl")
	public void setStudentCourseService(StudentCourseService studentCourseService) {
		this.studentCourseService = studentCourseService;
	}	
	
	public String selectBasicByNum(){
		s = studentService.selectInfBySchNum(schNum);
		clazz = s.getClazz().getClaName();
		return "student";
	}
	
	/*修改界面获得学生信息*/
	public String selectInfByNum(){
		s = studentService.selectInfBySchNum(schNum);
		/*awards = teacherService.loadAwardBySchNum(schNum);*/
		return "student";
	}
	
	public String modifyBasicInf(){
		s.setStuSchNum(schNum);
		boolean isSuccess = studentService.updateInfBySchNum(s);
		//s = ssi.selectInfBySchNum(schNum);
		if(isSuccess){
			request.put("Message", "修改成功！");
			return "student";
		}
		else{
			request.put("Message", "对不起，修改失败！");
			return "student";
		}
	}
	
	public String modifyPassword(){
		boolean isSuccess = studentService.modifyPassword(schNum,password);
		s = studentService.selectInfBySchNum(schNum);
		if(isSuccess){
			request.put("Message", "修改密码成功！");
			return "student";
		}
		else{
			request.put("Message", "对不起，修改密码失败！");
			return "student";
		}
	}
	
	public String updateShortGoal(){
		s = studentService.selectInfBySchNum(schNum);
		s.setShortGoal(shortGoal);
		studentService.updateGoal(s);
		request.put("Message", "修改成功！");
		return "student";
	}
	
	public String updateMidGoal(){
		s = studentService.selectInfBySchNum(schNum);
		s.setMidGoal(midGoal);
		studentService.updateGoal(s);
		request.put("Message", "修改成功！");
		return "student";
	}
	
	public String updateLongGoal(){
		s = studentService.selectInfBySchNum(schNum);
		s.setLongGoal(longGoal);
		studentService.updateGoal(s);
		request.put("Message", "修改成功！");
		return "student";
	}
	
	/*学生活动*/
	public String selectActivity(){
		List<StudentActivity> sa = studentActivityService.selectByStuNum(schNum);
		for(int i =0;i<sa.size();i++){
			if(sa.get(i).getType().equals("组织活动")){
				orgActivities.add(sa.get(i));
			}
			if(sa.get(i).getType().equals("参与活动")){
				attendActivities.add(sa.get(i));
			}
			if(sa.get(i).getType().equals("社会实践")){
				socialActivities.add(sa.get(i));
			}
		}
		return "student";
	}
	
	public String deleteActivity(){
		studentActivityService.deleteById(actId);
		request.put("Message", "删除成功！");
		return "stuActList";
	}
	
	public String addActivity(){
		activity.setStudent(studentService.selectInfBySchNum(schNum));
		studentActivityService.add(activity);
		request.put("Message", "添加成功！");
		return "stuActList";
	}
	
	/*学生参与项目及获奖*/
	public String selectItem(){
		items = studentItemService.selectByStuNum(schNum);
		return "student";
	}
	
	public String deleteItem(){
		studentItemService.deleteById(itemId);
		request.put("Message", "删除成功！");
		return "stuItemList";
	}
	
	public String addItem(){
		item.setItemState("待审核");
		item.setStudent(studentService.selectInfBySchNum(schNum));
		studentItemService.add(item);
		request.put("Message", "添加成功！");
		return "stuItemList";
	}
	
	//学生查询成绩
	public String selectStuPer(){
		String currTerm = year + "-" + term;
		stuCurs = studentCourseService.selectByNumTerm(schNum, currTerm);
		if(stuCurs.size()==0){
			request.put("Message", "没有找到相关信息！");
		}
		return "student";
	}
	
	public Student getS() {
		return s;
	}

	public void setS(Student s) {
		this.s = s;
	}

	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public String getShortGoal() {
		return shortGoal;
	}

	public void setShortGoal(String shortGoal) {
		this.shortGoal = shortGoal;
	}

	public String getMidGoal() {
		return midGoal;
	}

	public void setMidGoal(String midGoal) {
		this.midGoal = midGoal;
	}

	public String getLongGoal() {
		return longGoal;
	}

	public void setLongGoal(String longGoal) {
		this.longGoal = longGoal;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
	

	public List<StudentActivity> getOrgActivities() {
		return orgActivities;
	}

	public void setOrgActivities(List<StudentActivity> orgActivities) {
		this.orgActivities = orgActivities;
	}

	public List<StudentActivity> getAttendActivities() {
		return attendActivities;
	}

	public void setAttendActivities(List<StudentActivity> attendActivities) {
		this.attendActivities = attendActivities;
	}

	public List<StudentActivity> getSocialActivities() {
		return socialActivities;
	}

	public void setSocialActivities(List<StudentActivity> socialActivities) {
		this.socialActivities = socialActivities;
	}

	public StudentActivity getActivity() {
		return activity;
	}

	public void setActivity(StudentActivity activity) {
		this.activity = activity;
	}

	
	
	public List<StudentItem> getItems() {
		return items;
	}

	public void setItems(List<StudentItem> items) {
		this.items = items;
	}

	public StudentItem getItem() {
		return item;
	}

	public void setItem(StudentItem item) {
		this.item = item;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public List<StudentCourse> getStuCurs() {
		return stuCurs;
	}

	public void setStuCurs(List<StudentCourse> stuCurs) {
		this.stuCurs = stuCurs;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public String excute(){
		return "student";
	}
}
