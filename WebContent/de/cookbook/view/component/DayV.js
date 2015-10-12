Ext.define('de.cookbook.view.component.DayV', {

	requires : [],

	xtype : 'de_cookbook_view_component_dayv',

	extend : 'Ext.container.Container',

	store : Ext.create('de.cookbook.store.DayS'),

	// configs

	dayLabel : null,

	/**
	 * @override
	 */
	initComponent : function() {
		this.items = [

		// first row
		{
			xtype : 'container',
			layout : 'hbox',
			items : [ {
				xtype : 'label',
				text : this.dayLabel
			}, {
				xtype : 'grid',
				itemId : 'recipeGrid',
				flex : 1,
				columns : [ {
					text : 'Inhalt',
				} ]
			}, {
				xtype : 'button',
				text : '+',
				listeners : {
					click : 'addEntry'
				}
			} ]
		} ];

		this.callParent(arguments);
	}

});