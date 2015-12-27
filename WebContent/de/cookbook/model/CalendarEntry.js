Ext.define('de.cookbook.model.CalendarEntry', {
	extend : 'Ext.data.Model',

	idProperty : 'id',

	fields : [ {
		name : 'id'
	}, {
		name : 'date',
		type : 'int',
		allowNull : true
	}, {
		name : 'previousMonth',
		
		allowNull : true
	}, {
		name : 'previousMonthDate',
		type : 'date',
		allowNull : true
	}, {
		name : 'currentMonth',
		
		allowNull : true
	}, {
		name : 'currentMonthDate',
		type : 'date',
		allowNull : true
	}, {
		name : 'nextMonth',
		
		allowNull : true
	}, {
		name : 'nextMonthDate',
		type : 'date',
		allowNull : true
	} ]
});