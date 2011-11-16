AccountingApp.views.Viewport = Ext.extend(Ext.Panel, {
	showAnimation: 'fade',
	html: 'Test Page',
	fullscreen: true,
	layout: 'card',
	// autoRender: true,
    initComponent: function() {
    	AccountingApp.views.Viewport.superclass.initComponent.call(this, arguments);
    	this.show();
    }
});