Ext.define('de.cookbook.view.recipe.WindowV', {

	extend : 'Ext.window.Window',

	requires : [ 'de.cookbook.view.recipe.DetailV',
			'de.cookbook.controller.recipe.WindowC' ],

	controller : 'recipe_windowc',

	title : 'Recipe',
	height : 400,
	width : 500,
	layout : 'fit',
	modal : true,
	autoScroll: true,
	closeAction : 'destroy',

	items : [{
		xtype : 'de_cookbook_view_recipe_detailv',
		itemId : 'detailv',

		listeners : {
			cancel : 'closeWindow',
			saveSuccess : 'closeWindow'
		}
	} ]
});