<script>
function buildQueryTab(){
	return Ext.create('Ext.panel.Panel', {
	    title: '<%= i18n.getI18nText("menu.accounting.query") %>',
	    layout : {
          type : 'border'
        },
        defaults : {
          split : false
        },
        items: [{
        	region: 'north',
        	height: 250,
        	bodyStyle: 'padding:10px',
        	items:[{
        		xtype: 'form',
        		url: 'queryRecord.action',
        		border: false,
        		defaults:{
        			anchor: '100%',
                    labelWidth: 100
        		},
        		fieldDefaults: {
        	        labelWidth: 150
        	    },
        		items: [{
        			xtype: 'fieldset',
        			title: '<%= i18n.getI18nText("accounting.input.input.tips") %>',
        			items: [{
                        xtype : 'combobox',
                        fieldLabel : '<%= i18n.getI18nText("accounting.query.type") %>',
                        name : 'type',
                        store : Ext.create('Ext.data.ArrayStore', {
                            fields : [ 'key', 'value' ],
                            data : [ [ '0', '<%= i18n.getI18nText("accounting.common.all") %>' ], [ '-1', '<%= i18n.getI18nText("accounting.common.type.credit") %>' ], [ '1', '<%= i18n.getI18nText("accounting.common.type.debit") %>' ] ]
                        }),
                        value: '0',
                        valueField : 'key',
                        displayField : 'value',
                        typeAhead : false,
                        editable : false,
                        queryMode : 'local',
                        allowBlank: true
                    },{
                        xtype: 'combobox',
                        name: 'consumeTypeId',
                        store : Ext.create('Ext.data.ArrayStore', {
                            fields: [ {name: 'id',  type: 'string'}, {name: 'text', type: 'string'}],
                            proxy: {
                                type: 'ajax',
                                url : 'loadCascadeConsumeType.action'
                            },
                            autoLoad: true
                        }),
                        fieldLabel: '<%= i18n.getI18nText("accounting.query.label.consume.type") %>',
                        valueField : 'id',
                        displayField : 'text',
                        value: '',
                        editable : false,
                        allowBlank: true
                    },{
        				xtype: 'combobox',
        				name: 'target',
        				forceSelection: true,
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
                        valueField : 'name',
                        displayField : 'name',
                        allowBlank: true
                    },{
        				xtype: 'fieldcontainer',
        				fieldLabel: '<%= i18n.getI18nText("accounting.query.label.data.range") %>',
        				layout: 'hbox',
                        defaults: {
                            hideLabel: true
                        },
        				items:[{
        					xtype     : 'datefield',
                            name      : 'startDate',
                            fieldLabel: '<%= i18n.getI18nText("accounting.query.label.data.range.start") %>',
                            margin: '0 5 0 0',
                            allowBlank: false,
                            format: 'Y-m-d',
                            value: Ext.Date.format(new Date(), 'Y-m-d')
        				},{
        				    xtype: 'displayfield', value: '-'
        				},{
        					xtype     : 'datefield',
                            name      : 'endDate',
                            fieldLabel: '<%= i18n.getI18nText("accounting.query.label.data.range.end") %>',
                            allowBlank: false,
                            margin: '0 0 0 5',
                            format: 'Y-m-d',
                            value: Ext.Date.format(new Date(), 'Y-m-d')
        				}]
        			}, {
        				xtype: 'fieldcontainer',
                        fieldLabel: '<%= i18n.getI18nText("accounting.query.label.amount.range") %>',
                        layout: 'hbox',
                        defaults: {
                            hideLabel: true
                        },
                        items:[{
                            xtype     : 'textfield',
                            name      : 'startAmount',
                            margin: '0 5 0 0',
                            regex: /^[0-9.]+$/,
                            regexText: '<%= i18n.getI18nText("accounting.input.amount.err") %>'
                        },{
                            xtype: 'displayfield', value: '-'
                        },{
                            xtype     : 'textfield',
                            name      : 'endAmount',
                            margin: '0 0 0 5',
                            regex: /^[0-9.]+$/,
                            regexText: '<%= i18n.getI18nText("accounting.input.amount.err") %>'
                        }]
        			},{
                        xtype: 'textfield',
                        name: 'desc',
                        fieldLabel: '<%= i18n.getI18nText("accounting.query.label.desc") %>'
                    }]
        		}],
        		buttons:[{
        			text : '<%= i18n.getI18nText("button.reset") %>',
                    handler : function() {
                        this.up('form').getForm().reset();
                    }
        		}, {
                    text : '<%= i18n.getI18nText("button.submit") %>',
                    handler : function() {
                        var form = this.up('form').getForm();
                        if (form.isValid()) {
                            form.submit({
                                success : function(form, action) {
                                	if (action.result){
                                		store = Ext.getStore('temp-query-data');
                                		store.loadData(action.result.value, false);
	                                    Ext.getCmp('temp-query-grid').getView().refresh();
                                	}
                                },
                                failure : function(form, action) {
                                    Ext.Msg.alert('Failed', 'Error');
                                },
                                waitMsg : '<%= i18n.getI18nText("msg.waiting.submit") %>'
                            });
                        } else {

                        }
                    }
                }]
        		
        	}]
        },{
        	region: 'center',
        	split: true,
            defaults: {
                padding: 4
            },
        	items:[{
        		xtype:'grid',
        		id : 'temp-query-grid',
                title: '<%= i18n.getI18nText("accounting.input.record") %>',
                store: Ext.create('Ext.data.Store', {
                    storeId: 'temp-query-data',
                    data: [],
                    model: 'RecordModel'
                }),
                features: [{
                    ftype: 'summary'
                }],
                plugins: [
                    Ext.create('Ext.grid.plugin.RowEditing', {
                        clicksToEdit: 1
                    })
                ],
                tbar: [
                    {
                    	xtype: 'button',
                    	text: 'Apply',
                    	handler: function() {
                    		store = Ext.getStore('temp-query-data');
                    		Ext.Msg.alert('?', store.getUpdatedRecords());
                    	}
                    }
                ],
                columns: [
                    {header: '<%= i18n.getI18nText("accounting.input.type") %>',  dataIndex: 'type', flex: 1, 
                        renderer: function(value, metaData, record){
		                    if (value == 1){
		                        return '<%= i18n.getI18nText("accounting.input.type.debit") %>';
		                    }else if (value == -1){
		                        return '<%= i18n.getI18nText("accounting.input.type.credit") %>';
		                    }
	                    },
                        editor:{
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
                            emptyText : '<%= i18n.getI18nText("accounting.input.type.tips") %>',
                            allowBlank: false
                        }
                    },
	                {header: '<%= i18n.getI18nText("accounting.input.consume.type") %>', dataIndex: 'consumeTypeId', flex: 1,  
                    	renderer: function(value, metaData, record){
                    		   return record.data.consumeTypeValue;
	                    },
	                    editor: {
	                    	xtype: 'combobox',
	                        name: 'consumeTypeId',
	                        store : Ext.create('Ext.data.ArrayStore', {
	                            fields: [ {name: 'id',  type: 'string'}, {name: 'text', type: 'string'}],
	                            proxy: {
	                                type: 'ajax',
	                                url : 'loadCascadeConsumeType.action'
	                            },
	                            autoLoad: true
	                        }),
	                        fieldLabel: '<%= i18n.getI18nText("accounting.query.label.consume.type") %>',
	                        valueField : 'id',
	                        displayField : 'text',
	                        editable : false,
	                        allowBlank: true
	                    }
	                },
	                
	                {header: '<%= i18n.getI18nText("accounting.input.target") %>', dataIndex: 'target', flex: 1},
	                {header: '<%= i18n.getI18nText("accounting.input.date") %>', dataIndex: 'consumeDate', flex: 1,
	                    summaryRenderer: function(value, metaData, record){
	                          return '<string>Sum:</string>';
	                    }},
	                {header: '<%= i18n.getI18nText("accounting.input.amount") %>', dataIndex: 'amount', 
	                    renderer: function(value, metaData, record){
	                        if (record.data.type == -1){
	                            return '<font style="color: red;">' + Ext.util.Format.currency(value, '¥', 2) + '</font>';
	                        }else{
	                            return Ext.util.Format.currency(value, '¥', 2);
	                        }
	                    }, 
	                    flex: 1, summaryType: 'sum', 
	                    summaryRenderer: function(value, summaryData, dataIndex) {
	                    if (value < 0){
	                        return '<font style="color: red;">' + Ext.util.Format.currency(value, '¥', 2) + '</font>'; 
	                    }
	                    return Ext.util.Format.currency(value, '¥', 2); 
	                    }
	                },{
	                    header: '<%= i18n.getI18nText("accounting.input.desc") %>', dataIndex: 'desc', flex: 1
	                }
	            ]
        	}]
        }]
	});
}

</script>