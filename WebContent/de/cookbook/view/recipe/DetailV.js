Ext.define('de.cookbook.view.recipe.DetailV', {

	requires : [ 'de.cookbook.controller.recipe.DetailC',
			'de.cookbook.store.IngredientS' ],

	extend : 'Ext.form.Panel',

	xtype : 'de_cookbook_view_recipe_detailv',

	controller : 'recipe_detailc',

	layout : 'vbox',

	bodyPadding : 5,
	
	defaults : {
		width : '100%',
		labelSeparator : null
	},

	items : [ {
		xtype : 'textfield',
		fieldLabel : 'name',
		name : 'name'
	}, {
		xtype : 'container',
		layout : 'hbox',

		items : [ {
			xtype : 'numberfield',
			fieldLabel : 'portions',
			name : 'portionsMin',
			labelSeparator : null,
			width : 160
		}, {
			xtype : 'numberfield',
			fieldLabel : '->',
			name : 'portionsMax',
			labelSeparator : null,
			labelWidth : 20,
			width : 80
		} ]
	}, {
		// ***ingredients***
		xtype : 'container',
		layout : {
			type : 'hbox',

		},

		items : [ {
			xtype : 'label',
			text : 'ingredients',
			width : 105
		}, {
			xtype : 'grid',
			flex : 1,
			itemId : 'ingredients',
			plugins : {
				ptype : 'cellediting',
				clicksToEdit : 1
			},

			selModel : 'cellmodel',
			store : Ext.create('de.cookbook.store.IngredientS'),
			columns : [ {
				text : 'amount',
				dataIndex : 'amount',
				editor : 'numberfield',
				flex : 1
			}, {
				text : 'unit',
				dataIndex : 'unit',
				editor : 'textfield',
				flex : 1
			}, {
				text : 'type',
				dataIndex : 'type',
				editor : 'textfield',
				flex : 1
			}, {
				xtype : 'actioncolumn',
				width : 20,
				items : [ {
					icon : 'extjs/theme/resources/images/dd/drop-no.png',
					tooltip : 'delete',
					handler : function(grid, rowIndex, colIndex) {
						var rec = grid.getStore().getAt(rowIndex);
						grid.getStore().remove(rec);
					}
				} ]
			} ]
		} ]

	}, {
		xtype : 'textfield',
		fieldLabel : 'new ingredient',

		listeners : {
			specialkey : 'enterIngredient'
		}
	}, {
		xtype : 'textarea',
		fieldLabel : 'description',
		name : 'description',
	}, {
		xtype : 'textarea',
		fieldLabel : 'comment',
		name : 'comment',
	}, {
		xtype : 'container',
		layout : {
			type : 'hbox',
			pack : 'end'
		},

		items : [ {
			xtype : 'button',
			text : 'save',
			handler : 'saveRecipe'
		}, {
			xtype : 'button',
			text : 'cancel',
			handler : 'cancel',
			margin : '0 0 0 5'
		} ]
	} ]
});