<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="common.jsp"></jsp:include>
<div style="width: 100%;">
	<a href="prevSite">Previous</a>
	<s:property value="#request.siteUrl" />
	<a href="nextSite">Next</a>
</div>
<br />
<div style="width: 70%;">
	<iframe src="<s:property value="#request.siteUrl"/>"></iframe>
</div>
<div style="width: 30%">
	<form action="createForm.action">
		<table>
			<tr>
				<th>Form name</th>
				<td><input type="text" name="name" size="30" /></td>
				<th>Type</th>
				<td><input type="text" name="type" size="30" /></td>
			</tr>
			<tr>
				<th>Website</th>
				<td><s:property value="#request.siteName" /></td>
				<th>URL</th>
				<td><input type="text" name="url" size="30" /></td>
			</tr>
		</table>
	</form>
</div>