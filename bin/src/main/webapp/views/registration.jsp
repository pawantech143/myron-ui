<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#email").change(function() {

			$.ajax({

				url : "uniqueMailCheck",
				data : {
					'email' : $("#email").val()
				},
				success : function(resTxt) {

					if (resTxt == "EMAIL IS EXISTED") {

						$("#emailError").text("duplicate email");

						$("#submitbtn").prop("disabled", true);
					} else {

						$("#submitbtn").prop("disabled", false);
					}
				}

			});
		});
	});
</script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$("#cid")
								.change(
										function() {
											$('#stateId').find(
													'option:not(:first)')
													.remove();
											$('#ctid').find(
													'option:not(:first)')
													.remove();
											$
													.ajax({

														url : "countryChange?countryId="
																+ $("#cid")
																		.val(),
														success : function(
																resTxt) {

															$
																	.each(
																			resTxt,
																			function(
																					stateId,
																					stateName) {
																				$(
																						'#stateId')
																						.append(
																								$(
																										'<option>')
																										.text(
																												stateName)
																										.attr(
																												'value',
																												stateId));
																			});
														}

													});

										});

					});
</script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$("#stateId")
								.change(
										function() {

											$('#ctid').find(
													'option:not(:first)')
													.remove();
											$
													.ajax({

														url : "statesChange?stateId="
																+ $("#stateId")
																		.val(),
														success : function(
																resTxt) {

															$
																	.each(
																			resTxt,
																			function(
																					cityId,
																					cityName) {
																				$(
																						'#ctid')
																						.append(
																								$(
																										'<option>')
																										.text(
																												cityName)
																										.attr(
																												'value',
																												cityId));
																			});
														}

													});

										});

					});
</script>

<body>

	<div align="center">
		<h3>Registration Page</h3>
		<font color="green"">${successMsg}</font> <font color="red">${failureMsg}</font>
		<form:form action="userRegistration" method="POST"
			modelAttribute="account">


			<table>

				<tr>

					<td>first name:</td>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>

					<td>last name:</td>
					<td><form:input path="lastName" /></td>
				</tr>
				<tr>

					<td>Email:</td>
					<td><form:input path="email" id="email" /><span
						id="emailError"></span></td>

				</tr>
				<tr>

					<td>Mobile Number:</td>
					<td><form:input path="mobileNo" /></td>
				</tr>
				<tr>

					<td>Dob:</td>
					<td><form:input path="dob" /></td>
				</tr>
				<tr>

					<td>Gender:</td>
					<td><form:radiobutton path="gender" value="M" />MALE</td>
					<td><form:radiobutton path="gender" value="F" />FEMALE</td>
				</tr>
				<tr>

					<td>select country:</td>
					<td><form:select path="countryId" id="cid">
							<form:option value="">Select</form:option>
							<form:options items="${countries}" />

						</form:select></td>
				</tr>
				<tr>

					<td>select states:</td>
					<td><form:select path="stateId" id="stateId">
							<form:option value="">Select</form:option>


						</form:select>
				</tr>
				<tr>

					<td>select cities:</td>
					<td><form:select path="cityId" id="ctid">
							<form:option value="">Select</form:option>


						</form:select>
				</tr>
				<tr>

					<td><input type="reset" value="Reset"></td>
					<td><input type="submit" value="Register" id="submitbtn"></td>
				</tr>

			</table>

		</form:form>

	</div>

</body>

</html>