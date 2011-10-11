Ext.application({
    name: 'Family Accounting',
    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [
                {
                    title: 'Family Accounting',
                    html : 'Hello! Welcome to Ext JS.'
                }
            ]
        });
    }
});