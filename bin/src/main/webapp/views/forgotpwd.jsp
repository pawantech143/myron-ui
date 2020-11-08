<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>

<div align="center">
<font color="green">${successMsg}</font>

<font color="red">${errorMsg}</font>
		<form action="forgotPwd" method="POST">


			<table>

				<tr>

					<td>Email:</td>
					<td><input type="text" name="email"></td>
				
				<tr>

					<td><input type="reset" value="Reset"></td>
					<td><input type="submit" value="recover"></td>
				</tr>

			</table>

		</form>

	</div>

</body>


</html>