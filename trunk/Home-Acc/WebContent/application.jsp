<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.james.fa.actions.BasicAction.I18N"%>
<% I18N i18n = (I18N)pageContext.findAttribute("i18n"); %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="extjs/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="css/msgtool.css" />
<script type="text/javascript" src="extjs/ext-all.js"></script>
<script type="text/javascript" src="extjs/resources/locale/ext-lang-zh_CN.js"></script> 
<script type="text/javascript" src="js/models.js"></script>
<style type="text/css">
#loading { position: absolute; width: 180px; margin: -70px 0 0 -90px; height: 140px; top: 50%; left: 50%; }
#loading .title {position: absolute; display: block; top: 0; left: 0px; width: 180px; height: 27px; }
#loading .logo { background: url(images/loading.gif) no-repeat; position: absolute; display: block; top: 25px; left: 22px; width: 120px; height: 120px; }
</style>
<title>Family Accounting</title>
<script type="text/javascript">
Ext.onReady(function() {

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
            title: '<%= i18n.getI18nText("title.navigator") %>',
            split: true,
            width: '30%',
            layout: 'fit',
            items: [{
                xtype: 'treepanel',
                store: Ext.create('Ext.data.TreeStore', {
                    proxy: {
                        type: 'ajax',
                        url: 'loadNavigator.action'
                    },
                    root: {
                        text: 'ROOT',
                        expanded: true
                    },
                    model: 'TreeNode'
                }),
                rootVisible: false,
                listeners : {
                	itemclick: {
                		fn: function (view, record, item, index, e){
                			tabPanel = Ext.getCmp('main_tab');
        					tabPanel.setLoading(true);
                			if (record.data.key == "accounting/input"){
                				if (tabPanel){
                					tabPanel.removeAll();
                                    tabPanel.add(buildInputTab());
                                    tabPanel.setActiveTab(0);
                                }
                			}else if (record.data.key == "accounting/query"){
                				if (tabPanel){
                					tabPanel.removeAll();
                					tabPanel.add(buildQueryTab());
                					tabPanel.setActiveTab(0);
                				}
                			}else if (record.data.key == "accounting/realtimereport"){
                				if (tabPanel){
                                    tabPanel.removeAll();
                                    tabPanel.add(buildRealtimeReportTab());
                                    tabPanel.setActiveTab(0);
                                }
                			}else if (record.data.key == "accounting/historyreport"){
                				if (tabPanel){
                                    //tabPanel.removeAll();
                                    //tabPanel.add(buildRealtimeReportTab());
                                    //tabPanel.setActiveTab(0);
                                }
                			}
                            tabPanel.setLoading(false);
                		}
                	}
                }
            }]
        },{
            region: 'center',
            layout: 'fit',
            border: false,
            items: [{
                id: 'main_tab',
                xtype: 'tabpanel',
                items: [{
                    title: '<%= i18n.getI18nText("accounting.home.title") %>',
                    html: '<%= i18n.getI18nText("accounting.home.title") %>'
                }]
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
<form id="history-form" class="x-hide-display">
    <input type="hidden" id="x-history-field" />
    <iframe id="x-history-frame"></iframe>
</form>
<script type="text/javascript" src="js/msgtool.js"></script>
<script type="text/javascript" src="js/stores.js"></script>
<%@include file="accounting-input.jsp" %>
<%@include file="accounting-query.jsp" %>
<%@include file="realtime-report.jsp" %>
</body>
</html>