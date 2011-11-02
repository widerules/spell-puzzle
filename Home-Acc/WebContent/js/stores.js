Ext.create('Ext.data.Store', {
	storeId: 'RealTimeQueryMonthStore',
	fields: [{name: 'id', type: 'int'}, 'value'],
	data: (function(){
		var result = [];
		for (var i = 0; i < 12; i++){
			result[i] = {id: i + 1, value: i + 1};
		}
		return result;
	})()
});