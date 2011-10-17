<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
Ext.onReady(function() {
    Ext.define('ConsumeType', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'text',  type: 'string'},
        ]
    });
    
    tree = Ext.create('Ext.tree.Panel',{
    	renderTo: 'consume_tree',
    	width: 200,
        height: 150,
    	store: Ext.create('Ext.data.TreeStore', {
            proxy: {
                type: 'ajax',
                url: 'loadConsumeType.action'
            },
            model: 'ConsumeType'
        }),
        rootVisible: false,
        listeners : {
            itemclick: {
                fn: function (view, record, item, index, e){
                    str = "";
                	for (e in window['localStorage']){
                	    str += e + "<br />";
                    }
                    Ext.Msg.alert('title', str);
                }
            }
        }
        });
});

</script>
</head>
<body>
<div id="consume_tree"></div>
<div id="inputForm"></div>
</body>
</html>