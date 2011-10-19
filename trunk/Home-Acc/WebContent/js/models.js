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