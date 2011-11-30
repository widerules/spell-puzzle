AccountingApp.views.Viewport = Ext.extend(Ext.Panel, {
	showAnimation: 'fade',
	//html: 'Test Page',
	title: Ext.is.Phone ? 'Accounting' : 'Family Accounting',
	fullscreen: true,
	layout: 'card',
	items: [{
		cls: 'launchscreen',
		html: '<div><img src="../images/offline-logo.png" /><br /><br /><h1>Welcome to Family Accounting</h1><p><br /><br /><span>&copy;Offline studio, 2011</span></p></div>'
	}],
	// autoRender: true,
    initComponent: function() {
    	
    	this.navigationButton = new Ext.Button({
            hidden: Ext.is.Phone || Ext.Viewport.orientation == 'landscape',
            text: bundle.getText("accounting.common.title.navigator"),
            handler: this.onNavButtonTap,
            scope: this
        });

        this.backButton = new Ext.Button({
            text: bundle.getText("accounting.common.title.back"),
            ui: 'back',
            handler: this.onUiBack,
            hidden: true,
            scope: this
        });
        var btns = [this.navigationButton];
        if (Ext.is.Phone) {
            btns.unshift(this.backButton);
        }
        
        this.navigationBar = new Ext.Toolbar({
            ui: 'dark',
            dock: 'top',
            title: this.title,
            items: btns.concat(this.buttons || [])
        });
    	
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
    	this.dockedItems.unshift(this.navigationBar);
    	
    	if (!Ext.is.Phone && Ext.Viewport.orientation == 'landscape') {
            this.dockedItems.unshift(this.navigationPanel);
        }else if (Ext.is.Phone){
        	this.items = this.items || [];
        	this.items.unshift(this.navigationPanel);
        }
    	
    	
    	
    	AccountingApp.views.Viewport.superclass.initComponent.call(this);
    	// this.show();
    },
    
    onNavPanelItemTap: function(list, index, el, e) {
    	var record = list.getStore().getAt(index); 
    	
    	var key = record.get('key');
    	
        if (Ext.Viewport.orientation == 'portrait' && !Ext.is.Phone && !recordNode.childNodes.length && !preventHide) {
            this.navigationPanel.hide();
        }
        
        if (key == "accounting/input"){
        	this.setActiveItem(AccountingApp.frames.InputCard, 'slide');
        	this.currentCard = AccountingApp.frames.InputCard;
        }else if (key == "accounting/query"){
        	console.log(this);
        	this.setActiveItem(AccountingApp.frames.QueryCard, 'slide');
        	this.currentCard = AccountingApp.frames.QueryCard;
        }else if (key == "accounting/realtimereport"){
        	
        }else if (key == "accounting/historyreport"){
        	
        }
        
        if (Ext.is.Phone){
    		this.navigationBar.setTitle(this.currentCard.title || '');
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
        this.toggleUiBackButton();
//        this.fireEvent('navigate', this, record);
    },
    
    onNavButtonTap: function(){
    	console.log(this.navigationPanel);
    	this.navigationPanel.showBy(this.navigationButton, 'fade');
    },
    
    onUiBack: function() {
        var navPnl = this.navigationPanel;

        // if we already in the nested list
        if (this.getActiveItem() === navPnl) {
            navPnl.onBackTap();
        // we were on a demo, slide back into
        // navigation
        } else {
            this.setActiveItem(navPnl, {
                type: 'slide',
                reverse: true
            });
        }
        this.toggleUiBackButton();
        this.fireEvent('navigate', this, {});
    },
    
    toggleUiBackButton: function() {
        var navPnl = this.navigationPanel;

        if (Ext.is.Phone) {
            if (this.getActiveItem() === navPnl) {

                var currList      = navPnl.getActiveItem(),
                    currIdx       = navPnl.items.indexOf(currList),
                    recordNode    = currList.recordNode,
                    title         = navPnl.renderTitleText(recordNode),
                    parentNode    = recordNode ? recordNode.parentNode : null,
                    backTxt       = (parentNode && !parentNode.isRoot) ? navPnl.renderTitleText(parentNode) : this.title || '',
                    activeItem;


                if (currIdx <= 0) {
                    this.navigationBar.setTitle(this.title || '');
                    this.backButton.hide();
                } else {
                    this.navigationBar.setTitle(title);
                    if (this.useTitleAsBackText) {
                        this.backButton.setText(backTxt);
                    }

                    this.backButton.show();
                }
            // on a frame
            } else {
                activeItem = navPnl.getActiveItem();
                recordNode = activeItem.recordNode;
                backTxt    = (recordNode && !recordNode.isRoot) ? navPnl.renderTitleText(recordNode) : this.title || '';

                if (this.useTitleAsBackText) {
                    this.backButton.setText(backTxt);
                }
                this.backButton.show();
            }
            this.navigationBar.doLayout();
        }

    },
    
    layoutOrientation : function(orientation, w, h) {
        if (!Ext.is.Phone) {
            if (orientation == 'portrait') {
                this.navigationPanel.hide(false);
                this.removeDocked(this.navigationPanel, false);
                if (this.navigationPanel.rendered) {
                    this.navigationPanel.el.appendTo(document.body);
                }
                this.navigationPanel.setFloating(true);
                this.navigationPanel.setHeight(400);
                this.navigationButton.show(false);
            }
            else {
                this.navigationPanel.setFloating(false);
                this.navigationPanel.show(false);
                this.navigationButton.hide(false);
                this.insertDocked(0, this.navigationPanel);
            }
            this.navigationBar.doComponentLayout();
        }
        
        AccountingApp.views.Viewport.superclass.layoutOrientation.call(this, orientation, w, h);
    }
});