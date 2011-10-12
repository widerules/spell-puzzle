<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Family Accounting</title>
<link rel="stylesheet" type="text/css" href="extjs/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="extjs/resources/css/doc/docs-ext.css" />
<link rel="stylesheet" type="text/css" href="extjs/resources/css/doc/reset.css" />
<link rel="stylesheet" type="text/css" href="extjs/resources/css/doc/scrollbars.css" />
<link rel="stylesheet" type="text/css" href="extjs/resources/css/doc/viewport.css" />
<script type="text/javascript" src="extjs/ext-all.js"></script>
<!-- <script type="text/javascript" src="extjs/resources/locale/ext-lang-zh_CN.js"></script> -->
<script type="text/javascript">
	Ext.onReady(function() {
		Ext.create('Ext.form.Panel', {
			title : 'Family Accounting',
			floating : true,
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'login.action',

			// Fields will be arranged vertically, stretched to full width
			layout : {
				type : 'anchor'
			},
			defaults : {
				anchor : '100%'
			},

			// The fields
			defaultType : 'textfield',
			items : [ {
				xtype : 'panel',
				width : '100%',
				height : 40,
				border : 0,
				layout : {
					type : 'vbox',
					align : 'center'
				},
				defaults : {
					anchor : '100%'
				},
				items : [ {
					xtype : 'label',
					text : 'Login',
					style : {
						fontSize : '24px'
					}
				} ]
			}, {
				fieldLabel : 'User Name',
				name : 'username',
				allowBlank : false
			}, {
				fieldLabel : 'Password',
				inputType : 'password',
				name : 'password',
				allowBlank : false
			} ],

			// Reset and Submit buttons
			buttons : [ {
				text : 'Reset',
				handler : function() {
					this.up('form').getForm().reset();
				}
			}, {
				text : 'Submit',
// 				formBind : true, //only enabled once the form is valid
// 				disabled : true,
				handler : function() {
					var form = this.up('form').getForm();
					if (form.isValid()) {
						form.submit({
							success : function(form, action) {
                                location.href = 'app.action';
							},
							failure : function(form, action) {
								Ext.Msg.alert('Failed', action.result.msg);
							},
							waitMsg : 'Submiting...'
						});
					} else {

					}
				}
			}],
			renderTo : Ext.getBody()
		});
	});
</script>
</head>
<body>

</body>
</html>