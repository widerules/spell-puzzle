
Ext.regModel('ConsumeTypeModel', {
    fields: [
        {name: 'key',  type: 'string'}, 
        {name: 'value', type: 'string'}, 
        {name: 'originValue', type: 'string'}
    ]
});

AccountingApp.stores.ConsumeTypeStore = new Ext.data.Store({
    model: 'ConsumeTypeModel',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url: '../loadCascadeConsumeType.action'
    }
});

