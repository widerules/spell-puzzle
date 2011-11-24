Ext.regModel('TargetModel', {
    fields: [
        {name: 'name',  type: 'string'}, 
    ]
});

AccountingApp.stores.TargetStore = new Ext.data.Store({
    model: 'TargetModel',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url: '../loadCascadeTag.action'
    }
});
