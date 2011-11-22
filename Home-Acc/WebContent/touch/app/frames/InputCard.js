AccountingApp.frames.InputCard = new Ext.form.FormPanel({
    url: '../saveInput.action',
    standardSubmit: false,
//    fullscreen: Ext.is.Phone,
//	autoRender: true,
//    floating: true,
//    modal: true,
//    centered: true,
    scroll: false,
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
            fieldLabel: bundle.getText("accounting.query.label.consume.type"),
            placeHolder: bundle.getText("accounting.input.type.tips")
        }, {
            xtype : 'selectfield',
            fieldLabel : bundle.getText("accounting.input.type"),
            name : 'type',
            store : AccountingApp.stores.TypeStore,
            valueField : 'key',
            displayField : 'value',
            placeHolder: bundle.getText("accounting.input.type.tips")
        },{
        	xtype: 'datepickerfield',
        	name: 'consumeDate',
        	fieldLabel: bundle.getText("accounting.input.date"),
        	value: new Date()
        }]
    }],
	dockedItems: [{
	    xtype: 'toolbar',
	    dock: 'bottom',
	    items: [
        {xtype: 'spacer'},
        {
            text: 'Reset',
            handler: function() {
                this.reset();
            }
        },
        {
            text: 'Save',
            ui: 'confirm',
            handler: function() {
                var form = this.up('form');
                
                var loadMask = new Ext.LoadMask(form.el, {
                    msg: 'Loading...'
                });
                loadMask.show();

                // form.showMask('Loading...');
                
                form.submit({
                	success : function(form, action) {
                        // location.href = 'app.action';
                		// form.hideMask();
                		loadMask.destroy();
                		this.hide();
					},
					failure : function(form, action) {
						// form.hideMask();
						Ext.Msg.alert('Failed', action.msg);
						loadMask.destroy();
					}
                	// waitMsg : {message:'Submitting'}
                });
            }
        }]
    }]
});