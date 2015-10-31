Ext.define('de.cookbook.view.calendar.GridV', {

	requires : [ 'de.cookbook.controller.calendar.GridC',
			'de.cookbook.view.component.DayV',
			'de.cookbook.model.CalendarEntry'],

	xtype : 'de_cookbook_view_calendar_gridv',

	extend : 'Ext.panel.Panel',

	controller : 'calendar_gridc',

	table : null,
	
	lastMonthDayCount : null,
	currentMonthDayCount : null,
	nextMonthDayCount : null,
	
	items : [ {
		xtype : 'container',
		layout : {
			type : 'hbox',
			pack : 'center',
		},
		
		padding : 5,
		width : '100%',
		
		items : [ {
			xtype : 'button',
			text : '<<',
			handler : 'selectPreviousMonth'
		}, {
			xtype : 'datefield',
			format : 'm/Y',
			itemId : 'date',
			padding: '0 5 0 5'
		}, {
			xtype : 'button',
			text : '>>',
			handler : 'selectNextMonth'
		} ]
	}, {
		xtype : 'grid',
		itemId : 'table',
		selModel : null,
		selType : null,
		border : true,
			
		columns : [{
			text : 'date',
			dataIndex : 'date',
			flex : 1,
			sortable : false
		},{
			text : 'previousMonth',
			dataIndex : 'previousMonth',
			flex : 5,
			sortable : false
		},{
			xtype:'actioncolumn',
            items: [{
                icon: 'images/add.png',
                handler: 'createAndShowPopupForPrevious',
                
                getClass: function(v, metadata, r, rowIndex, colIndex, store) {
                    // hide this action if row data flag indicates it is not deletable
                    if(!r.data.previousMonthDate) {
                        return "x-hidden-display";
                    }
                }
            }],
            width : 40,
			sortable : false
		},{
			text : 'currentMonth',
			dataIndex : 'currentMonth',
			flex : 5,
			sortable : false,
			renderer: function (value, metaData) {
				metaData.tdAttr = 'white-space: pre;'
				return value;
			}
		},{
			xtype:'actioncolumn',
            items: [{
            	icon: 'images/add.png',
                handler: 'createAndShowPopupForCurrent',
                
                getClass: function(v, metadata, r, rowIndex, colIndex, store) {
                    // hide this action if row data flag indicates it is not deletable
                    if(!r.data.currentMonthDate) {
                        return "x-hidden-display";
                    }
                }
            }],
            width : 40,
			sortable : false
		},{
			text : 'nextMonth',
			dataIndex : 'nextMonth',
			flex : 5,
			sortable : false
		},{
			xtype:'actioncolumn',
            items: [{
            	icon: 'images/add.png',
                handler: 'createAndShowPopupForNext',
                
                getClass: function(v, metadata, r, rowIndex, colIndex, store) {
                    // hide this action if row data flag indicates it is not deletable
                    if(!r.data.nextMonthDate) {
                        return "x-hidden-display";
                    }
                }
            }],
            width : 40,
			sortable : false
		}],

		store : Ext.create('de.cookbook.store.CalendarEntryS')
	} ],

	initComponent : function() {

		this.callParent(arguments);
		var date = Ext.Date.getFirstDateOfMonth(new Date());
		this.down('#date').setValue(date);
		
		this.table = this.down('#table');
		this.table.getSelectionModel().setLocked(true);
	},

	renderItems : function() {

		var date = this.down('#date').getValue();
		date.setDate(1);
		
		var previousDate = new Date(date.getTime());
		previousDate.setMonth(previousDate.getMonth() - 1);
		
		var nextDate = new Date(date.getTime());
		nextDate.setMonth(nextDate.getMonth() + 1);
		
		var nextDatePlus1 = new Date(date.getTime());
		nextDatePlus1.setMonth(nextDatePlus1.getMonth() + 2);

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
		
		this.table.getColumns()[1].setText(lastMonth);
		this.table.getColumns()[3].setText(currentMonth);
		this.table.getColumns()[5].setText(nextMonth);
		
		this.table.getStore().load({
			params : {
				begin : previousDate,
				end : nextDatePlus1
			}
		});
	}
});