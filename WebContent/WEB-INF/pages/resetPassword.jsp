<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>xx科技 員工資訊系統</title>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/resetPassword.css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+TC&display=swap" rel="stylesheet"> 
</head>
<body>
<section class="ff-login">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="ff-login-box">
                    <form method="post" action="passwordChange" name="lForm">
                        <h2 class="title">重設密碼</h2>
                        <label for="usrname">Account</label>
						<input type="text" id="usrname" name="usrname" class="form-control form-control-lg font-weight-light mt-4" value="${userName}" readonly="readonly">                
							<label for="psw">Password</label>
							<input type="password" id="psw" name="psw" class="form-control form-control-lg font-weight-light mt-3" placeholder="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
								required>
							<input type="password" id="psw2" name="psw2" class="form-control form-control-lg font-weight-light mt-3" placeholder="Confirm Password" onkeyup="pw()"
								title="兩次輸入的密碼要相同"  required> <span id="tishi"></span>
							<input type="submit" id="submit" class="btn btn-primary btn-lg mt-3 ff-login-btn font-weight-bold" value="密碼變更">
								<span class="wrong">${MsgFromPwdReset.resetFailed}</span>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>

	<div id="message">
		<h5>你的密碼須符合</h5>
		<p id="letter" class="invalid">
			一個<b>小寫</b>英文字母
		</p>
		<p id="capital" class="invalid">
			一個<b>大寫</b>英文字母
		</p>
		<p id="number" class="invalid">
			一個<b>數字</b>
		</p>
		<p id="length" class="invalid">
			最少<b>8個數字加英文字母</b>
		</p>
	</div>

	<script>
		var myInput = document.getElementById("psw");
		var letter = document.getElementById("letter");
		var capital = document.getElementById("capital");
		var number = document.getElementById("number");
		var length = document.getElementById("length");

		myInput.onkeyup = function() {
			var lowerCaseLetters = /[a-z]/g;
			if (myInput.value.match(lowerCaseLetters)) {
				letter.classList.remove("invalid");
				letter.classList.add("valid");
			} else {
				letter.classList.remove("valid");
				letter.classList.add("invalid");
			}

			var upperCaseLetters = /[A-Z]/g;
			if (myInput.value.match(upperCaseLetters)) {
				capital.classList.remove("invalid");
				capital.classList.add("valid");
			} else {
				capital.classList.remove("valid");
				capital.classList.add("invalid");
			}

			var numbers = /[0-9]/g;
			if (myInput.value.match(numbers)) {
				number.classList.remove("invalid");
				number.classList.add("valid");
			} else {
				number.classList.remove("valid");
				number.classList.add("invalid");
			}

		
			if (myInput.value.length >= 8) {
				length.classList.remove("invalid");
				length.classList.add("valid");
			} else {
				length.classList.remove("valid");
				length.classList.add("invalid");
			}
		}
		
        function pw() {
            var pw1 = document.getElementById("psw").value;
            var pw2 = document.getElementById("psw2").value;
            if(pw1 == pw2) {
                document.getElementById("tishi").innerHTML="<font color='green'>兩次密碼相同</font>";
                document.getElementById("submit").disabled = false;
            }
            else {
                document.getElementById("tishi").innerHTML="<font color='red'>兩次密碼不相同</font>";
              document.getElementById("submit").disabled = true;
            }
        }
	 
	</script>

</body>
</html>