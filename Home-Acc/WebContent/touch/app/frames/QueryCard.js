AccountingApp.frames.QueryCard = new Ext.form.FormPanel(
		{
			url : '../queryRecord.action',
			standardSubmit : false,
			title : bundle.getText('menu.accounting.query'),
			// fullscreen: Ext.is.Phone,
			// autoRender: true,
			// floating: true,
			// modal: true,
			// centered: true,
			scroll : true,
			// showAnimation: 'fade',
			hideOnMaskTap : true,
			// height: 385,
			// width: 480,
			items : [
					{
						xtype : 'fieldset',
						title : bundle.getText('menu.accounting.query'),
						defaults : {
							required : true,
							labelAlign : 'left',
							labelWidth : '40%'
						},
						items : [{
							xtype : 'selectfield',
							label : bundle.getText("accounting.input.type"),
							name : 'type',
							store : AccountingApp.stores.TypeStoreWithAll,
							valueField : 'key',
							displayField : 'value'
						},{
							xtype : 'selectfield',
							name : 'consumeTypeId',
							// store:
							// AccountingApp.stores.NavigationStore,
							// valueField : 'key',
							// displayField : 'text',
							store : AccountingApp.stores.ConsumeTypeStore,
							valueField : 'key',
							displayField : 'value',
							label : bundle.getText("accounting.query.label.consume.type"),
							placeHolder : bundle.getText("accounting.input.type.tips")
						},{
							xtype : 'selectfield',
							name : 'target',
							store : AccountingApp.stores.TargetStore,
							label : bundle
									.getText("accounting.query.label.target"),
							value : '',
							valueField : 'name',
							displayField : 'name'
						},{
							xtype : 'textfield',
							name : 'desc',
							label : bundle
									.getText("accounting.input.desc"),
							maxLength : 60,
							maxRows : 8
						} ]
					},
					{
						xtype : 'fieldset',
						title : bundle
								.getText('accounting.query.label.date.range'),
						defaults : {
							required : true,
							labelAlign : 'left',
							labelWidth : '40%'
						},
						items : [
								{
									xtype : 'datepickerfield',
									name : 'startDate',
									label : bundle.getText("accounting.query.label.date.range.start"),
									value : new Date()
								},
								{
									xtype : 'datepickerfield',
									name : 'endDate',
									label : bundle.getText("accounting.query.label.date.range.end"),
									value : new Date()
								} ]
					},
					{
						xtype : 'fieldset',
						title : bundle
								.getText('accounting.query.label.amount.range'),
						defaults : {
							required : true,
							labelAlign : 'left',
							labelWidth : '40%'
						},
						items : [ {
							xtype : 'spinnerfield',
							name : 'startAmount',
							label : bundle.getText("accounting.query.label.amount.range.start")
						},{
							xtype : 'spinnerfield',
							name : 'endAmount',
							label : bundle.getText("accounting.query.label.amount.range.end")
						} ]
					} ],
			dockedItems : [ {
				xtype : 'toolbar',
				dock : 'bottom',
				items : [
						{
							xtype : 'spacer'
						},
						{
							text : bundle.getText("button.reset"),
							handler : function() {
								this.reset();
							}
						},
						{
							text : bundle.getText("button.submit"),
							ui : 'confirm',
							handler : function() {
								var form = this.up('form');

								var loadMask = new Ext.LoadMask(form.el, {
									msg : 'Loading...'
								});
								loadMask.show();

								// form.showMask('Loading...');

								form.submit({
											params : form.getValues(true),
											method : 'POST',
											success : function(form, action) {
												// location.href = 'app.action';
												// form.hideMask();
												
												loadMask.destroy();
												
												console.log(action);
												if (action.value && action.value.length == 0){
													Ext.Msg.alert(bundle.getText("accounting.common.msg.title.failure"), "Empty Records");
												}else{
													var queryList = new AccountingApp.frames.QueryList({'data': action.value});
													AccountingApp.views.viewport.setActiveItem(queryList, 'slide');
													AccountingApp.views.viewport.currentCard = queryList;
												}
												
												// Ext.Msg.alert(bundle.getText("accounting.common.msg.title.success"), bundle.getText("accounting.input.save.msg.success"));
												// this.hide();
												
												
											},
											failure : function(form, action) {
												// form.hideMask();
												console.log(form
														.getValues(true));
												Ext.Msg.alert(bundle.getText("accounting.common.msg.title.failure"), action.msg);
												loadMask.destroy();
											}
										// waitMsg : {message:'Submitting'}
										});
							}
						} ]
			} ]
		});

AccountingApp.frames.QueryList = Ext.extend(Ext.Panel, {
	data: [],
	title: 'List',
	scroll: 'vertical',
	items:[{
		
	}],
	initComponent: function(){
		var overlayTb = new Ext.Toolbar({
            dock: 'top'
        });
		
		this.overlay = new Ext.Panel({
            floating: true,
            modal: true,
            centered: false,
            width: Ext.is.Phone ? 260 : 400,
            height: Ext.is.Phone ? 220 : 400,
            styleHtmlContent: true,
            dockedItems: overlayTb,
            scroll: 'vertical',
//            contentEl: 'lipsum',
            cls: 'htmlcontent'
        });
		
		AccountingApp.stores.DetailsStore.loadData(this.data, false);
		
		this.itemList = new Ext.List({
//		    itemTpl : Ext.is.Phone? '<div style="float:left;">{consumeDate}</div> <div style="position: relative; float:left; left: 40%;">{consumeTypeValue}</div> <div style="float:right; padding-right: 20px; <tpl if="type === -1">color: red;</tpl>">{[values.type * values.amount]}</div>' 
//		    		: '<div style="float:left;">{consumeDate}</div> <div style="position: relative; float:left; left: 20%;">{consumeTypeValue}</div> <div style="float:right; padding-right: 20px; <tpl if="type === -1">color: red;</tpl>">{[values.type * values.amount]}</div>',
		    // grouped : true,
		    //indexBar: true,
//		    fullscreen: true,
		    
		    scroll: 'vertical',
		    store: AccountingApp.stores.DetailsStore,
		    listeners: {
		    	itemtap: function(list, index, item, event ){
		    		console.log(list);
		    	}
		    }
		});
		
		this.items = this.items || [];
		this.items.unshift(this.itemList);
		
		AccountingApp.frames.QueryList.superclass.initComponent.call(this, arguments);
	}
});

