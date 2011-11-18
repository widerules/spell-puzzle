Ext.regModel('NavigationModel', {
    fields: [
        {name: 'text',  type: 'string'},
        {name: 'url',  type: 'string'},
        {name: 'key',  type: 'string'},
    ]
});

AccountingApp.stores.NavigationStore = new Ext.data.TreeStore({
    model: 'NavigationModel',
    root: {
        text: 'ROOT',
        expanded: true
    },
    proxy: {
        type: 'ajax',
        url: '../loadNavigator.action',
        autoLoad: true,
        reader: {
            type: 'tree',
            root: 'children'
        }
    }
});