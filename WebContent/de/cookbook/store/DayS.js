Ext.define('de.cookbook.store.DayS', {
	extend : 'Ext.data.Store',
	
	model : 'de.cookbook.model.DayEntry',

	proxy : {
		type : 'ajax',
		url : 'recipe.json'
	}
});