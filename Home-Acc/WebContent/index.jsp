<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Redirect Page</title>
<script type="text/javascript" src="extjs/ext.js"></script>
<script type="text/javascript">
Ext.onReady(function(){
    console.log(Ext.is);
    if (Ext.is.Desktop){
        location.href = "desktop.action";
    }else{// if(Ext.is.Phone){
        location.href = "./touch/index.action";
    };
});
</script>
</head>
<body>
 
</body>
</html>