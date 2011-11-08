<script type="text/javascript">
function buildHomeTab(){
    var d = new Date(),
	dateLike = d.getFullYear() + "-" + (d.getMonth() + 1  < 10 ? "0" + d.getMonth() + 1 : d.getMonth() + 1),
    type = -1,
    by = 'consumeType';
    
    pieStore = Ext.create('Ext.data.Store', {
        fields: [ {name: 'key', type: 'string'}, {name: 'amount',  type: 'float'}],
        proxy:{
        	type: 'ajax',
            url: 'realtimeConsumeTypeReport.action?dateLike=' + dateLike + '&type=' + type + '&by=' + by
        },
        pageSize: 100,
        autoLoad: true
    });
    
    lineStore = Ext.create('Ext.data.Store', {
        fields: [ {name: 'key', type: 'string'}, {name: 'amount',  type: 'float'}],
        proxy:{
        	type: 'ajax',
            url: 'realtimeDailyReport.action?dateLike=' + dateLike + '&type=' + type
        },
        pageSize: 100,
        autoLoad: true
    });
    
    gridStore = Ext.create('Ext.data.Store', {
        model: 'RecordModel',
        proxy: {
        	type: 'ajax',
            url: 'realtimeDetailReport.action?dateLike=' + dateLike + '&type=' + type
        },
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
<%--                    title: '<%= i18n.getI18nText("accounting.realtime.report.daily.axes.x.title") %>', --%>
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
    
	return Ext.create('Ext.panel.Panel', {
		title: '<%= i18n.getI18nText("accounting.home.title") %>',
	    layout : {
          type : 'border'
        },
        defaults : {
          split : true
        },
        items: [
             {region: 'north', height: '40%',layout: 'fit', border: false, items:[lineChart]},
            {
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
                items: [detailGrid]
            }
        ]
	});
}

</script>