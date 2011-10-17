<body>
<script type="text/javascript">
	Ext.onReady(function() {

		Ext.create('Ext.panel.Panel', {
			renderTo: 'inputForm',
			height: '100%',
			layout : {
				type : 'border',
				padding : 5
			},
			defaults : {
				split : true
			},
			items : [ {
				region : 'west',
				width : '30%',
				split : true,
				collapsible: false,
				items : [ {
					xtype : 'treepanel',
					store : Ext.create('Ext.data.TreeStore', {
						proxy : {
							type : 'ajax',
							url : 'loadConsumeType.action'
						},
						model : 'ConsumeType'
					}),
					rootVisible : false,
					listeners : {
						itemclick : {
							fn : function(view, record, item, index, e) {
								Ext.Msg.alert('title', str);
							}
						}
					}
				} ]
			}, {
				region : 'center',
				border : false,
				items : [ {
					xtype : 'combobox',
					fieldLabel : 'State',
					name : 'type',
					store : Ext.create('Ext.data.ArrayStore', {
						fields : [ 'key', 'value' ],
						data : [ [ '-1', 'Credit' ], [ '1', 'Debit' ] ]
					}),
					valueField : 'key',
					displayField : 'value',
					typeAhead : false,
					editable : false,
					queryMode : 'local',
					emptyText : 'Select a Type...'
				} ]
			} ]
		});

		//     tree = Ext.create('Ext.tree.Panel',{
		//     	renderTo: 'consume_tree',
		//     	title: 'Consume Type',
		//     	width: 200,
		//         height: 150,
		//     	store: Ext.create('Ext.data.TreeStore', {
		//             proxy: {
		//                 type: 'ajax',
		//                 url: 'loadConsumeType.action'
		//             },
		//             model: 'ConsumeType'
		//         }),
		//         rootVisible: false,
		//         listeners : {
		//             itemclick: {
		//                 fn: function (view, record, item, index, e){
		//                     Ext.Msg.alert('title', str);
		//                 }
		//             }
		//         }
		//         });

		//     inputForm = Ext.create('Ext.form.Panel', {
		//     	renderTo: 'inputForm',
		//     	url: 'saveDetails.action',
		//     	width: '100%',
		//     	frame: true,
		//     	fieldDefaults: {
		//             msgTarget: 'side',
		//             labelWidth: 100
		//         },
		//         layout: {
		//             type: 'border',
		//             padding: 5
		//         },
		//         defaults: {
		//             split: true
		//         },
		//         items :[{
		//             region: 'west',
		//             width: '30%',
		//             split: true,
		//             items: [{
		//                 xtype: 'treepanel',
		//                 store: Ext.create('Ext.data.TreeStore', {
		//                     proxy: {
		//                         type: 'ajax',
		//                         url: 'loadConsumeType.action'
		//                     },
		//                     model: 'ConsumeType'
		//                 }),
		//                 rootVisible: false,
		//                 listeners : {
		//                     itemclick: {
		//                         fn: function (view, record, item, index, e){
		//                             Ext.Msg.alert('title', str);
		//                         }
		//                     }
		//                 }
		//             }]
		//         },{
		//             region: 'center',
		//             border: false,
		//             items: [{
		//                 xtype: 'combobox',
		//                 fieldLabel: 'State',
		//                 name: 'type',
		//                 store: Ext.create('Ext.data.ArrayStore', {
		//                     fields: ['key', 'value'],
		//                     data : [
		//                             ['-1', 'Credit'],
		//                             ['1', 'Debit']
		//                     ]
		//                 }),
		//                 valueField: 'key',
		//                 displayField: 'value',
		//                 typeAhead: false,
		//                 editable: false,
		//                 queryMode: 'local',
		//                 emptyText: 'Select a Type...'
		//             }]
		//         }]
		//         items: [{
		//         	xtype:'fieldset',
		//             title: 'Input Record~',
		//         	defaultType: 'textfield',
		//             layout: {
		//                 type: 'border',
		//                 padding: 5
		//             },
		//             defaults: {
		//                 split: true
		//             },

		//         }]
		//     });
	});
</script>

<div id="inputForm" style="height: 100%; width: 100%"></div>
</body>