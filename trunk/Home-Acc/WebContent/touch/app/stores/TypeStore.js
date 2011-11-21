Ext.regModel('TypeModel', {
	fields : [ {
		name : 'key',
		type : 'string'
	}, {
		name : 'value',
		type : 'string'
	} ]
});

AccountingApp.stores.TypeStore = new Ext.data.Store({
	model : 'TypeModel',
	data : [ {
		key : '-1',
		value : bundle.getText("accounting.input.type.credit")
	}, {
		key : '1',
		value : bundle.getText("accounting.input.type.debit")
	} ]
});