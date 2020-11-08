<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>

	<div align="center">
		<h3>Unlock Account</h3>
		<p>${successMsg}</p>
		<p>${failureMsg}</p>
		<form:form action="unlockAcc?email=${userAcc.email}" method="POST"
			modelAttribute="userAcc">


			<table>

				<tr>

					<td>Email:</td>
					<td>${userAcc.email}</td>
				</tr>
				<tr>

					<td>Temporary Password:</td>
					<td><form:password path="tempPwd" /></td>
				</tr>
				<tr>

					<td>Choose New Password:</td>
					<td><form:password path="newPwd" />
						</td>

				</tr>
				<tr>

					<td>Confirm Pssword:</td>
					<td><form:password path="confirmPwd" /></td>
				</tr>
				
				<tr>

					<td><input type="reset" value="Reset"></td>
					<td><input type="submit" value="Register" id="unlock account"></td>
				</tr>

			</table>

		</form:form>

	</div>

</body>

</html>