<%@ page language="java" pageEncoding="gbk" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base target="_self" />
		<title>��ҳ</title>
		<style type="text/css">
<!--
body {
	font-size: 10pt;
	text-align: center;
}

dl {
	margin: 0;
}

dt {
	background-color: #666;
	color: #fff;
	margin: 1px;
	padding: 0 3px;
}

dd {
	margin: 3px;
}

div {
	margin: auto;
	line-height: 150%;
	text-align: left;
	width: 600px;
	border: 1px solid #049ed0;
}

#postBox {
	margin-top: 10px;
}

dd.button {
	text-align: center;
}

dd.button input {
	margin: 0 20px;
}

.messageHistabs {
	width: 60%;
}

.messageHistabs li {
	margin: 0 2px 0 0;
	background: #dcecf8 url(../images/steps_right.gif) no-repeat right top;
	float: left;
	cursor: pointer;
	text-align: left;
}

ul,ol {
	list-style: none outside none;
}

th {
	color: #049ed0;
	text-align: center;
}

td {
	text-align: left;
}

a {
	color: #049ed0;
}
//
-->
</style>
		<script language="javascript" src="<%=path%>/js/zxc.js"></script>
	</head>
	<body bgcolor="#F4F4F4">
		<h1>
			<font color="#049ed0" face="Arial">�����ļ�<a
				style="color: #049ed0;" href="<%=path%>/uploadfile.jsp">�ϴ�</a>������ϵͳ</font>
		</h1>
		<span style="float: left"> ��ǰ�û��� <s:property
								value="#session.user.username" /> &nbsp;&nbsp;&nbsp;&nbsp;��¼ʱ��:
							<script type="text/javascript">
document.write(new Date().toLocaleString())
</script> </span>
		<span
			style="color: #049ed0; float: right; text-decoration: underline; cursor: pointer;"><a
			onclick="javascript:window.location.href='<%=path%>/logout.jsp'" style="color: #049ed0;">�˳�ϵͳ</a>
		</span>
		<br>
		<input type="hidden" id="path" value="<%=path%>" />
		<form name="FileListForm" id="FileListForm"
			action="<%=path%>/query.action" method="post">
			<font color="#141F78" size="5">�ļ��б�</font>
			<table id="me" border="1" align="center" width="80%">
				<thead>
					<tr>
						<th width="5%">
							<span>���</span>
						</th>
						<th width="25%">
							<span>�ļ�����</span>
						</th >
						<th width="15%">
							<span>�ļ���С</span>
						</th>
						<th width="5%">
							<span>���ش���</span>
						</th>
						<th width="15%">
							<span>�ϴ���IP</span>
						</th>
						<th width="15%">
							<span>�ϴ�ʱ��</span>
						</th>
						<th width="10%">
							<span>����</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<s:if
						test="(pageListData.dataList!=null)&&(!pageListData.dataList.isEmpty())">
						<s:iterator value="#request.pageListData.dataList" id="u"
							status="st">
							<tr id="changecolor" bgcolor="#FFFFFF" align="center">
								<td>
									<s:property value="#st.index+1+(currentPage-1)*pageSize" />
								</td>
								<td>
									<s:property value="#u.fileName" />
								</td>
								<td>
									<s:property value="#u.fileSize" />
								</td>
								<td>
									<s:property value="#u.fileTimes" />
								</td>
								<td>
									<s:property value="#u.fileIp" />
								</td>
								<td>
									<s:property value="#u.fileUptime" />
								</td>
								<td>
								    <a href="javascript:window.location.href='<%=path%>/download.action?fileId=${u.fileId}&&fileName=${u.fileName}&&fileSavename=${u.fileSavename}'">����</a>
									<a href="javascript:window.location.href='<%=path%>/delete.action?fileId=${u.fileId}'"
										onclick="return confirm('ɾ�����޷��ָ�,ȷ��Ҫɾ����')">ɾ��</a>
								</td>
							</tr>
						</s:iterator>
					</s:if>
				</tbody>
			</table>
			<s:property value="#request.pageListData.footer" escape="false" />
		</form>
	</body>
</html>
