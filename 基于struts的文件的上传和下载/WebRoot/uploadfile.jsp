<%@ page language="java" contentType="text/html; charset=gbk"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>upload</title>

	</head>
	<body>

		<script type="text/javascript">

<!--addMore���������ṩ�ϴ�����ļ��ϴ�-->

function addMore()
{

	var td = document.getElementById("more");
	
	var br = document.createElement("br");
	var input = document.createElement("input");
	var button = document.createElement("input");
	
	input.type = "file";
	input.name = "upload";
	
	button.type = "button";
	button.value = "ɾ�� ";
	
	button.onclick = function()
	{
		td.removeChild(br);
		td.removeChild(input);
		td.removeChild(button);
	}
	
	td.appendChild(br);
	td.appendChild(input);
	td.appendChild(button);
}

</script>

		<!--<h3><font color="red">�ϴ��ļ����ͺ�׺Ϊdoc,ppt,xls,pdf,txt,java��ÿ���ļ���С���ܴ���50M</font></h3>-->

		<table align="center" width="70%">
			<tr>
				<td>

					<!--		<s:actionerror cssStyle="color:red"/>-->
					<s:fielderror cssStyle="color:red" />

				</td>
			</tr>
		</table>

		<s:form action="upload.action" theme="simple" method="post"
			enctype="multipart/form-data">

			<table align="center" width="50%" border="1">
				<tr>
					<td>
						�ϴ��ļ�
					</td>
					<td id="more">
						<s:file name="upload" />
						<input type="button" value="�ϴ�����..." onclick="addMore()">
					</td>
				</tr>
				<tr>
					<td>
					</td>
					<td>
						<s:submit value=" ȷ�� "></s:submit>
						<s:reset value=" ���� "></s:reset>
						<input type="button" value="�����б�"
							onclick="window.location.href='<%=request.getContextPath()%>/query.action'" />
					</td>
				</tr>

			</table>

		</s:form>

	</body>


</html>
