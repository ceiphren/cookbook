Ext.define('de.cookbook.model.DayEntry', {
	extend : 'Ext.data.Model',

	idProperty : 'clientId',

	fields : [ {
		name : 'clientId'
	}, {
		name : 'date',
		type : 'date',
		allowNull : true
	}, {
		name : 'pureText',
		type : 'string',
		allowNull : true
	}, {
		name : 'recipeId',
		type : 'int',
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