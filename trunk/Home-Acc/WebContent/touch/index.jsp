<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Redirect Page</title>
<script type="text/javascript" src="../touchjs/sencha-touch.js"></script>
<script type="text/javascript">
Ext.onReady(function(){
    console.log(Ext.os);
    if (Ext.os.is.iOS || Ext.os.is.Android){
        // location.href = "./touch/index.action";
    }else{
    	// location.href = "desctop.action";
    };
});
</script>
</head>
<body>
 
</body>
</html>