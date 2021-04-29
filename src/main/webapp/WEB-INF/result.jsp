<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
	      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/home.css" />
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
	<title>Result</title>
</head>
<body>
<div class="form__group">
	<p style="font-size: 30px">Result of <strong>${functionType}(${functionValue}):</strong> ${functionResult}</p>
	<div>
		<a href="${pageContext.request.contextPath}/" style="color: white; text-decoration: underline; font-size: 40px">Go back</a>
	</div>
</div>

</body>
</html>