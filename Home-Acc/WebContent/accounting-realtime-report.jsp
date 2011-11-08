<script type="text/javascript">
<!--
selectedStoreItem = false;

function buildRealtimeReportTab() {

	dateLike = '';
    type = -1;
    by = 'consumeType';
	
	pieStore = Ext.create('Ext.data.Store', {
		fields: [ {name: 'key', type: 'string'}, {name: 'amount',  type: 'float'}],
		data: [{key: 'N/A', amount: 0}],
		pageSize: 100,
        autoLoad: true
	});
	
    lineStore = Ext.create('Ext.data.Store', {
        fields: [ {name: 'key', type: 'string'}, {name: 'amount',  type: 'float'}],
        data: [],
        pageSize: 100,
        autoLoad: true
    });
    
    gridStore = Ext.create('Ext.data.Store', {
        model: 'RecordModel',
        data: [],
        pageSize: 100,
        autoLoad: false
    });

    refreshPie = function(type, dateLike, by){
    	pieStore.setProxy({
            type: 'ajax',
            url: 'realtimeConsumeTypeReport.action?dateLike=' + dateLike + '&type=' + type + '&by=' + by
        });
        pieStore.load();
    };
	
	refreshData = function(type, dateLike){
		 Ext.getCmp('piePanel').setTitle(dateLike);
		 refreshPie(type, dateLike, by);
         
         lineStore.setProxy({
             type: 'ajax',
             url: 'realtimeDailyReport.action?dateLike=' + dateLike + '&type=' + type
         });
         lineStore.load();
         
         gridStore.setProxy({
             type: 'ajax',
             url: 'realtimeDetailReport.action?dateLike=' + dateLike + '&type=' + type
         });
         gridStore.load();
	};
	
	pieChart = Ext.create('Ext.chart.Chart', {
		id: 'pieChart',
        store: pieStore,
        animate: true,
        //shadow: true,
        theme: 'Base:gradients',
        legend: {
            position: 'right'
        },
        series: [{
        	type: 'pie',
            field: 'amount',
            showInLegend: true,
            tips: {
              trackMouse: true,
              width: 140,
              height: 28,
              renderer: function(storeItem, item) {
                //calculate percentage.
                var total = 0;
                pieStore.each(function(rec) {
                    total += rec.get('amount');
                });
                this.setTitle(storeItem.get('key') + ': ' + storeItem.get('amount') + '(' + Math.round(storeItem.get('amount') / total * 100) + '%)');
              }
            },
            highlight: {
              segment: {
                margin: 20
              }
            },
            label: {
                field: 'key',
                display: 'rotate',
                contrast: true,
                font: '18px Arial'
            },
            clickedItem: null,
            listeners: {
                itemmousedown: function(item){
                    this.clickedItem = item;
                },
                itemmouseup: function(item) {
                    if (this.clickedItem == item){
                          console.log(item);
                    }
                    this.clickedItem = null;
               }
            }
        }]
    });
	
	lineChart = Ext.create('Ext.panel.Panel', {
		border: false,
		height: '50%',
		width: '100%',
		layout: 'fit',
		items:[{
		       xtype: 'chart',
		       store: lineStore,
		        animate: true,
		        shadow: true,
		        height: 300,
		        width: '100%',
		        theme: 'Base:gradients',
		        axes: [{
		            type: 'Numeric',
		            minimum: 0,
		            position: 'left',
		            fields: ['amount'],
		            title: '<%= i18n.getI18nText("accounting.realtime.report.daily.axes.y.title") %>',
		            grid: {
		                odd: {
		                    opacity: 1,
		                    fill: '#ddd',
		                    stroke: '#bbb',
		                    'stroke-width': 0.5
		                }
		            }
		        }, {
		            type: 'Category',
		            position: 'bottom',
		            fields: ['key'],
<%-- 		            title: '<%= i18n.getI18nText("accounting.realtime.report.daily.axes.x.title") %>', --%>
		            label: {
		                rotate: {
		                    degrees: 315
		                }
		            }
		        }],
		        series: [{
		            type: 'line',
		            highlight: {
		                size: 7,
		                radius: 7
		            },
		            axis: 'left',
		            xField: 'consumeDate',
		            yField: 'amount',
		            tips: {
                        trackMouse: false,
                        width: 140,
                        height: 28,
                        renderer: function(storeItem, item) {
                          this.setTitle(storeItem.get('key') + ': ¥ ' + storeItem.get('amount'));
                        }
                    },
		            markerConfig: {
		                type: 'circle',
		                size: 4,
		                radius: 4,
		                'stroke-width': 0
		            }
		        }]
		}]
	});
	
	detailGrid = Ext.create('Ext.panel.Panel', {
		border: false,
        width: '100%',
        height: '50%',
        layout: 'fit',
        defaults: {
            padding: 4
        },
        items : [ {
            xtype:'grid',
            autoScroll: true,
            title: '<%= i18n.getI18nText("accounting.input.record") %>',
            store: gridStore,
            features: [{
                ftype: 'summary'
            }],
            columns: [
                      {header: '<%= i18n.getI18nText("accounting.input.type") %>',  dataIndex: 'type', flex: 1, renderer: function(value, metaData, record){
                          if (value == 1){
                              return '<%= i18n.getI18nText("accounting.input.type.debit") %>';
                          }else if (value == -1){
                              return '<%= i18n.getI18nText("accounting.input.type.credit") %>';
                          }
                      }},
                      {header: '<%= i18n.getI18nText("accounting.input.consume.type") %>', dataIndex: 'consumeTypeId', flex: 1,  renderer: function(value, metaData, record){
                          return record.data.consumeTypeValue;
                      }},
                      
                      {header: '<%= i18n.getI18nText("accounting.input.target") %>', dataIndex: 'target', flex: 1},
                      {header: '<%= i18n.getI18nText("accounting.input.date") %>', dataIndex: 'consumeDate', flex: 1,
                          xtype: 'datecolumn',
                          format: 'Y-m-d',
                          summaryRenderer: function(value, metaData, record){
                                return '<string>Sum:</string>';
                          }},
                      {header: '<%= i18n.getI18nText("accounting.input.amount") %>', dataIndex: 'amount', 
                        	  renderer: function(value, metaData, record){
                                  if (record.data.type == -1){
                                      return '<font style="color: red;">' + Ext.util.Format.currency(value * record.data.type, '¥', 2) + '</font>';
                                  }else{
                                      return Ext.util.Format.currency(value, '¥', 2);
                                  }
                              }, 
                              flex: 1, 
                              summaryType: function(field){
                                  sum = 0;
                                  for (i in field){
                                      sum += field[i].data.amount * field[i].data.type;
                                  }
                                  return sum;
                              }, 
                              summaryRenderer: function(value, summaryData, dataIndex) {
                                  if (value < 0){
                                      return '<font style="color: red;">' + Ext.util.Format.currency(value, '¥', 2) + '</font>'; 
                                  }
                                  return Ext.util.Format.currency(value, '¥', 2); 
                              }
                      },{
                          header: '<%= i18n.getI18nText("accounting.input.desc") %>', dataIndex: 'desc', flex: 1
                      }
                  ]
        } ]
	});

    selectBarchartItem = function(storeItem){

    	var series = Ext.getCmp('fullBarChart').series.get(0);

        var items, i, l;
        var dateLike = storeItem.get('key');

        for (i = 0, items = series.items, l = items.length; i < l; i++) {
            if (dateLike == items[i].storeItem.get('key')) {
            	selectedStoreItem = items[i].storeItem;
                console.log(items[i]);
                series.highlightItem(items[i]);
                break;
            }
        }
    };

    var timer = false;
		
		
    return Ext.create('Ext.panel.Panel', {
    	  title: '<%= i18n.getI18nText("menu.accounting.report.realtime") %>',
          layout : {
            type : 'border'
          },
          defaults : {
            split : true
            
          },
          items: [{
        	  region: 'north',
              height: '40%',
              layout: 'fit',
              hideCollapseTool: true,
              collapsible: true,
              defaults : {
                  border: false,
                  split : true
              },
              tbar: [{
                  xtype: 'combo',
                  id: 'realtime-report-params-month',
                  fieldLabel: '<%= i18n.getI18nText("accounting.realtime.report.label.month") %>',
                  store: 'RealTimeQueryMonthStore',
                  value: 6,
                  valueField : 'id',
                  displayField : 'value',
                  queryMode: 'local'
              }, {
                  text: '<%= i18n.getI18nText("accounting.realtime.report.full.reload") %>',
                  handler: function() {
                      store = Ext.getStore('realtime-year-month-report');
                      store.setProxy({
                    	  type: 'ajax',
                          url: 'realtimeReport.action?month=' + Ext.getCmp('realtime-report-params-month').value
                      });
                      store.load();
                  }
              }],
              items: [{
                      xtype: 'chart',
                      id: 'fullBarChart',
                      height: 250,
                      width: '100%',
                      animate: true,
                      style: 'background:#fff',
                      store: Ext.create('Ext.data.ArrayStore', {
                          storeId: 'realtime-year-month-report',
                          fields: [ {name: 'key', type: 'string'}, {name: 'amount',  type: 'float'}],
                          pageSize: 100,
                          proxy: {
                              type: 'ajax',
                              url : 'realtimeReport.action?month=6'
                          },
                          autoLoad: true
                      }),
                      listeners:{
                    	  beforerefresh: function(){
                	            //clearTimeout(timer);
                	            if (selectedStoreItem) {
                	            //    timer = setTimeout(function() {
                	                	selectBarchartItem(selectedStoreItem);
                	            //    }, 900);
                	            }
                          }
                      },
                      axes: [{
                          type: 'Numeric',
                          position: 'left',
                          fields: ['amount'],
                          label: {
                              renderer: Ext.util.Format.numberRenderer('0,0.00')
                          },
                          title: '<%= i18n.getI18nText("accounting.realtime.report.full.axes.y.title") %>',
                          grid: {
                              odd: {
                                  opacity: 1,
                                  fill: '#eee',
                                  stroke: '#ddd',
                                  'stroke-width': 1
                              }
                          }
                      }, {
                          type: 'Category',
                          position: 'bottom',
                          fields: ['key'],
<%--                           title: '<%= i18n.getI18nText("accounting.realtime.report.full.axes.x.title") %>' --%>
                      }],
                      series: [{
                          type: 'column',
                          axis: 'left',
                          clickedItem: false,
                          
                          listeners: {
                              itemmousedown: function(item){
                                  this.clickedItem = item;
                              },
                              itemmouseup: function(item) {
                                  if (this.clickedItem == item){
                                        dateLike = item.storeItem.get('key');
                                        refreshData(type , dateLike);
                                        selectedStoreItem = item.storeItem;
                                        selectBarchartItem(selectedStoreItem);
                                  }
                            	  this.clickedItem = null;
                             }
                          },
                          highlight: true,
                          highlightCfg: {
                              opacity : 0.5,
                              fill: '#a2b5ca'
                          },
                          renderer: function(sprite, storeItem, barAttr, i, store) {
                              if (storeItem.data.amount < 0){
                                  barAttr.fill = 'rgb(249, 153, 0)';
                              }else{
                                  barAttr.fill = 'rgb(49, 149, 0)';
                              }
                              return barAttr;
                          },
                          tips: {
                            trackMouse: true,
                            width: 140,
                            height: 28,
                            renderer: function(storeItem, item) {
                              this.setTitle(storeItem.get('key') + ': ¥ ' + storeItem.get('amount'));
                            }
                          },
                          label: {
                            display: 'insideEnd',
                            'text-anchor': 'middle',
                              field: 'amount',
                              renderer: Ext.util.Format.numberRenderer('0,0.00'),
                              orientation: 'vertical',
                              color: '#333'
                          },
                          xField: 'key',
                          yField: 'amount'
                      }]
                }]
          },{
                region: 'center',
                layout: 'border',
                defaults:{
                	hideCollapseTool: true,
                    collapsible: true,
                },
                items: [{
                    region: 'west',
                    id: 'piePanel',
                    layout: 'fit',
                    title: '<%= i18n.getI18nText("accounting.realtime.report.pie.title.unselect") %>',
                    border: false,
                    hideCollapseTool: false,
                    split: true,
                    width: '40%',
                    tbar: [{
                    	xtype: 'combo',
                    	labelWidth: 50,
                    	flex: 1,
                        fieldLabel: '<%= i18n.getI18nText("accounting.realtime.report.pie.type") %>',
                        store: Ext.create('Ext.data.ArrayStore', {
                            storeId : 'TypeStore',
                            fields : [ 'key', 'value' ],
                            data : [ [ -1, '<%= i18n.getI18nText("accounting.common.type.credit") %>' ], [ 1, '<%= i18n.getI18nText("accounting.common.type.debit") %>' ] ]
                        }),
                        value: -1,
                        valueField : 'key',
                        displayField : 'value',
                        queryMode: 'local',
                        listeners: {
                        	change: function(scope, newValue, oldValue){
                        		type = newValue;
                        		refreshData(type, dateLike);
                        	}
                        }
                    }, {
                        xtype: 'combo',
                        id: 'pieReportBy',
                        labelWidth: 50,
                        flex: 1,
                        fieldLabel: '<%= i18n.getI18nText("accounting.realtime.report.pie.by") %>',
                        store: Ext.create('Ext.data.ArrayStore', {
                            storeId : 'TypeStore',
                            fields : [ 'key', 'value' ],
                            data : [ [ 'consumeType', '<%= i18n.getI18nText("accounting.realtime.report.pie.by.options.consume.type") %>' ], [ 'target', '<%= i18n.getI18nText("accounting.realtime.report.pie.by.options.target") %>' ] ]
                        }),
                        value: 'consumeType',
                        valueField : 'key',
                        displayField : 'value',
                        queryMode: 'local',
                        listeners: {
                            change: function(scope, newValue, oldValue){
                                by = newValue;
                                refreshPie(type, dateLike, by);
                            }
                        }
                    }],
                    items :[pieChart]
                },{
                    region: 'center',
                    layout: 'anchor',
                   // title: '<%= i18n.getI18nText("accounting.realtime.report.list.title") %>',
                    border: false,
                    items: [lineChart, detailGrid]
                }]
          }]
    });
}
//-->
</script>
