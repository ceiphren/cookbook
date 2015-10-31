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
		type : 'string',
		allowNull : true
	}, {
		name : 'previousMonthDate',
		type : 'date',
		allowNull : true
	}, {
		name : 'currentMonth',
		type : 'string',
		allowNull : true
	}, {
		name : 'currentMonthDate',
		type : 'date',
		allowNull : true
	}, {
		name : 'nextMonth',
		type : 'string',
		allowNull : true
	}, {
		name : 'nextMonthDate',
		type : 'date',
		allowNull : true
	} ]
});