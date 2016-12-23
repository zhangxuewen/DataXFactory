<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DataXFactory index</title>
<script type="text/javascript" src="<%=basePath%>resources/jquery-2.0.3.min.js"></script>

</head>
<body>
  <div class="">
  	<form action="" method="post" name="form1" id="form1">
  		<table>
  			<tr>
  				<td>数据库类型</td>
  				<td>
  					<select name="dbType" id="dbType">
  						<option value="MySql">MySQL</option>
  						<option value="oracle">oracle</option>
  						<option value="SQLServer">sql server</option>
  					</select>
  				</td>
  			</tr>
  			<tr>
  				<td>数据库地址</td>
  				<td><input type="text" name="address" id="address"/></td>
  			</tr>
  			<tr>
  				<td>数据库名称</td>
  				<td><input type="text" name="dbName" id="dbName"/></td>
  			</tr>
  			<tr>
  				<td>端口号</td>
  				<td><input type="text" name="port" id="port"/></td>
  			</tr>
  			<tr>
  				<td>用户名</td>
  				<td><input type="text" name="user" id="user"/></td>
  			</tr>
  			<tr>
  				<td>密码</td>
  				<td><input type="text" name="password" id="password"/></td>
  			</tr>
  			<tr>
  				<td colspan="2">
  					<input type="button" value="设置" name="setUp" id="setUp"/>
  				</td>
  			</tr>
  		</table>
  	</form>
  </div>
  <div>
  	<table border="1">
  		<tr>
  			<td>编号</td>
  			<td>数据库类型</td>
  			<td>数据库名称</td>
  			<td>地址</td>
  			<td>端口号</td>
  			<td>用户名</td>
  			<td>密码</td>
  			<td>状态</td>
  			<td>操作</td>
  		</tr>
	  	<c:if test="${!empty dataBaseParams}">
	  		<c:forEach items="${dataBaseParams}" var="dbParam" varStatus="status">
	  			<tr>
	  				<td width="10px"><input type="text" value="<c:out value='${status.index+1 }'/>"/></td>
	  				<td width="20px"><input type="text" value="<c:out value='${dbParam.dbType }'/>"></td>
	  				<td width="50px"><input type="text" value="<c:out value='${dbParam.dbName }'/>"></td>
	  				<td width="70px"><input type="text" value="<c:out value='${dbParam.address }'/>"></td>
	  				<td width="10px"><input type="text" value="<c:out value='${dbParam.port }'/>"></td>
	  				<td width="50px"><input type="text" value="<c:out value='${dbParam.userName }'/>"></td>
	  				<td width="50px"><input type="text" value="<c:out value='${dbParam.password }'/>"></td>
	  				<td width="30px"><input type="text" value="<c:out value='${dbParam.status }'/>"></td>
	  				<td width="80px">
	  					<input type="hidden" name="id" class="id" value="<c:out value='${dbParam.id}'/>"/>
		  				<input type="button" value="修改" class="updates"/>
		  				<input type="button" value="删除" class="delete"/>
		  				<input type="button" value="连接" class="connection"/>
	  				</td>
	  			</tr>
	  		</c:forEach>
	  	</c:if>
  	</table>
  </div>
  <div class="tables">
  </div>
  <div class="attributes">
  </div>
  <input type="button" value="设置" id="btn">
  <input type="button" value="修改" id="update"/>
</body>
<script type="text/javascript">
	$(function(){
		//afe7078abf67d953a386dcfd7271e5be
		$("#update").click(function(){
			alert("1234...");
			//config/setUp
			var datas = {"dpId":"afe7078abf67d953a386dcfd7271e5be"};
			
			//config/update
			//var datas = {"dpId":"afe7078abf67d953a386dcfd7271e5be","dbType":"MySql","dbName":"dxf","address":"localhost","port":3308,"user":"root","password":"root"};
			
			$.ajax({
				url:"<%=basePath%>config/setUp",
				type:"post",
				data:datas,
				dataType:"json",
				success:function(data){
					alert(data.status);
				}
			});
		});
		$("#setUp").click(function(){
			var dbType = $("#dbType").val();
			var address = $("#address").val();
			var dbName = $("#dbName").val();
			var port = $("#port").val();
			var user = $("#user").val();
			var password = $("#password").val();
			
			var datas = {"dbType":dbType,"dbName":dbName,"address":address,"port":port,"user":user,"password":password};
			$.ajax({
				type:"post",
				url:"<%=basePath%>config/setUp",
				data:datas,
				dataType:"json",
				success:function(data){
					/* alert(jQuery.parseJSON(data.tables));
					var jsonData = jQuery.parseJSON(data.tables);
					alert(jsonData); */
					var uls = "<ul>";
					$(data.tables).each(function(i,item){
						uls += "<li class='tableName' onclick='liClick(this)'>";
						uls += item;
						uls += "</li>";
					});
					uls += "</ul>";
					$(".tables").html(uls);
				}
			});
		});
		
		//生成SQL语句
		$("#btn").click(function(){
			var select = "jn_tags.tagtext;jn_usertags.creatorid";
			var from = "jn_tags;jn_usertags";
			var where = "jn_tags.tagid=jn_usertags.tagid;jn_tags.tagid2=jn_usertags.tagid2";
			var datas = {"select":select,"from":from,"where":where};
			$.ajax({
				type:"post",
				url:"<%=basePath%>sql/setUp",
				data:datas,
				dataType:"json",
				success:function(data){
					alert(data.status);
					alert(data.sql);
				}
			});
		});
		
	
	});
	function liClick(x){
		var value = $(x).html();
		$.ajax({  
	        type : "post",  
	        url : "<%=basePath%>query/attribute",
	        data:{tableName:value},
	        dataType:"json",  
	        success : function(json) {  
	            var tables = "<table border='1'><tr><td><input type='checkbox' /></td><td>名称</td><td>是否主键</td><td>类型</td><td>长度</td><td>小数位数</td><td>是否为空</td><td>说明</td></tr>";
	            $(json.models).each(function(i,item){
	            	tables += "<tr>";
	            	tables += "<td><input type='checkbox' /></td>";
	            	tables += "<td>"+ item.columnName +"</td>";
	            	tables += "<td>"+ item.primaryKey +"</td>";
	            	tables += "<td>"+ item.columnType +"</td>";
	            	tables += "<td>"+ item.datasize +"</td>";
	            	tables += "<td>"+ item.digits +"</td>";
	            	tables += "<td>"+ item.nullable +"</td>";
	            	tables += "<td>"+ item.description +"</td>";
	            	tables += "</tr>";
	            });
	            tables += "</table>";
	            alert(tables);
	            $(".attributes").html(tables);
	        }  
	    });
	}
	
</script>
</html>