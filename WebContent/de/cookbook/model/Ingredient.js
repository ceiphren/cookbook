Ext.define('de.cookbook.model.Ingredient', {
	extend : 'Ext.data.Model',

	idProperty : '@rid',

	fields : [ {
		name : '@rid',
		type : 'string',
		allowNull : true
	}, {
		name : 'recipeId',
		type : 'string',
		allowNull : true
	}, {
		name : 'amount',
		type : 'int',
		allowNull : true
	}, {
		name : 'unit',
		type : 'string',
		allowNull : true
	}, {
		name : 'type',
		type : 'string',
		allowNull : true
	} ],

	proxy : {
		type : 'ajax',

		api : {
			create : 'recipe?method=save&type=recipe',
			update : 'recipe?method=save&type=recipe'
		}
	},

	reader : {
		type : 'json',
		root : 'data'
	}
});