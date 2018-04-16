<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/assets/css/grid/demos.css" />
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open+Sans:300,600,400" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/assets/css/grid/jsgrid.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/assets/css/grid/theme.css" />  
  
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/assets/js/db.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/assets/js/grid/jsgrid.core.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/assets/js/grid/jsgrid.load-indicator.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/assets/js/grid/jsgrid.load-strategies.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/assets/js/grid/jsgrid.sort-strategies.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/assets/js/grid/jsgrid.field.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/assets/js/grid/fields/jsgrid.field.text.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/assets/js/grid/fields/jsgrid.field.number.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/assets/js/grid/fields/jsgrid.field.select.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/assets/js/grid/fields/jsgrid.field.checkbox.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/assets/js/grid/fields/jsgrid.field.control.js"></script>
<script>
$(function() {

	$("#jsGrid").jsGrid({
		
		onItemUpdating: function(args) {
			
			console.log(db.toVoData2(args.item));
			
			$.ajax({
				url: "/mysite3/grid/api/update",
				type: "post",
				dataType: "json",
				data: JSON.stringify(db.toVoData2(args.item)),
				contentType: "application/json",
				success: function(response) {
					if (response.result == false) {
						args.cancel = true;
					}
					
				}
			});
			
		},
                height: "70%",
                width: "100%",
                //filtering: true,
                editing: true,
                inserting: true,
                //sorting: true,
                //paging: true,
                autoload: true,
                //pageSize: 15,
                //pageButtonCount: 5,
                deleteConfirm: "삭제하시겠습니까?",
                controller: db,
                fields: [
                	{ name: "No", type: "text", width: 20, visiable: false },
                    { name: "Name", type: "text", width: 150 },
                    { name: "Age", type: "number", width: 50 },
                    { name: "Address", type: "text", width: 200 },
                    { name: "Country", type: "select", items: db.countries, valueField: "Id", textField: "Name" },
                    { name: "Married", type: "checkbox", title: "Is Married", sorting: false },
                    { type: "control" }
                ]
            });
});
</script>
</head>
<body>
    <h1>Basic Scenario</h1>
    <div id="jsGrid"></div>
</body>
</html>