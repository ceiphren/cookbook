Ext.define('de.cookbook.view.recipe.GridV', {

	extend : 'Ext.grid.Panel',
	
	requires : [ 'de.cookbook.controller.recipe.GridC' ],

	xtype : 'de_cookbook_view_recipe_gridv',

	autoLoad : true,

	controller : 'recipe_gridc',

	store : Ext.create('de.cookbook.store.RecipeS'),
	
	listeners : {
		select : 'selectRecipe'
	},

	columns : [ {
		text : 'Name',
		dataIndex : 'name'
	}, {
		text : 'Description',
		dataIndex : 'description',
		flex : 1
	} ],

	tbar : [ {
		xtype : 'button',
		text : 'create',
		
		handler : 'createRecipe'
	} ]
});