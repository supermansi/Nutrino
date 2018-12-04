<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"> -->

</head>
<body>

	<center>
		<h1>Nutrino</h1>
	</center>

	<div class="container" id="loginScreen">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">Login</a>
							</div>
							<div class="col-xs-6">
								<a href="#" id="register-form-link">Register</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" action="userlogin" method="post"
									style="display: block;">
									<div class="form-group">
										<input type="text" name="username" id="username" tabindex="1"
											class="form-control" placeholder="Username" value="">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password"
											tabindex="2" class="form-control" placeholder="Password">
									</div>
									<!-- <div class="form-group text-center">
										<input type="checkbox" tabindex="3" class="" name="remember"
											id="remember"> <label for="remember">
											Remember Me</label>
									</div> -->
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="login-submit" id="login-submit"
													tabindex="4" class="form-control btn btn-login"
													value="Log In">
											</div>
										</div>
									</div>
									<!-- <div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href="https://phpoll.com/recover" tabindex="5"
														class="forgot-password">Forgot Password?</a>
												</div>
											</div>
										</div>
									</div> -->
								</form>
								<form id="register-form" action="usercreate" method="post"
									style="display: none;">
									<div class="form-group">
										<input type="text" name="username" id="username" tabindex="1"
											class="form-control" placeholder="Username" value="">
									</div>

									<div class="form-group">
										<input type="text" name="firstname" id="firstname"
											tabindex="1" class="form-control" placeholder="First Name"
											value="">
									</div>

									<div class="form-group">
										<input type="text" name="lastname" id="lastname" tabindex="1"
											class="form-control" placeholder="Last Name" value="">
									</div>

									<div class="form-group">
										<input type="email" name="email" id="email" tabindex="1"
											class="form-control" placeholder="Email Address" value="">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password"
											tabindex="2" class="form-control" placeholder="Password">
									</div>

									<div class="form-group">
										<input type="text" name="height" id="height" tabindex="2"
											class="form-control" placeholder="Height in cm">
									</div>

									<div class="form-group">
										<input type="text" name="weight" id="weight" tabindex="2"
											class="form-control" placeholder="Weight in kg">
									</div>
									<p>Diet Restrictions</p>
									<div class="form-group">
										<select id="diet" name="diet">
											<option value="Balanced">Balanced</option>
											<option value="AlcoholFree">Alcohol-Free</option>
											<option value="CeleryFree">Celery-Free</option>
											<option value="CrustaceanFree">Crustacean-Free</option>
											<option value="DairyFree">Dairy-Free</option>
											<option value="EggFree">Egg-Free</option>
											<option value="FishFree">Fish-Free</option>
											<option value="GlutenFree">Gluten-Free</option>
											<option value="KidneyFriendly">Kidney-Friendly</option>
											<option value="Kosher">Kosher</option>
											<option value="LowPotassium">Low-Potassium</option>
											<option value="LupineFree">Lupine-Free</option>
											<option value="MustardFree">Mustard-Free</option>
											<option value="NoOilAdded">No Oil Added</option>
											<option value="NoSugar">No Sugar</option>
											<option value="Paleo">Paleo</option>
											<option value="PeanutFree">Peanut-Free</option>
											<option value="Pescatarian">Pescatarian</option>
											<option value="PorkFree">Pork-Free</option>
											<option value="RedMeatFree">Red Meat-Free</option>
											<option value="SesameFree">Sesame-Free</option>
											<option value="ShellFishFree">ShellFish-Free</option>
											<option value="SoyFree">Soy-Free</option>
											<option value="SugarConscious">Sugar-Conscious</option>
											<option value="TeaNutsFree">Tea-Nuts-Free</option>
											<option value="Vegan">Vegan</option>
											<option value="Vegetarian">Vegetarian</option>
											<option value="WheatFree">Wheat-Free</option>
										</select>
									</div>

									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="register-submit"
													id="register-submit" tabindex="4"
													class="form-control btn btn-register" value="Register Now">
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="./js/main.js"></script>
</html>