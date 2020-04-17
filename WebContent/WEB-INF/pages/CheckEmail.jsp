<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>xx科技 員工資訊系統</title>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/CheckEmail.css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+TC&display=swap" rel="stylesheet"> 
</head>
<body>
<div>

<div class="ff-login">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="ff-login-box">
                    <form method="post" name="lForm">
                        <h2 class="title">找回密碼</h2>
                        <input type="text" class="form-control form-control-lg font-weight-light mt-4" placeholder="你的E-mail" name="email">
                        <input type="submit" value="提交" class="btn btn-primary btn-lg mt-3 ff-login-btn font-weight-bold" onclick="document.lForm.action='sendMail'"/>
                        <span class="wrong">${errorMsgFromForgetPwd.emailNotFound}</span>
                        <span class="wrong">${errorMsgFromForgetPwd.emailEmptyError}</span>
                        <span class="registerOK">${errorMsgFromForgetPwd.emailSucess}</span>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<figure class="CanNot">
    <img src="../images/ResetPwd.jpg">
</figure>
</div>


</body>
</html>