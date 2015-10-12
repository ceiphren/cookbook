Ext.define('de.cookbook.controller.calendar.PopupC', {
	extend : 'Ext.app.ViewController',

	alias : 'controller.calendar_popupc',

	initController : function() {

	},

	addRecipe : function() {
		var view = this.getView();
		console.log('you man');
		var combo = view.down('#recipeCombo');
		var recipeValue = combo.getValue();

		if (!combo.findRecordByValue(recipeValue)) {

			// value not found. show creation popup
			var record = Ext.create('de.cookbook.model.Recipe');
			record.set('name', recipeValue);

			var window = Ext.create('de.cookbook.view.recipe.WindowV');
			window.show();

			window.down('#detailv').on('saveSuccess', this.closeWindow, this);
			window.down('#detailv').loadRecord(record);
		} else {
			// value found. write it into the entry list for this date
		}
	},

	closeWindow : function() {

		this.getView().close();
	}
});