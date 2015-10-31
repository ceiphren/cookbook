Ext.define('de.cookbook.store.CalendarEntryS', {
	extend : 'Ext.data.Store',

	model : 'de.cookbook.model.CalendarEntry',

	remoteFilter : true,

	proxy : {
		type : 'ajax',

		api : {
			read : 'recipe?object=dayentry&action=getCalendarEntries'
		},

		actionMethods : {
			read : 'POST'
		}
	}
});