package cn.xidian.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="studentitem")
public class StudentItem {// 学生参加的项目，获奖等

	private Integer stuItemId;// pk

	private Student student;// fk

	private String itemNum;// 项目编号
	private String itemName;// 项目名称
	private String itemUnit;// 主办单位
	private String itemType;// 项目类型
	private String itemGrade;// 项目等级
	private String itemAward;// 获奖情况
	private String itemScore;// 得分
	private String note;//备注
	private String itemState;//项目审核状态
	@Id
	@GeneratedValue
	public Integer getStuItemId() {
		return stuItemId;
	}
	
	@ManyToOne
	@JoinColumn(name="stuId")
	public Student getStudent() {
		return student;
	}
	
	
	public void setStuItemId(Integer stuItemId) {
		this.stuItemId = stuItemId;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getItemNum() {
		return itemNum;
	}
	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemGrade() {
		return itemGrade;
	}
	public void setItemGrade(String itemGrade) {
		this.itemGrade = itemGrade;
	}
	public String getItemAward() {
		return itemAward;
	}
	public void setItemAward(String itemAward) {
		this.itemAward = itemAward;
	}
	public String getItemScore() {
		return itemScore;
	}
	public void setItemScore(String itemScore) {
		this.itemScore = itemScore;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getItemState() {
		return itemState;
	}

	public void setItemState(String itemState) {
		this.itemState = itemState;
	}
	
	
}
