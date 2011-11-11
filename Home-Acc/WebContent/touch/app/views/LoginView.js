AccountingApp.views.LoginView = Ext.extend(Ext.form.FormPanel, {
	scroll: 'vertical',
    url: '../login.action',
    standardSubmit: false,
    fullscreen: Ext.is.Phone,
	autoRender: true,
    floating: true,
    modal: true,
    centered: true,
    hideOnMaskTap: false,
    height: 385,
    width: 480,
	items: [{
		xtype: 'fieldset',
        title: bundle.getText('title.login'),
        instructions: 'Please enter the information above.',
        defaults: {
            required: true,
            labelAlign: 'left',
            labelWidth: '40%'
        },
        items: [{
            xtype: 'textfield',
            name : 'username',
            label: bundle.getText('label.username'),
            useClearIcon: true,
            autoCapitalize : false
        }, {
            xtype: 'passwordfield',
            name : 'password',
            label: bundle.getText('label.password'),
            useClearIcon: false
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
                var loadMask = new Ext.LoadMask(this.up('form').el, {
                    msg: 'Loading...'
                });
                loadMask.show();
                
                this.up('form').submit({
                	success : function(form, action) {
                        // location.href = 'app.action';
                		loadMask.destroy();
                		console.log(action);
                        Ext.Msg.alert('Success', action);
					},
					failure : function(form, action) {
						loadMask.destroy();
						console.log(action);
						Ext.Msg.alert('Failed', action);
					}
                	// waitMsg : {message:'Submitting'}
                });
            }
        }]
    }],
    initComponent: function() {
    	AccountingApp.views.LoginView.superclass.initComponent.call(this, arguments);
    	this.show();
    }
});
