Ext.define('de.cookbook.model.DayEntry', {
	extend : 'Ext.data.Model',

	idProperty : 'clientId',

	fields : [ {
		name : 'clientId',
		type : 'string',
		allowNull : true
	}, {
		name : 'date',
		type : 'date',
		allowNull : true
	}, {
		name : 'text',
		type : 'string',
		allowNull : true
	}, {
		name : 'recipeId',
		type : 'string',
		allowNull : true
	} ],

	proxy : {
		type : 'ajax',

		api : {
			create : 'recipe?action=save&object=dayEntry',
			update : 'recipe?action=save&object=dayEntry'
		}
	},

	reader : {
		type : 'json',
		root : 'data'
	}
});