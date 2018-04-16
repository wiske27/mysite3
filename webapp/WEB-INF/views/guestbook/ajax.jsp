<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	//var no = 0;
	var isEnd = false;

	var fetchList = function() {
		if (isEnd == true) {
			return;
		}

		var no = $("#list li").last().data("no") || 0;
		$.ajax({
			url : "/mysite3/guestbook/ajax/list?no=" + no,
			type : "get",
			dataType : "json",
			data : "",
			success : function(response) {
				//끝 감지
				if (response.length < 5) {
					isEnd = true;
					$("#btn-fetch").prop("disabled", true);
				}
				$.each(response, function(index, data) {
					render(false, data);
				});
			}
		});
	}
	
	var render = function(mode, data) {
		var html = "<li data-no='"+ data.no +"'><table><tr>" 
				+ "<td>"+ data.name + "</td>" 
				+ "<td>" + data.regDate + "</td>"
				+ "<td><a href='' data-no='" + data.no +" '>삭제</a></td>" 
				+ "</tr><tr>" 
				+ "<td colspan=3>"
				+ data.contents + "</td>" + "</tr></table><br></li>";

		if (mode == true) {
			$("#list").prepend(html);
		} else {
			$("#list").append(html);
		}
	}

	//main(dom이 완성된 후에 실행되는 함수)
	$(function() {
		
		var dialog = $("#dialog-form").dialog({
			autoOpen : false,
			//height : 400,
			width : 350,
			modal : true,
			buttons : {
				"삭제" : function(){
					var no = $("#delete-no").val();
					var password = $("#delete-password").val();
					
					console.log(no + ":" + password);
					
					//ajax 통신
					$.ajax({
						url: "/mysite3/guestbook/ajax/delete",
						type: "post",
						dataType: "json",
						data: "no=" + no + "&password=" + password,
						success: function(response){
							console.log(response);
							
							if(response.result == false) {
								$(".validateTips.normal").hide();
								$(".validateTips.error").show();
								$("#delete-password").val("");
								return;
							}
							
							$("#list li[data-no=" + response.no + "]").remove();
							dialog.dialog("close");
						}
					});
				},
				"취소" : function() {
					dialog.dialog("close");
				}
			},
			close : function() {
				$(".validateTips.normal").show();
				$(".validateTips.error").hide();
				$("#delete-no").val("");
				$("delete-password").val("");
			}
		});
		
		$(window).scroll(function(){
			var scrollTop = $(this).scrollTop();
			var windwoHeight = $(this).height();
			var documentHeight = $(document).height();
			
			if (scrollTop + windwoHeight + 20 > documentHeight) {
				fetchList();
			}
		});
				
				
		//live event
		$(document).on("click", "#list li a", function(event) {
			event.preventDefault();
			var no = $(this).data("no");
			$("#delete-no").val(no);
			
			dialog.dialog("open");
		});

		$("#add-form").submit(function(event) {
			event.preventDefault();
			/*
			var data = 
				"name=" + $("#input-name").val() + 
				"&password=" + $("#input-password").val() + 
				"&contents=" + $("#input-contents").val();
			 */
			var data = $(this).serialize();
			$.ajax({
				url : "/mysite3/guestbook/ajax/insert",
				type : "post",
				dataType : "json",
				data : data,
				success : function(response) {
					render(true, response);
					$("#add-form")[0].reset();
				}
			});

		});
		
		//첫번째 리스트 가져오기
		fetchList();
		
		/*$("#btn-fetch").click(function() {
			fetchList();
		});*/
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<form id="add-form" action="" method="get">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name" id="input-name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="password"
								id="input-password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="contents" id="input-contents"
									cols="60" rows="5"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul id="list">

				</ul>
				<!-- <button id="btn-fetch">가져오기</button> -->
			</div>

			<div id="dialog-form" title="비밀번호 입력" style="display: none">
			<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
			<p class="validateTips error" style="display:none">비밀번호가 잘못되었습니다.</p>
				<form>
					<input type="hidden" id="delete-no" value="" > 
					<input type="password" id="delete-password" value="" class="text ui-widget-content ui-corner-all">
					<input type="submit" tabindex="-1" style="position: absolute; top: -1000px">
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp" />
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>