<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
$(function(){
	$("#join-form").submit(function(){
		//1. 이름 체크
		var name = $("#name").val();
		if( name == "" ) {
			alert( "이름은 필수 입력 사항입니다." );
			$("#name").focus();
			return false;
		}
		
		//2. 이메일 체크
		var email = $("#email").val();
		if( email == "" ){
			alert( "이메일은 필수 입력 항목입니다." );
			$("#email").focus();
			return false;
		}
		
		//3. 이메일 중복 체크
		var isVisible = $( "#img-checkemail" ).is( ":visible" )
		if( isVisible == false ) {
			alert( "이메일 중복 체크를 해 주세요." );
			return false;
		}
		
		//4. 비밀번호 체크
		var password = $("#password").val();
		if( password == "" ) {
			alert( "비밀번호는 필수 입력 항목입니다." );
			$("#password").focus();
			return false;
		}
		
		//5. 약관동의
		var checked = $("#agree-prov").is( ":checked" );
		if( checked == false ) {
			alert( "약관 동의를 해 주세요" );
			return false;
		}
		
		return true;
	});
	
	$( "#email" ).change(function(){
		$( "#btn-checkemail" ).show();
		$( "#img-checkemail" ).hide();		
	});
	
	$( "#btn-checkemail" ).click(function(){
		var email = $("#email").val();
		if( email == '' ) {
			return;
		}
		
		$.ajax({
			url: "/mysite3/user/checkemail?email=" + email,
			type: "get",
			dataType: "json",
			data: "",
			success: function(response){
				//console.log( response );
				if( response.exist == true ) {
					alert( "존재 하는 이메일입니다. 다른 이메일을 사용하세요." );
					$("#email").val( "" ).focus();
					return;
				}
				
				$( "#btn-checkemail" ).hide();
				$( "#img-checkemail" ).show();
			},
			error: function(xhr, e){
				console.error( e );
			}
		});
	});
	
	
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="${pageContext.servletContext.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="">
					<input id="btn-checkemail" type="button" value="id 중복체크">
					<img id="img-checkemail" style="display:none" src="/mysite3/assets/images/check.png">
					
					<label class="block-label">패스워드</label>
					<input id="password" name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>