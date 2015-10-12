Ext.define('de.cookbook.store.IngredientS', {
	extend : 'Ext.data.Store',

	model : 'de.cookbook.model.Ingredient',

	remoteFilter : true,

	proxy : {
		type : 'ajax',

		api : {
			create : 'recipe?action=saveList&object=ingredient',
			update : 'recipe?action=saveList&object=ingredient',
			read : 'recipe?object=ingredient&action=getList',
		},

		actionMethods : {
			read : 'POST'
		}
	}
});