package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clazz")
public class Clazz{

	private Integer claId;
	private String claName;
	private String claGrade;//班级属于哪一级，冗余，16.5.9新增
	
	@Id
	@GeneratedValue
	public Integer getClaId() {
		return claId;
	}
	public void setClaId(Integer claId) {
		this.claId = claId;
	}
	public String getClaName() {
		return claName;
	}
	public void setClaName(String claName) {
		this.claName = claName;
	}
	public String getClaGrade() {
		return claGrade;
	}
	public void setClaGrade(String claGrade) {
		this.claGrade = claGrade;
	}
	
}
