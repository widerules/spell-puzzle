<script type="text/javascript">
<!--
function buildInputTab() {
    return Ext.create('Ext.panel.Panel', {
        title: '<%= i18n.getI18nText("menu.accounting.input") %>',
        layout : {
          type : 'border',
          padding : 5
        },
        defaults : {
            split : true
        },
        items : [ {
        	region: 'north',
        	height: 280,
            collapsible: true,
            hideCollapseTool: true,
        	layout: 'border',
        	items: [ {
                region : 'west',
                width : '30%',
                layout: 'fit',
                split : true,
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
                                Ext.getCmp('consumeTypeValue').setValue(record.data.text);
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
                            value: '-1',
                            queryMode : 'local',
                            emptyText : '<%= i18n.getI18nText("accounting.input.type.tips") %>',
                            allowBlank: false
                        }, {
                            xtype: 'datefield',
                            fieldLabel: '<%= i18n.getI18nText("accounting.input.date") %>',
                            name: 'consumeDate',
                            allowBlank: false,
                            format: 'Y-m-d',
                            value: Ext.Date.format(new Date(), 'Y-m-d')
                        }, {
                        	xtype: 'combo',
                            name: 'target',
                            store : Ext.create('Ext.data.ArrayStore', {
                                fields: [ {name: 'name',  type: 'string'}],
                                proxy: {
                                    type: 'ajax',
                                    url : 'loadCascadeTag.action'
                                },
                                autoLoad: true
                            }),
                            fieldLabel: '<%= i18n.getI18nText("accounting.query.label.target") %>',
                            value: '',
                            typeAhead: true,
                            valueField : 'name',
                            displayField : 'name',
                            allowBlank: false
                        }, {
                            xtype: 'textfield',
                            fieldLabel: '<%= i18n.getI18nText("accounting.input.amount") %>',
                            id: 'amount',
                            name: 'amount',
                            allowBlank: false,
                            regex: /^[0-9.]+$/,
                            regexText: '<%= i18n.getI18nText("accounting.input.amount.err") %>'
                        }, {
                            xtype: 'textareafield',
                            fieldLabel: '<%= i18n.getI18nText("accounting.input.desc") %>',
                            grow      : true,
                            name      : 'desc'
                        }, {
                            xtype: 'hiddenfield',
                            id: 'consumeTypeId',
                            name: 'consumeTypeId',
                            value: ''
                        }, {
                            xtype: 'hiddenfield',
                            id: 'consumeTypeValue',
                            name: 'consumeTypeValue',
                            value: ''
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
                                Ext.Msg.alert('<%= i18n.getI18nText("accounting.common.msg.title.failure") %>', '<%= i18n.getI18nText("accounting.input.type.err.required") %>');
                            }
                            if (form.isValid()) {
                                form.submit({
                                    success : function(form, action) {
                                        store = Ext.getStore('temp-input-data');
                                        form.setValues([{id: 'amount', value: parseInt(form.getValues()['type']) * parseFloat(form.getValues()['amount']) }]);
                                        store.insert(0, form.getValues());
                                        Ext.getCmp('temp-input-grid').getView().refresh();
                                        ctid = Ext.getCmp('consumeTypeId').getValue();
                                        ctval = Ext.getCmp('consumeTypeValue').getValue();
                                        Ext.Tips.msg('<%= i18n.getI18nText("accounting.common.msg.title.success") %>', '<%= i18n.getI18nText("accounting.input.save.msg.success") %>');
                                        form.reset();
                                        Ext.getCmp('consumeTypeId').setValue(ctid);
                                        Ext.getCmp('consumeTypeValue').setValue(ctval);
                                    },
                                    failure : function(form, action) {
                                        Ext.Msg.alert('<%= i18n.getI18nText("accounting.common.msg.title.failure") %>', '<%= i18n.getI18nText("accounting.common.msg.unknown") %>');
                                    },
                                    waitMsg : '<%= i18n.getI18nText("msg.waiting.submit") %>'
                                });
                            } else {

                            }
                        }
                    }]
                }]
            }]
        }, {
            region : 'center',
            split: true,
            defaults: {
                padding: 4
            },
            items : [ {
                xtype:'grid',
                id: 'temp-input-grid',
                title: '<%= i18n.getI18nText("accounting.input.record") %>',
                store: Ext.create('Ext.data.Store', {
                    storeId: 'temp-input-data',
                    data: [],
                    model: 'RecordModel'
                }),
                features: [{
                    ftype: 'summary'
                }],
                columns: [
                          {header: '<%= i18n.getI18nText("accounting.input.type") %>',  dataIndex: 'type', flex: 1, renderer: function(value, metaData, record){
                              if (value == 1){
                                  return '<%= i18n.getI18nText("accounting.input.type.debit") %>';
                              }else if (value == -1){
                                  return '<%= i18n.getI18nText("accounting.input.type.credit") %>';
                              }
                          }},
                          {header: '<%= i18n.getI18nText("accounting.input.consume.type") %>', dataIndex: 'consumeTypeId', flex: 1,  renderer: function(value, metaData, record){
                              return record.data.consumeTypeValue;
                          }},
                          
                          {header: '<%= i18n.getI18nText("accounting.input.target") %>', dataIndex: 'target', flex: 1},
                          {header: '<%= i18n.getI18nText("accounting.input.date") %>', dataIndex: 'consumeDate', flex: 1,
                        	  xtype: 'datecolumn',
                              format: 'Y-m-d',
                              summaryRenderer: function(value, metaData, record){
                                    return '<string>Sum:</string>';
                              }},
                          {header: '<%= i18n.getI18nText("accounting.input.amount") %>', dataIndex: 'amount', 
                              renderer: function(value, metaData, record){
                                  if (record.data.type == -1){
                                      return '<font style="color: red;">' + Ext.util.Format.currency(value, '�', 2) + '</font>';
                                  }else{
                                      return Ext.util.Format.currency(value, '�', 2);
                                  }
                              }, 
                              flex: 1, summaryType: 'sum', 
                              summaryRenderer: function(value, summaryData, dataIndex) {
                              if (value < 0){
                                  return '<font style="color: red;">' + Ext.util.Format.currency(value, '�', 2) + '</font>'; 
                              }
                              return Ext.util.Format.currency(value, '�', 2); 
                              }
                          },{
                              header: '<%= i18n.getI18nText("accounting.input.desc") %>', dataIndex: 'desc', flex: 1
                          }
                      ]
            } ]
        } ]
    });
}
//-->
</script>
