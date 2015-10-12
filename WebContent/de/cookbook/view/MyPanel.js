Ext.define('de.cookbook.view.MyPanel', {

	extend : 'Ext.Panel',

	xtype : 'de_cookbook_view_mypanel',

	title : 'Hello Parent',

	html : 'This is the inner panel content',

	items : [ {
		xtype : 'button',
		text : 'load',
		handler : function() {
			var store = Ext.create('de.cookbook.store.RecipeS');

			store.load({
				params : {
					clientId : 123,
					queryType : 'default'
				}
			});
		}
	},{
		xtype : 'button',
		text : 'save',
		handler : function(){
			var model = Ext.create('de.cookbook.model.Recipe');
			
			model.data.id = 123;
			model.data.name= 'Kartoffelpuffer';
			
			model.save('');
		}
	} ]
});