AccountingApp.frames.InputCard = new Ext.form.FormPanel({
    url: '../saveInput.action',
    standardSubmit: false,
//    fullscreen: Ext.is.Phone,
//	autoRender: true,
//    floating: true,
//    modal: true,
//    centered: true,
    scroll: true,
//    showAnimation: 'fade',
    hideOnMaskTap: true,
//    height: 385,
//    width: 480,
	items: [{
		xtype: 'fieldset',
        title: bundle.getText('menu.accounting.input'),
        instructions: 'Please enter the information above.',
        defaults: {
            required: true,
            labelAlign: 'left',
            labelWidth: '40%'
        },
        items: [{
        	xtype: 'selectfield',
        	name: 'consumeTypeId',
//        	store: AccountingApp.stores.NavigationStore,
//        	valueField : 'key',
//            displayField : 'text',
            store : AccountingApp.stores.ConsumeTypeStore,
            valueField : 'key',
            displayField : 'value',
            label: bundle.getText("accounting.query.label.consume.type"),
            placeHolder: bundle.getText("accounting.input.type.tips")
        }, {
            xtype : 'selectfield',
            label : bundle.getText("accounting.input.type"),
            name : 'type',
            store : AccountingApp.stores.TypeStore,
            valueField : 'key',
            displayField : 'value',
            placeHolder: bundle.getText("accounting.input.type.tips")
        },{
        	xtype: 'datepickerfield',
        	name: 'consumeDate',
        	label: bundle.getText("accounting.input.date"),
        	value: new Date()
        },{
        	xtype: 'selectfield',
        	name: 'target',
            store : AccountingApp.stores.TargetStore,
            label: bundle.getText("accounting.query.label.target"),
            value: '',
            valueField : 'name',
            displayField : 'name',
        },{
        	xtype: 'spinnerfield',
            name : 'amount',
            label: bundle.getText("accounting.input.amount")
        },{
            xtype : 'textareafield',
            name  : 'desc',
            label : bundle.getText("accounting.input.desc"),
            maxLength : 60,
            maxRows : 8
        }]
    }],
	dockedItems: [{
	    xtype: 'toolbar',
	    dock: 'bottom',
	    items: [
        {xtype: 'spacer'},
        {
            text: bundle.getText("button.reset"),
            handler: function() {
                this.reset();
            }
        },
        {
            text: bundle.getText("button.submit"),
            ui: 'confirm',
            handler: function() {
                var form = this.up('form');
                
                var loadMask = new Ext.LoadMask(form.el, {
                    msg: 'Loading...'
                });
                loadMask.show();

                // form.showMask('Loading...');
                
                form.submit({
                	params: form.getValues(true),
                	method: 'POST',
                	success : function(form, action) {
                        // location.href = 'app.action';
                		// form.hideMask();
                		console.log(form.getValues(true));
                		loadMask.destroy();
                		Ext.Msg.alert(bundle.getText("accounting.common.msg.title.success"), bundle.getText("accounting.input.save.msg.success"), function(){form.reset();});
                		// this.hide();
					},
					failure : function(form, action) {
						// form.hideMask();
						console.log(form.getValues(true));
						Ext.Msg.alert(bundle.getText("accounting.common.msg.title.failure"), action.msg);
						loadMask.destroy();
					}
                	// waitMsg : {message:'Submitting'}
                });
            }
        }]
    }]
});