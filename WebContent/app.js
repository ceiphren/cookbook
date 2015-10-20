
Ext.Loader.setConfig({
    disableCaching: false
});
Ext.Ajax.disableCaching = false;
Ext.application({
	name : 'MyApp',

	requires : [ 'de.cookbook.view.MainV' ],

	launch : function() {

		Ext.create('Ext.Panel', {
			renderTo : Ext.getBody(),
			
			bodyPadding : 5,
			layout : 'fit',

			items : [ {
				//xtype : 'de_cookbook_view_recipe_detailv',
				xtype : 'de_cookbook_view_mainv',

			} ]
		});

	}
});