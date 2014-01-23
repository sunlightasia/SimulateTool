<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="common.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/form/form.js"></script>
<div style="width: 100%;">
	<a href="prevSite">Previous</a>${siteUrl}<a href="nextSite">Next</a> <br />
	<span>(${indexSite}/${totalSite})</span>
</div>
<br />
<div style="width: 70%;">
	<iframe src="${siteUrl}" style="width: 100%; height: 500px;"></iframe>
</div>
<div style="width: 30%">
	<form action="createForm" method="POST">
		<table>
			<tr>
				<th>Form name</th>
				<td><input type="text" name="name" size="30" /></td>
				<th>Type</th>
				<td><input type="text" name="type" size="30" /></td>
			</tr>
			<tr>
				<th>Website</th>
				<td>${siteUrl}</td>
				<th>Action URL</th>
				<td><input type="text" name="url" size="30" /></td>
			</tr>
			<tr>
				<td>
					<button type="submit">Submit</button>
				</td>
			</tr>
		</table>
	</form>
	<br/>
	<form action="createField" method="POST">
		<table>
			<tr>
				<th>Form name</th>
				<td>
					<select name="formId">
						<option value="-1">--Please select form--</option>
						<c:forEach var="form" items="${formList}">
						<option value="${form.id}">${form.name}</option>
						</c:forEach>
					</select>
				</td>
				<th>Field name</th>
				<td><input type="text" name="name" size="30" /></td>
			</tr>
			<tr>
				<th>Type</th>
				<td><input type="text" name="type" size="30" /></td>
				<th>Max length</th>
				<td><input type="text" name="length" size="30" /></td>
			</tr>
			<tr>
				<th>Is required</th>
				<td colspan="3"><input type="radio" name="isRequired" value="1"/>Yes<input type="radio" name="isRequired" value="0"/>No</td>
			</tr>
			<tr>
				<td>
					<button type="submit">Submit</button>
				</td>
			</tr>
		</table>
	</form>
	<br/>
	<form action="registerAccount" method="get">
		<button type="submit">Register account for this site</button>
	</form>
</div>