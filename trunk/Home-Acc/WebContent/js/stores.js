Ext.create('Ext.data.Store', {
	storeId: 'RealTimeQueryMonthStore',
	fields: [{name: 'id', type: 'int'}, 'value'],
	data: (function(){
		var result = [];
		for (var i = 0; i < 16; i++){
			result[i] = {id: i + 3, value: i + 3};
		}
		return result;
	})()
});

