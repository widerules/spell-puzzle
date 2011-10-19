<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.james.fa.actions.BasicAction.I18N"%>
<% I18N i18n = (I18N)pageContext.findAttribute("i18n"); %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="extjs/resources/css/ext-all.css" />
<!-- <script type="text/javascript" src="extjs/ext-all.js"></script> -->
<script type="text/javascript" src="extjs/ext-all-debug-w-comments.js"></script>
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
                    title: '<%= i18n.getI18nText("accounting.home.title") %>',
                    html: '<%= i18n.getI18nText("accounting.home.title") %>'
                }]
            }]
        }],
        renderTo: 'mainContain'
    });
});

function buildInputTab() {
    return Ext.create('Ext.panel.Panel', {
        title: '<%= i18n.getI18nText("menu.accounting.input") %>',
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
                title : '<%= i18n.getI18nText("accounting.input.consume.type") %>',
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
                    title: '<%= i18n.getI18nText("accounting.input.input.tips") %>',
                    defaultType: 'textfield',
                    defaults: {
                        anchor: '50%'
                    },
                    items: [{
                        xtype : 'combobox',
                        fieldLabel : '<%= i18n.getI18nText("accounting.input.type") %>',
                        name : 'type',
                        store : Ext.create('Ext.data.ArrayStore', {
                            fields : [ 'key', 'value' ],
                            data : [ [ '-1', '<%= i18n.getI18nText("accounting.input.type.credit") %>' ], [ '1', '<%= i18n.getI18nText("accounting.input.type.debit") %>' ] ]
                        }),
                        valueField : 'key',
                        displayField : 'value',
                        typeAhead : false,
                        editable : false,
                        queryMode : 'local',
                        emptyText : '<%= i18n.getI18nText("accounting.input.type.tips") %>'
                    }, {
                        xtype: 'datefield',
                        fieldLabel: '<%= i18n.getI18nText("accounting.input.date") %>',
                        name: 'consumeDate',
                        allowBlank: false,
                        format: 'Y-m-d',
                        value: Ext.Date.format(new Date(), 'Y-m-d')
                    }, {
                        xtype: 'textfield',
                        fieldLabel: '<%= i18n.getI18nText("accounting.input.target") %>',
                        name: 'target',
                    }, {
                        xtype: 'textfield',
                        fieldLabel: '<%= i18n.getI18nText("accounting.input.amount") %>',
                        name: 'amount',
                        allowBlank: false,
                        regex: /^[0-9.]+$/,
                        regexText: '<%= i18n.getI18nText("accounting.input.amount.err") %>'
                    }, {
                        xtype: 'hiddenfield',
                        id: 'consumeTypeId',
                        name: 'consumeTypeId',
                        value: '',
                    }]
                }],
                buttons: [{
                    text : '<%= i18n.getI18nText("button.reset") %>',
                    handler : function() {
                        this.up('form').getForm().reset();
                    }
                }, {
                    text : '<%= i18n.getI18nText("button.submit") %>',
                    handler : function() {
                        var form = this.up('form').getForm();
                        if (Ext.getCmp('consumeTypeId').value == ""){
                            Ext.Msg.alert('Failed', '<%= i18n.getI18nText("accounting.input.type.err.required") %>');
                        }
                        if (form.isValid()) {
                            form.submit({
                                success : function(form, action) {
                                    
                                },
                                failure : function(form, action) {
                                    Ext.Msg.alert('Failed', action.result.msg);
                                },
                                waitMsg : '<%= i18n.getI18nText("msg.waiting.submit") %>'
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