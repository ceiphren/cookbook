Ext.define('de.cookbook.view.calendar.GridV', {

	requires : [ 'de.cookbook.controller.calendar.GridC',
			'de.cookbook.view.component.DayV' ],

	xtype : 'de_cookbook_view_calendar_gridv',

	extend : 'Ext.panel.Panel',

	controller : 'calendar_gridc',

	table : null,
	
	lastMonthDayCount : null,
	currentMonthDayCount : null,
	nextMonthDayCount : null,
	
	items : [ {
		xtype : 'container',
		layout : 'hbox',
		items : [ {
			xtype : 'button',
			text : 'to ze left',
			handler : 'selectPreviousMonth'
		}, {
			xtype : 'datefield',
			format : 'm/Y',
			itemId : 'date',
			flex : 1,
			value : new Date()
		}, {
			xtype : 'button',
			text : 'to ze right',
			handler : 'selectNextMonth'
		} ]
	}, {
		xtype : 'container',
		itemId : 'table'
	} ],

	initComponent : function() {

		this.callParent(arguments);
	},

	renderItems : function() {

		this.down('#table').removeAll();
		
		this.table = Ext.create('Ext.container.Container',{

			itemId : 'table',
			layout : {
				type : 'table',
				columns : 4,
				tableAttrs : {
					style : {
						width : '100%',
						height : '100%'
					}
				}
			}
		});
		
		this.createHeader();
		
		for (var dayCounter = 0; dayCounter < 31; dayCounter++) {
			this.table.add(Ext.create('Ext.form.Label',{
				text : dayCounter+1
			}));
			
			if(dayCounter < this.lastMonthDayCount){
				var c = this.createContainer();
				this.table.add(c);
			}else{
				var c = this.createEmptyContainer();
				this.table.add(c);
			}
			
			if(dayCounter < this.currentMonthDayCount){
				var c = this.createContainer();
				this.table.add(c);
			}else{
				var c = this.createEmptyContainer();
				this.table.add(c);
			}
			
			if(dayCounter < this.nextMonthDayCount){
				var c = this.createContainer();
				this.table.add(c);
			}else{
				var c = this.createEmptyContainer();
				this.table.add(c);
			}
		}
		
		this.down('#table').add(this.table);
	},
	
	createContainer : function(){
		var entry = Ext.create('Ext.container.Container',{
			layout : 'hbox',
			items : [{
				xtype : 'textareafield',
				value : 'lorem ipsum',
				flex  :1
			},{
				xtype : 'button',
				text : 'hi'
			}]
		});
		
		return entry;
	},
	
	createEmptyContainer : function(){
		var entry = Ext.create('Ext.container.Container',{
			
		});
		
		return entry;
	},

	createHeader : function() {

		var date = this.down('#date').getValue();
		
		var previousDate = new Date(date.getTime());
		previousDate.setMonth(previousDate.getMonth() - 1);
		
		var nextDate = new Date(date.getTime());
		nextDate.setMonth(nextDate.getMonth() + 1);

		this.lastMonthDayCount = Ext.Date.getDaysInMonth(previousDate);
		this.currentMonthDayCount = Ext.Date.getDaysInMonth(date);
		this.nextMonthDayCount = Ext.Date.getDaysInMonth(nextDate);

		var currentMonth = Ext.Date.monthNames[date.getMonth()]

		var lastValue = 11
		if (date.getMonth() != 0) {
			lastValue = date.getMonth() - 1;
		}
		var lastMonth = Ext.Date.monthNames[lastValue]

		var nextMonth = Ext.Date.monthNames[(date.getMonth() + 1) % 12]

		// create headers
		var h0 = Ext.create('Ext.container.Container', {

			items : [ {
				xtype : 'label',
				text : ''
			} ]
		});
		
		var h1 = Ext.create('Ext.container.Container', {

			items : [ {
				xtype : 'label',
				text : lastMonth
			} ]
		});

		var h2 = Ext.create('Ext.container.Container', {

			items : [ {
				xtype : 'label',
				text : currentMonth,

			} ]
		});

		var h3 = Ext.create('Ext.container.Container', {

			items : [ {
				xtype : 'label',
				text : nextMonth
			} ]
		});

		this.table.add(h0);
		this.table.add(h1);
		this.table.add(h2);
		this.table.add(h3);
	}

});