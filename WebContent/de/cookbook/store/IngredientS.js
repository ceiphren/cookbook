Ext.define('de.cookbook.store.IngredientS', {
	extend : 'Ext.data.Store',

	model : 'de.cookbook.model.Ingredient',

	remoteFilter : true,

	proxy : {
		type : 'ajax',

		api : {
			create : 'recipe?object=ingredient&action=saveList',
			update : 'recipe?object=ingredient&action=saveList',
			read : 'recipe?object=ingredient&action=getList',
			destroy : 'recipe?object=ingredient&action=deleteList',
		},

		actionMethods : {
			read : 'POST'
		}
	}
});