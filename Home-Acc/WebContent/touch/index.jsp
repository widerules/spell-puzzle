<%@page import="com.james.fa.actions.BasicAction.I18N"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.PropertyResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% I18N i18n = (I18N)pageContext.findAttribute("i18n"); %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Family Accounting</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../touchjs/resources/css/touch-charts-full.css" type="text/css">
<link rel="stylesheet" href="../touchjs/resources/css/touch-charts-demo.css" type="text/css">
<script type="text/javascript" src="../touchjs/sencha-touch.js"></script>
<script type="text/javascript" src="../touchjs/touch-charts.js"></script>
<script type="text/javascript">
bundle = (function(){
    var resourcesBundle = {};
	<% PropertyResourceBundle b = i18n.getBundle();
       Enumeration<String> keys = b.getKeys();
	   while (keys.hasMoreElements()){
            String key = keys.nextElement(); %>
    resourcesBundle["<%= key %>"] = "<%=b.getString(key)%>"; <%
       }
    %>

    return resourcesBundle;
})();

bundle.getText = function(key){
    return bundle[key] || key;
};
</script>
<script type="text/javascript" src="app/app.js"></script>
<script type="text/javascript" src="app/views/LoginView.js"></script>
</head>
<body>
 
</body>
</html>