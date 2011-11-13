<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.james.fa.actions.BasicAction.I18N"%>
<%@page import="java.util.Calendar"%>

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
<script type="text/javascript" >

Ext.onReady(function(){
	
	var d = new Date(),
	dateLike = d.getFullYear() + "-" + (d.getMonth() + 1  < 10 ? "0" + d.getMonth() + 1 : d.getMonth() + 1),
	type = -1,
	by = 'consumeType';

	pieStore = Ext.create('Ext.data.Store', {
	    fields: [ {name: 'key', type: 'string'}, {name: 'amount',  type: 'float'}],
	    proxy:{
	        type: 'ajax',
	        url: 'realtimeConsumeTypeReport.action?dateLike=' + dateLike + '&type=' + type + '&by=' + by
	    },
	    pageSize: 100,
	    autoLoad: false
	});

	lineStore = Ext.create('Ext.data.Store', {
	    fields: [ {name: 'key', type: 'string'}, {name: 'amount',  type: 'float'}],
	    proxy:{
	        type: 'ajax',
	        url: 'realtimeDailyReport.action?dateLike=' + dateLike + '&type=' + type
	    },
	    pageSize: 100,
	    autoLoad: true
	});

	gridStore = Ext.create('Ext.data.Store', {
	    model: 'RecordModel',
	    proxy: {
	        type: 'ajax',
	        url: 'realtimeDetailReport.action?dateLike=' + dateLike + '&type=' + type
	    },
	    pageSize: 1000,
	    autoLoad: false
	});

	refreshPie = function(type, dateLike, by){
	    pieStore.setProxy({
	        type: 'ajax',
	        url: 'realtimeConsumeTypeReport.action?dateLike=' + dateLike + '&type=' + type + '&by=' + by
	    });
	    pieStore.load();
	};

	refreshData = function(type, dateLike){
	     refreshPie(type, dateLike, by);
	     
	     lineStore.setProxy({
	         type: 'ajax',
	         url: 'realtimeDailyReport.action?dateLike=' + dateLike + '&type=' + type
	     });
	     lineStore.load();
	     
	     gridStore.setProxy({
	         type: 'ajax',
	         url: 'realtimeDetailReport.action?dateLike=' + dateLike + '&type=' + type
	     });
	     gridStore.load();
	};
	
	Ext.create('Ext.panel.Panel', {
	    renderTo: 'lineChart',
	    border: false,
	    height: 300,
	    width: 600,
	    layout: 'fit',
	    items:[{
	           xtype: 'chart',
	           store: lineStore,
	            animate: true,
	            shadow: true,
	            height: 300,
	            width: '100%',
	            theme: 'Base:gradients',
	            axes: [{
	                type: 'Numeric',
	                minimum: 0,
	                position: 'left',
	                fields: ['amount'],
	                title: '<%= i18n.getI18nText("accounting.realtime.report.daily.axes.y.title") %>',
	                grid: {
	                    odd: {
	                        opacity: 1,
	                        fill: '#ddd',
	                        stroke: '#bbb',
	                        'stroke-width': 0.5
	                    }
	                }
	            }, {
	                type: 'Category',
	                position: 'bottom',
	                fields: ['key'],
	                label: {
	                    rotate: {
	                        degrees: 315
	                    }
	                }
	            }],
	            series: [{
	                type: 'line',
	                highlight: {
	                    size: 7,
	                    radius: 7
	                },
	                axis: 'left',
	                xField: 'consumeDate',
	                yField: 'amount',
	                tips: {
	                    trackMouse: false,
	                    width: 160,
	                    height: 28,
	                    renderer: function(storeItem, item) {
	                      this.setTitle(storeItem.get('key') + ': ¥ ' + storeItem.get('amount'));
	                    }
	                },
	                markerConfig: {
	                    type: 'circle',
	                    size: 4,
	                    radius: 4,
	                    'stroke-width': 0
	                }
	            }]
	    }]
	});
});

</script>
</head>
<body>
<div style="font-size: 24px"><%= i18n.getI18nText("accounting.home.h1", String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1)) %></div>
<hr />
<div id="lineChart"></div>
<div id="pieChart"></div>
<div id="detailGrid"></div>
</body>
</html>