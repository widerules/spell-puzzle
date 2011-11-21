Ext.regModel('NavigationModel', {
    fields: [
        {name: 'text',  type: 'string'},
        {name: 'url',  type: 'string'},
        {name: 'key',  type: 'string'},
    ]
});

AccountingApp.stores.NavigationStore = new Ext.data.TreeStore({
    model: 'NavigationModel',
    proxy: {
        type: 'ajax',
        url: '../loadNavigator.action',
        noCache: false,
        reader: {
        	type: 'tree',
        	root: 'children'
        }
    }
});