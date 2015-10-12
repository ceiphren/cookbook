Ext.define('de.cookbook.store.RecipeS', {
	extend : 'Ext.data.Store',

	model : 'de.cookbook.model.Recipe',

	proxy : {
		type : 'ajax',
		url : 'recipe?object=recipe&action=getList',
	}
});