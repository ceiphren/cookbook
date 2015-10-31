Ext.define('de.cookbook.controller.calendar.PopupC', {
	extend : 'Ext.app.ViewController',

	alias : 'controller.calendar_popupc',

	initController : function() {
		var recipeCombo = this.getView().down('#recipeCombo');
		recipeCombo.getStore().load();
	},

	onOk : function() {
		var view = this.getView();

		var dateValue = view.down('#date').getValue();
		var text = view.down('#text').getValue();
		var recipeId = view.down('#recipeCombo').getValue();

		var record = Ext.create('de.cookbook.model.DayEntry');
		record.set('date', dateValue);
		record.set('text', text);
		record.set('recipeId', recipeId);

		record.save({
			callback : this.closeWindow,
			scope : this
		});
	},

	addRecipe : function() {
		var view = this.getView();

		var combo = view.down('#recipeCombo');
		var recipeValue = combo.getValue();

		var record = Ext.create('de.cookbook.model.Recipe');
		
		if (!combo.findRecordByValue(recipeValue)) {

			// value not found. show creation popup
			record.set('name', recipeValue);

		}

		var window = Ext.create('de.cookbook.view.recipe.WindowV');
		window.show();

		window.down('#detailv').on('saveSuccess', this.setRecipe, this);
		window.down('#detailv').getController().setUp(record);
	},

	setRecipe : function(record) {
		var view = this.getView();
		var combo = view.down('#recipeCombo');
		var newEntry = {
			recordId : record.get('@rid'),
			name : record.get('name')
		};

		combo.getStore().add(newEntry);
		combo.select(newEntry.recordId);
	},

	closeWindow : function() {

		this.getView().close();
	}
});