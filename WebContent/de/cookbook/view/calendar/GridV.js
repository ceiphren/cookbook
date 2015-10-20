Ext.define('de.cookbook.view.calendar.GridV', {

	requires : [ 'de.cookbook.controller.calendar.GridC',
	             'de.cookbook.view.component.DayV'],

	xtype : 'de_cookbook_view_calendar_gridv',

	extend : 'Ext.panel.Panel',
	
	controller : 'calendar_gridc',

	layout : {
		type : 'table',
		columns : 3,
		tableAttrs : {
			style : {
				width : '100%',
				height : '100%'
			}
		}
	},

	listeners : {
		show : 'onShow'
	},

	renderItems : function() {

		this.removeAll();

		// create headers
		var h1 = Ext.create('Ext.container.Container', {

			items : [ {
				xtype : 'label',
				text : 'Dezember'
			} ]
		});

		var h2 = Ext.create('Ext.container.Container', {

			items : [ {
				xtype : 'label',
				text : 'Januar',

			} ]
		});

		var h3 = Ext.create('Ext.container.Container', {

			items : [ {
				xtype : 'label',
				text : 'Februar'
			} ]
		});

		// create date fields
		var c1 = Ext.create('Ext.container.Container', {

			items : [
			// first row
			{
				xtype : 'container',
				layout : 'hbox',
				items : [ {
					xtype : 'label',
					text : '1'
				}, {
					flex : 1
				}, {
					xtype : 'button',
					text : '+',
					listeners : {
						click : 'addEntry'
					}
				} ]
			},
			//the grid
			{
				xtype : 'grid'
			} ]
		});
		
		var c12 = Ext.create('de.cookbook.view.component.DayV',{
			dayLabel : 'horst'
		});

		// create date fields
		var c2 = Ext.create('Ext.container.Container', {

			items : [ {
				xtype : 'container',
				layout : 'hbox',
				items : [ {
					xtype : 'label',
					text : '1'
				}, {
					xtype : 'button',
					text : '+'
				} ]
			} ]
		});

		// create date fields
		var c3 = Ext.create('Ext.container.Container', {

			items : [ {
				xtype : 'container',
				layout : 'hbox',
				items : [ {
					xtype : 'label',
					text : '1'
				}, {
					xtype : 'button',
					text : '+'
				} ]
			} ]
		});

		this.add(h1);
		this.add(h2);
		this.add(h3);
		this.add(c12);
		this.add(c2);
		this.add(c3);
	}

});