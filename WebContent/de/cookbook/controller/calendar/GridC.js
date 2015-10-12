Ext.define('de.cookbook.controller.calendar.GridC', {
	extend : 'Ext.app.ViewController',

	requires : [ 'de.cookbook.view.calendar.EntryPopup' ],

	alias : 'controller.calendar_gridc',

	onShow : function() {

		this.getView().renderItems();
	},

	addEntry : function(button, e, eOpts) {

		var x = button.getX();
		var y = button.getY() + button.getHeight();

		this.createAndShowPopup(x, y);

	},

	createAndShowPopup : function(x, y) {

		var window = Ext.create('de.cookbook.view.calendar.EntryPopup');

		window.show();

		window.setPagePosition(x, y);
	}

});