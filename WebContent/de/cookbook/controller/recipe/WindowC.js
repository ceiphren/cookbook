Ext.define('de.cookbook.controller.recipe.WindowC', {
	extend : 'Ext.app.ViewController',

	alias : 'controller.recipe_windowc',

	closeWindow : function() {

		this.getView().close();
	}
});