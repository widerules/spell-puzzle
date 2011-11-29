Ext.regModel('DetailsModel', {
	fields : [ 'id', {
		name : 'type',
		type : 'int'
	}, 'consumeTypeId', {
		name : 'consumeDate',
		type : 'string'
	}, 'target', {
		name : 'amount',
		type : 'float'
	}, 'consumeTypeValue', 'desc' ]
});

AccountingApp.stores.DetailsStore = new Ext.data.JsonStore({
	model : 'DetailsModel',
	sorters : 'consumeDate',
	// getGroupString : function(record) {
	// return record.get('lastName')[0];
	// },

	data : []
});