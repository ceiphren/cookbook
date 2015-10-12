Ext.define('de.cookbook.controller.recipe.DetailC', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.recipe_detailc',

	setUp : function(record) {
		this.getView().loadRecord(record);

		var ingredientsGrid = this.getView().down('#ingredients');

		var recipeId = record.get('id');
		console.log('load ingredients');

		if (recipeId !== null) {
			ingredientsGrid.getStore().load({
				params : {
					recipeId : recipeId
				}
			});
		} else {
			ingredientsGrid.getStore().removeAll();
		}
	},

	enterIngredient : function(field, e) {

		if (e.getKey() === e.ENTER) {

			var value = field.getValue();

			var ingredientParts = value.split(' ');

			var amount = null;
			var unit = null;
			var type = null;

			if (ingredientParts === null || ingredientParts.length === 0) {
				// no valid input found
				return;
			} else if (ingredientParts.length === 2) {

				amount = ingredientParts[0];
				type = ingredientParts[1];
			} else if (ingredientParts.length === 1) {
				type = ingredientParts[0];
			} else {
				amount = ingredientParts[0];
				unit = ingredientParts[1];
				type = ingredientParts[2];
			}

			var ingredients = this.getView().down('#ingredients');

			var ingredient = Ext.create('de.cookbook.model.Ingredient');
			ingredient.data.amount = amount;
			ingredient.data.unit = unit;
			ingredient.data.type = type;

			ingredients.getStore().add(ingredient);

			field.setValue('');
		}
	},

	saveRecipe : function() {

		this.getView().updateRecord();

		this.getView().getRecord().save({
			callback : this.afterSave,
			scope : this
		});

		this.getView().setLoading(true);
	},

	afterSave : function(record, opts, arg2) {

		// now after the record is saved it can be guaranteed that it has an ID
		// which we need for the ingredients

		this.getView().loadRecord(record);
		var recipeId = record.get('id');

		Ext.each(this.getView().down('#ingredients').getStore().data.items,
				function(value) {
					value.set('recipeId', recipeId);
				})

		this.getView().down('#ingredients').getStore().save({
			callback : this.afterSaveIngredients,
			scope : this
		});

	},

	afterSaveIngredients : function() {
		// TODO: error handling would be nice...
		this.getView().setLoading(false);
		this.getView().fireEvent('saveSuccess', this.getView().getRecord());

	},

	cancel : function() {
		// currently not much to do. But the event is catched by the
		// popup-window
		// which will close and destroy itself (mehehehehe)
		this.getView().fireEvent('cancel');
	}
});