Ext.define('de.cookbook.model.Recipe', {
	extend : 'Ext.data.Model',

	idProperty: '@rid',

	fields : [ {
		name : '@rid',
		type : 'string',
		allowNull : true
	}, {
		name : 'name',
		type : 'string',
		allowNull : true
	}, {
		name : 'portionsMin',
		type : 'int',
		allowNull : true
	}, {
		name : 'portionsMax',
		type : 'int',
		allowNull : true
	}, {
		name : 'description',
		type : 'string',
		allowNull : true
	}, {
		name : 'comment',
		type : 'string',
		allowNull : true
	} ],

	proxy : {
		writer : {
			type : 'json',
			//Needed to send ALL fields (not only the edited ones) to the server. Otherwise
			//the server has something that is in fact the wrong model
			writeAllFields:true
		},
		
		type : 'ajax',

		api : {
			create : 'recipe?action=save&object=recipe',
			update : 'recipe?action=save&object=recipe'
		}
	},
	
	reader : {
		type : 'json',
		root : 'data'
	}
});