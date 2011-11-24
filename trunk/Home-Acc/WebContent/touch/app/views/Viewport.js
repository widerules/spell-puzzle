AccountingApp.views.Viewport = Ext.extend(Ext.Panel, {
	showAnimation: 'fade',
	//html: 'Test Page',
	fullscreen: true,
	layout: 'card',
	items: [{
		cls: 'launchscreen',
		html: '<div><img src="../images/offline-logo.png" /><br /><br /><h1>Welcome to Family Accounting</h1><p><br /><br /><span>&copy;Offline studio, 2011</span></p></div>'
	}],
	// autoRender: true,
    initComponent: function() {
    	this.navigationPanel = new Ext.NestedList({
            store: AccountingApp.stores.NavigationStore,
            useToolbar: !Ext.is.Phone,
            updateTitleText: false,
            dock: 'left',
            hidden: !Ext.is.Phone && Ext.Viewport.orientation == 'portrait',
            toolbar: Ext.is.Phone ? this.navigationBar : null,
            listeners: {
                itemtap: this.onNavPanelItemTap,
                scope: this
            }
        });
    	
    	if (!Ext.is.Phone){
    		this.navigationPanel.setWidth(250);
    	}
    	
    	this.dockedItems = this.dockedItems || [];
    	
    	if (!Ext.is.Phone && Ext.Viewport.orientation == 'landscape') {
            this.dockedItems.unshift(this.navigationPanel);
        }else{
        	this.items.unshift(this.navigationPanel);
        }
    	
    	AccountingApp.views.Viewport.superclass.initComponent.call(this);
    	// this.show();
    },
    
    onNavPanelItemTap: function(list, index, el, e) {
    	var record = list.getStore().getAt(index); 
    	console.log(record);
    	
    	var key = record.get('key');
    	
        if (Ext.Viewport.orientation == 'portrait' && !Ext.is.Phone && !recordNode.childNodes.length && !preventHide) {
            this.navigationPanel.hide();
        }
        
        if (key == "accounting/input"){
        	this.setActiveItem(AccountingApp.frames.InputCard, 'slide');
        	this.currentCard = AccountingApp.frames.InputCard;
        }else if (key == "accounting/query"){
        	
        }else if (key == "accounting/realtimereport"){
        	
        }else if (key == "accounting/historyreport"){
        	
        }
//
//        if (card) {
//            this.setActiveItem(card, anim || 'slide');
//            this.currentCard = card;
//        }
//
//        if (title) {
//            this.navigationBar.setTitle(title);
//        }
//        this.toggleUiBackButton();
//        this.fireEvent('navigate', this, record);
    }
});