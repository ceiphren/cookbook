Ext.define('de.cookbook.view.calendar.GridC', {
	extend : 'Ext.app.ViewController',

	requires : [ 'de.cookbook.view.calendar.EntryPopup' ],

	alias : 'controller.calendar_gridc',

	init : function() {
		
		this.getView().renderItems();
	},
	
	createAndShowPopupForPrevious : function(view, rowIdx, colIdx, item, e, record, row) {
		var date = record.get('previousMonthDate');
		this.createAndShowPopup(date);
	},
	
	createAndShowPopupForCurrent : function(view, rowIdx, colIdx, item, e, record, row) {
		var date = record.get('currentMonthDate');
		this.createAndShowPopup(date);
	},
	
	createAndShowPopupForNext : function(view, rowIdx, colIdx, item, e, record, row) {
		var date = record.get('nextMonthDate');
		this.createAndShowPopup(date);
	},
	
	loadRecipe : function(recipeId){
		
		de.cookbook.model.Recipe.load(recipeId, { 
			callback: this.showRecipe,
			scope : this
		});
		
		return false;
	},

	showRecipe : function(recipe) {

		var window = Ext.create('de.cookbook.view.recipe.WindowV');
		window.show();

		window.down('#detailv').on("saveSuccess", this.getView().renderItems, this.getView());
		window.down('#detailv').getController().setUp(recipe);
	},

	createAndShowPopup : function(date) {

		var window = Ext.create('de.cookbook.view.calendar.EntryPopup');
		window.down('#date').setValue(date);

		window.on('close', this.getView().renderItems, this.getView());
		window.show();
	},

	selectPreviousMonth : function() {

		var date = this.getDateField().getValue();
		date = Ext.Date.add(date, Ext.Date.MONTH, -1)

		this.getDateField().setValue(date);

		this.getView().renderItems();
	},

	selectNextMonth : function() {
		var date = this.getDateField().getValue();
		date = Ext.Date.add(date, Ext.Date.MONTH, 1)

		this.getDateField().setValue(date);

		this.getView().renderItems();
	},

	getDateField : function() {
		return this.getView().down('#date');
	}
});