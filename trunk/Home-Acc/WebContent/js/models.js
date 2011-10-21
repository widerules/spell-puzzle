Ext.define('TreeNode', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'text',  type: 'string'},
        {name: 'url',  type: 'string'},
        {name: 'key',  type: 'string'},
    ]
});

Ext.define('ConsumeType', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'text',  type: 'string'},
    ]
});

Ext.define('RecordModel',{
	extend: 'Ext.data.Model',
	fields: ['id', 'type', 'consumeTypeId', 'consumeDate', 'target', {name: 'amount', type: 'int'}, 'consumeTypeValue', 'desc'],
    proxy: {
        type: ('localStorage' in window && window['localStorage'] !== null) ? 'localstorage' : 'memory',
        id  : 'temp-input-data'
    }
});