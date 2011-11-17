AccountingApp.views.Viewport = Ext.extend(Ext.Panel, {
	showAnimation: 'fade',
	//html: 'Test Page',
	fullscreen: true,
	layout: 'card',
	// autoRender: true,
    initComponent: function() {
    	this.navigationPanel = new Ext.NestedList({
            store: sink.StructureStore,
            useToolbar: Ext.is.Phone ? false : true,
            updateTitleText: false,
            dock: 'left',
            hidden: !Ext.is.Phone && Ext.Viewport.orientation == 'portrait',
            toolbar: Ext.is.Phone ? this.navigationBar : null,
            listeners: {
                itemtap: this.onNavPanelItemTap,
                scope: this
            }
        });
    	
    	
    	
    	AccountingApp.views.Viewport.superclass.initComponent.call(this);
    	// this.show();
    }
});