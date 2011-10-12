<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="extjs/resources/css/ext-all.css" />
<script type="text/javascript" src="extjs/ext-all.js"></script>
<style type="text/css">
#loading { position: absolute; width: 180px; margin: -70px 0 0 -90px; height: 140px; top: 50%; left: 50%; }
#loading .title {position: absolute; display: block; top: 0; left: 0px; width: 180px; height: 27px; }
#loading .logo { background: url(images/loading.gif) no-repeat; position: absolute; display: block; top: 25px; left: 22px; width: 120px; height: 120px; }
</style>
<title>Family Accounting</title>
<script type="text/javascript">
Ext.onReady(function() {
	
	Ext.define('TreeNode', {
	    extend: 'Ext.data.Model',
	    fields: [
            {name: 'text',  type: 'string'},
            {name: 'url',  type: 'string'},
	    ],
	});
	
	
    Ext.create('Ext.Viewport', {
        layout: {
            type: 'border',
            padding: 5
        },
        defaults: {
            split: true
        },
        items: [{
            region: 'west',
            collapsible: true,
            title: 'Navigator',
            split: true,
            width: '30%',
            layout: 'fit',
            html: 'west<br>I am floatable',
            items: [{
                xtype: 'treepanel',
                store: Ext.create('Ext.data.TreeStore', {
                    proxy: {
                        type: 'ajax',
                        url: 'loadNavigator.action'
                    },
                    root: {
                        text: 'Ext JS',
                        id: 'src',
                        expanded: true
                    },
                    model: 'TreeNode'
                }),
                rootVisible: false,
                listeners : {
                	itemclick: {
                		fn: function (view, record, item, index, e){
                			Ext.Msg.alert('Title', record.data.url);
                		}
                	}
                }
            }]
        },{
            region: 'center',
            layout: 'border',
            border: false,
            items: [{
                region: 'center',
                html: 'center center'
            }]
        }],
        renderTo: 'mainContain'
    });
});
</script>
</head>
<body>
<div id="loading"><span class="title">Accounting Loading...</span><span class="logo"></span></div>
<div id="mainContain"></div>
</body>
</html>