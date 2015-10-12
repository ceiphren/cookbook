Ext.define('de.cookbook.view.MainV', {

	extend : 'Ext.tab.Panel',

	requires : [ 'de.cookbook.view.recipe.GridV',
			'de.cookbook.view.calendar.GridV' ],

	xtype : 'de_cookbook_view_mainv',

	items : [ {
		title : 'Recipes',
		xtype : 'de_cookbook_view_recipe_gridv'
	}, {
		title : 'Calendar',
		xtype : 'de_cookbook_view_calendar_gridv',
		border : 10
	} ]

});