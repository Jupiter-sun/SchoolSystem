<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>学生参与项目页面</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.css" />
<link rel="stylesheet" href="css/common.css" />
<link rel="stylesheet" href="css/student_goal.css" />
<script type="text/javascript" src="js/jquery1.12.1.js"></script>

<script type="text/javascript">
	var msg = "${requestScope.Message}";
	if (msg != "") {
		alert(msg);
	}
<%request.removeAttribute("Message");%>
	//显示后将request里的Message清空，防止回退时重复显示。

	function isEmpty() {
		var time = document.getElementById("input-time");
		var name = document.getElementById("input-name");
		var unit = document.getElementById("input-unit");
		var duty = document.getElementById("input-duty");

		if (time.value.toString().trim().length != 0
				&& name.value.toString().trim().length != 0
				&& unit.value.toString().trim().length != 0
				&& duty.value.toString().trim().length != 0) {
			return true;
		} else {
			if (time.value.toString().trim().length == 0) {
				time.focus();
			}
			if (name.value.toString().trim().length == 0) {
				name.focus();
			}
			if (unit.value.toString().trim().length == 0) {
				unit.focus();
			}
			if (duty.value.toString().trim().length == 0) {
				duty.focus();
			}
			alert("输入框不能为空！");
			return false;
		}
	}
</script>
</head>

<body>
	<%@ include file="/include/header.jsp"%>
	<%@ include file="/include/student_main_nav.jsp"%>
	<div class="content">
		<div class="container">
			<div class="row">
				<%@ include file="/include/stuLeftBar.jsp"%>
				<div class="span9">
					<div class="row">
						<div class="span9 div-content-white-bgr">
							<div class="div-inf-bar">
								<label>学生参与项目</label>
							</div>
							<div class="div-inf-tbl">
								<table class="table table-bordered table-condensed">
									<thead>
										<tr>
											<th>项目编号</th>
											<th>项目名称</th>
											<th>主办单位</th>
											<th>项目类别</th>
											<th>项目等级</th>
											<th>获奖情况</th>
											<th>审核状态</th>
											<th>得分</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="items" var="i">
											<tr>
												<td><s:property value="#i.itemNum" /></td>
												<td><s:property value="#i.itemName" /></td>
												<td><s:property value="#i.itemUnit" /></td>
												<td><s:property value="#i.itemType" /></td>
												<td><s:property value="#i.itemGrade" /></td>
												<td><s:property value="#i.itemAward" /></td>
												<td><s:property value="#i.itemState" /></td>
												<td><s:property value="#i.itemScore" /></td>
												<td><a onclick="return confirm('确认删除？')"
													href="Student_Portfolio_Activity_deleteItem?itemId=<s:property value="#i.stuItemId"/>">删除</a></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
								<label class="lable-add" data-toggle="modal"
									data-target="#myModal">添加</label>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp"%>
	
	<!-- 模态框，用于添加参与活动信息 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" style="display:none">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h5 class="modal-title">添加项目信息</h5>
				</div>
				<div class="modal-body">
					<form action="Student_Award_1_addItem" method="post"
						class="form-horizontal form-add" enctype="multipart/form-data"
						onsubmit="javascript:return isEmpty(1)">
						<div class="control-group">
							<label class="control-label">项目编号：</label>
							<div class="controls">
								<input id="input-time" type="text" name="item.itemNum">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">项目名称：</label>
							<div class="controls">
								<input id="input-name" type="text" name="item.itemName">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">主办单位：</label>
							<div class="controls">
								<input id="input-name" type="text" name="item.itemUnit">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">项目类别：</label>
							<div class="controls">
								<select id="input-type" name="item.itemType">
									<option value="项目">项目</option>
									<option value="竞赛">竞赛</option>
									<option value="论文">论文</option>
									<option value="著作">著作</option>
									<option value="专利/软著">专利/软著</option>
									<option value="技能证书">技能证书</option>
									<option value="社会实践、见习、公益活动">社会实践、见习及公益活动</option>
									<option value="竞赛">竞赛</option>
									<option value="其他">其他</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">项目等级：</label>
							<div class="controls">
								<select id="input-type" name="item.itemGrade">
									<option value="国际级">国际级</option>
									<option value="国家级">国家级</option>
									<option value="省级">省级</option>
									<option value="校级">校级</option>
									<option value="SCI检索论文">SCI检索论文</option>
									<option value="EI检索论文">EI检索论文</option>
									<option value="核心期刊论文">"核心期刊论文"</option>
									<option value="普通刊物及会议论文">普通刊物及会议论文</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">获奖情况：</label>
							<div class="controls">
								<input id="input-duty" type="text" name="item.itemAward">
							</div>
							<input type="text" name="item.itemScore" value="0"
								class="hidden">
						</div>
						<div class="div-btn">
							<input type="submit" value="提交" class="btn">
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<!-- 模态框，用于添加参与活动信息完 -->
	
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script>
		$(function() {
			$(".container").css("min-height",
					$(document).height() - 90 - 88 - 41 + "px");//container的最小高度为“浏览器当前窗口文档的高度-header高度-footer高度”
		});
	</script>
</body>
</html>
