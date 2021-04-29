<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
	      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/home.css" />
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
	<title>Homepage</title>
</head>
<body>
<div class="main-container">
	<form:form modelAttribute="function" action="calculate" method="post" cssClass="main-container">
		<div class="form__group">
			<div>
				<input type="number" step="0.01" class="form__input" id="value" placeholder="Function Value..." name="value"/>
				<label for="value" class="form__label">Function Value...</label>
			</div>
			<div class="center">
				<select name="type" class="form__input small-size">
					<option>Sin</option>
					<option>Tan</option>
				</select>
			</div>
			<div class="center">
				<input type="submit" value="Calculate" class="form__input medium-size">
			</div>
		</div>
	</form:form>
</div>
</body>
</html>