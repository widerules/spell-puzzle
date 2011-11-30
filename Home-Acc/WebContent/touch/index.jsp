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
<link rel="stylesheet" href="app/css/style.css" type="text/css">
<style type="text/css">

#loading { position: absolute; width: 180px; margin: -70px 0 0 -90px; height: 140px; top: 50%; left: 50%; }
#loading .title {position: absolute; display: block; top: 0; left: 0px; width: 180px; height: 27px; }
#loading .logo { background: url(../images/loading.gif) no-repeat; position: absolute; display: block; top: 25px; left: 22px; width: 120px; height: 120px; }

</style>


</head>
<body>
<div id="loading"><span class="title">Accounting Loading...</span><span class="logo"></span></div>

<script type="text/javascript" src="../touchjs/sencha-touch.js"></script>
<!--<script type="text/javascript" src="../touchjs/touch-charts.js"></script>-->
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

<!-- utils -->
<script type="text/javascript" src="app/utils/lang-zh_CN.js"></script>

<script type="text/javascript" src="app/views/LoginView.js"></script>
<script type="text/javascript" src="app/views/Viewport.js"></script>

<!-- stores -->
<script type="text/javascript" src="app/stores/NavigationStore.js"></script>
<script type="text/javascript" src="app/stores/TypeStore.js"></script>
<script type="text/javascript" src="app/stores/ConsumeTypeStore.js"></script>
<script type="text/javascript" src="app/stores/TargetStore.js"></script>
<script type="text/javascript" src="app/stores/DetailsStore.js"></script>
<script type="text/javascript" src="app/stores/TypeStoreWith.js"></script>

<!-- frames -->
<script type="text/javascript" src="app/frames/InputCard.js"></script>
<script type="text/javascript" src="app/frames/QueryCard.js"></script>
</body>
</html>