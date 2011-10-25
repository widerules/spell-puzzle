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
        		url: '',
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
                            data : [ [ '-1', '<%= i18n.getI18nText("accounting.input.type.credit") %>' ], [ '1', '<%= i18n.getI18nText("accounting.input.type.debit") %>' ] ]
                        }),
                        valueField : 'key',
                        displayField : 'value',
                        typeAhead : false,
                        editable : false,
                        queryMode : 'local',
                        emptyText : '<%= i18n.getI18nText("accounting.query.type.tips") %>',
                        allowBlank: true
                    },{
        				xtype: 'combobox',
        				name: 'target',
        				forceSelection: true,
        				store : Ext.create('Ext.data.ArrayStore', {
       						fields: [ {name: 'tag',  type: 'string'}],
        				    proxy: {
        				        type: 'ajax',
        				        url : 'tags.action'
        				    },
        				    autoLoad: true
                        }),
                        fieldLabel: '<%= i18n.getI18nText("accounting.query.label.target") %>',
                        valueField : 'tag',
                        displayField : 'tag',
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
                            name      : 'amountStart',
                            margin: '0 5 0 0',
                            regex: /^[0-9.]+$/,
                            regexText: '<%= i18n.getI18nText("accounting.input.amount.err") %>'
                        },{
                            xtype: 'displayfield', value: '-'
                        },{
                            xtype     : 'textfield',
                            name      : 'amountEnd',
                            margin: '0 0 0 5',
                            regex: /^[0-9.]+$/,
                            regexText: '<%= i18n.getI18nText("accounting.input.amount.err") %>'
                        }]
        			},{
                        name: 'description',
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
                                    store = Ext.getStore('temp-input-data');
                                    form.setValues([{id: 'amount', value: parseInt(form.getValues()['type']) * parseFloat(form.getValues()['amount']) }]);
                                    store.insert(0, form.getValues());
                                    Ext.getCmp('temp-input-grid').getView().refresh();
                                    ctid = Ext.getCmp('consumeTypeId').getValue();
                                    ctval = Ext.getCmp('consumeTypeValue').getValue();
                                    form.reset();
                                    Ext.getCmp('consumeTypeId').setValue(ctid);
                                    Ext.getCmp('consumeTypeValue').setValue(ctval);
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
        	items:[{
        		
        	}]
        }]
	});
}

</script>