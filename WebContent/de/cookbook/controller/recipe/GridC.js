Ext.define('de.cookbook.controller.recipe.GridC', {
	extend : 'Ext.app.ViewController',

	alias : 'controller.recipe_gridc',
	
	requires : ['de.cookbook.view.recipe.WindowV'],

	createRecipe : function() {

		var record = Ext.create('de.cookbook.model.Recipe');

		this.createAndShowPopup(record);
	},

	selectRecipe : function() {

		var record = this.getView().getSelectionModel().getSelection()[0]

		this.createAndShowPopup(record);
	},

	createAndShowPopup : function(record) {

		var window = Ext.create('de.cookbook.view.recipe.WindowV');
		window.show();
		window.down('#detailv').on('saveSuccess', this.reloadStore, this);
		window.down('[itemId=detailv]').getController().setUp(record);
	},

	reloadStore : function() {
		
		this.getView().getStore().load();
	}

});