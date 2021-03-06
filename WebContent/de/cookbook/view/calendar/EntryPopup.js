Ext.define('de.cookbook.view.calendar.EntryPopup', {

	extend : 'Ext.window.Window',

	requires : [ 'de.cookbook.view.calendar.PopupC' ],

	closeAction : 'destroy',

	title : 'Recipe',

	width : 400,

	layout : 'vbox',

	controller : 'calendar_popupc',
	
	modal : true,
	
	listeners : {
		activate : 'initController'
	},

	bodyPadding : 5,
	defaults : {
		width : '100%'
	},

	items : [{
		xtype : 'datefield',
		itemId : 'date',
		fieldLabel : 'date'
	}, {
		xtype : 'textfield',
		itemId : 'text',
		fieldLabel : 'just text'
	}, {
		xtype : 'container',
		layout : 'hbox',
		items : [{
			xtype : 'combobox',
			itemId : 'recipeCombo',
			enableKeyEvents : true,
			fieldLabel : 'recipe',
			displayField : 'name',
			valueField : 'recordId',
			queryMode : 'local',

			store : new Ext.create('Ext.data.Store', {
				fields : [ 'recordId', 'name' ],
				//autoLoad : true,
				proxy : {
					type : 'ajax',
					url : 'recipe?object=recipe&action=getComboValues',
				}
			})
		}, {
			xtype : 'button',
			text : 'add recipe (F2)',
			handler : 'addRecipe',
			flex : 1
		} ]
	}, {
		xtype : 'container',
		layout : {
			type : 'hbox',
			pack : 'end'
		},

		items : [ {
			xtype : 'button',
			text : 'ok',
			handler : 'onOk'
		}, {
			xtype : 'button',
			text : 'cancel',
			margin : '0 0 0 5',
			handler : 'closeWindow'
		} ]
	} ]
});