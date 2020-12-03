<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title></title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/studentAdd.js"></script>
</head>
<body>
	<!-- 顶栏 -->
	<jsp:include page="top.jsp"></jsp:include>
	<!-- 中间主体 -->
		<div class="container" id="content">
		<div class="row">
			<jsp:include page="menu.jsp"></jsp:include>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 style="text-align: center;">添加学生信息</h1>
						</div>
				    </div>
					<!--这里的name要和实体类的属性一致，才可以自动匹配-->
				    <div class="panel-body">
						<form class="form-horizontal" role="form" action="/admin/addStudent" id="editfrom" method="post">
							  <div class="form-group">
							    <label for="userID" class="col-sm-2 control-label">学号</label>
							    <div class="col-sm-7 has-feedback">
							      <input type="number" class="form-control" id="userID" name="userID" placeholder="请输入学号">
									<span id="info-panel"></span>
									<span id="userInfo"></span>
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="userName" class="col-sm-2 control-label">姓名</label>
							    <div class="col-sm-7 has-feedback">
							      <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入姓名">
									<span id="info-panel1"></span>
									<span id="nameInfo"></span>
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="sex" class="col-sm-2 control-label">性别</label>
							    <div class="col-sm-7">
									<select name="sex" id="sex">
										<option value="1" selected="selected">男</option>
										<option value="2">女</option>
									</select>
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="birthYear" class="col-sm-2 control-label">出生年份</label>
							    <div class="col-sm-7">
								    <input type="date" id="birthYear" value="1996-09-02" name="birthYear"/>
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="grade" class="col-sm-2 control-label">入学时间</label>
							    <div class="col-sm-7">
								    <input type="date" id="grade" value="2015-09-02" name="grade"/>
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="collegeID" class="col-sm-2 control-label">所属院系</label>
							    <div class="col-sm-7">
								    <select class="form-control" id="collegeID" name="collegeID">
										<c:forEach items="${collegeList}" var="item">
											<option value="${item.collegeID}">${item.collegeName}</option>
										</c:forEach>
								    </select>
							    </div>
							  </div>
							  <div class="form-group" style="text-align: center">
								<button class="btn btn-default" type="submit" id="submit">提交</button>
								<button class="btn btn-default" type="reset" id="reset">重置</button>
							  </div>
						</form>
				    </div>
				    
				</div>

			</div>
		</div>
	</div>
	<div class="container" id="footer">
	<div class="row">
		<div class="col-md-12"></div>
	</div>
	</div>
</body>
	<script type="text/javascript">
		$("#nav li:nth-child(2)").addClass("active")
	</script>

</html>