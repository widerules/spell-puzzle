<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="extjs/resources/css/ext-all.css" />
<!-- <script type="text/javascript" src="extjs/ext-all.js"></script> -->
<script type="text/javascript" src="extjs/ext-all-debug-w-comments.js"></script>
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
            {name: 'key',  type: 'string'},
	    ]
	});
	
	Ext.define('ConsumeType', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'text',  type: 'string'},
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
                			if (record.data.key == "accounting/input"){
                				if (Ext.getCmp('main_tab')){
                					tabPanel = Ext.getCmp('main_tab');
                					tabPanel.setLoading(true);
                					tabPanel.removeAll();
                                    tabPanel.add(buildInputTab());
                                    tabPanel.setActiveTab(0);
                                    tabPanel.setLoading(false);
                                }
                			}else if (record.data.key == ""){
                				
                			}else if (record.data.key == ""){
                				
                			}
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
                    id: 'main_content',
                    title: 'test title',
                    html: 'content'
                }]
            }]
        }],
        renderTo: 'mainContain'
    });
});

function buildInputTab() {
	return Ext.create('Ext.panel.Panel', {
        title: 'input',
        layout : {
          type : 'border',
          padding : 5
        },
        defaults : {
            split : false
        },
        items : [ {
            region : 'west',
            width : '30%',
            layout: 'fit',
            split : false,
            collapsible: false,
            items : [ {
                xtype : 'treepanel',
                title : 'Consume Type',
                border : false,
                store : Ext.create('Ext.data.TreeStore', {
                    proxy : {
                        type : 'ajax',
                        url : 'loadConsumeType.action'
                    },
                    model : 'ConsumeType'
                }),
                rootVisible : false,
                listeners : {
                    itemclick : {
                        fn : function(view, record, item, index, e) {
                            Ext.getCmp('consumeTypeId').setValue(record.data.id);
                        }
                    }
                }
            } ]
        }, {
            region : 'center',
            border : false,
            bodyStyle: 'padding:5px 5px 0',
            defaults: {
                padding: 4
            },
            items : [ {
            	xtype : 'form',
            	url : 'saveInput.action',
            	border : false,
            	items :[{
                    xtype:'fieldset',
                    title: 'Input Record~',
                    defaultType: 'textfield',
                    defaults: {
                        anchor: '50%'
                    },
                    items: [{
                        xtype : 'combobox',
                        fieldLabel : 'Type',
                        name : 'type',
                        store : Ext.create('Ext.data.ArrayStore', {
                            fields : [ 'key', 'value' ],
                            data : [ [ '-1', 'Credit' ], [ '1', 'Debit' ] ]
                        }),
                        valueField : 'key',
                        displayField : 'value',
                        typeAhead : false,
                        editable : false,
                        queryMode : 'local',
                        emptyText : 'Select a Type...'
                    }, {
                        xtype: 'datefield',
                        fieldLabel: 'Date',
                        name: 'consumeDate',
                        allowBlank: false,
                        format: 'Y-m-d',
                        value: Ext.Date.format(new Date(), 'Y-m-d')
                    }, {
                        xtype: 'textfield',
                        fieldLabel: 'Target',
                        name: 'target',
                    }, {
                        xtype: 'textfield',
                        fieldLabel: 'Amount',
                        name: 'amount',
                        allowBlank: false,
                        regex: /^[0-9.]+$/,
                        regexText: 'Number only'
                    }, {
                        xtype: 'hiddenfield',
                        id: 'consumeTypeId',
                        name: 'consumeTypeId',
                        value: '',
                    }]
                }],
                buttons: [{
                    text : 'Reset',
                    handler : function() {
                        this.up('form').getForm().reset();
                    }
                }, {
                    text : 'Submit',
                    handler : function() {
                        var form = this.up('form').getForm();
                        if (Ext.getCmp('consumeTypeId').value == ""){
                            Ext.Msg.alert('Failed', 'Please select a "Consume Type"');
                        }
                        if (form.isValid()) {
                            form.submit({
                                success : function(form, action) {
                                    
                                },
                                failure : function(form, action) {
                                    Ext.Msg.alert('Failed', action.result.msg);
                                },
                                waitMsg : 'Submiting...'
                            });
                        } else {

                        }
                    }
                }]
            }]
        }, {
            region : 'south',
            height : '60%',
            defaults: {
                padding: 4
            },
            items : [ {
                xtype:'fieldset',
                title: 'Input Record~',
                defaultType: 'textfield',
                defaults: {
                    bodyPadding: 50
                },
                items: [{
                    xtype : 'combobox',
                    fieldLabel : 'State',
                    name : 'type',
                    store : Ext.create('Ext.data.ArrayStore', {
                        fields : [ 'key', 'value' ],
                        data : [ [ '-1', 'Credit' ], [ '1', 'Debit' ] ]
                    }),
                    valueField : 'key',
                    displayField : 'value',
                    typeAhead : false,
                    editable : false,
                    queryMode : 'local',
                    emptyText : 'Select a Type...'
                }]
            } ]
        } ]
    });
}

</script>
</head>
<body>
<div id="loading"><span class="title">Accounting Loading...</span><span class="logo"></span></div>
<div id="mainContain"></div>
<form id="history-form" class="x-hide-display">
    <input type="hidden" id="x-history-field" />
    <iframe id="x-history-frame"></iframe>
</form>
</body>
</html>