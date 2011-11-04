<script type="text/javascript">
<!--
function buildRealtimeReportTab() {
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
              height: 300,
              layout: 'fit',
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
                      console.log(Ext.getCmp('realtime-report-params-month').value);
                      store.setProxy({
                    	  type: 'ajax',
                          url: 'realtimeReport.action?month=' + Ext.getCmp('realtime-report-params-month').value
                      })
                      store.load(function(records, operation, success) {
                          
                      });
                  }
              }],
              items: [{
                      xtype: 'chart',
                      animate: true,
                      style: 'background:#fff',
                      store: Ext.create('Ext.data.ArrayStore', {
                          storeId: 'realtime-year-month-report',
                          fields: [ {name: 'yearMonth', type: 'string'}, {name: 'amount',  type: 'int'}],
                          proxy: {
                              type: 'ajax',
                              url : 'realtimeReport.action?month=6'
                          },
                          autoLoad: true
                      }),
                      axes: [{
                          type: 'Numeric',
                          position: 'left',
                          fields: ['amount'],
                          label: {
                              renderer: Ext.util.Format.numberRenderer('0,0')
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
                          fields: ['yearMonth'],
                          title: '<%= i18n.getI18nText("accounting.realtime.report.full.axes.x.title") %>'
                      }],
                      series: [{
                          type: 'column',
                          axis: 'left',
                          clickedItem: null,
                          listeners: {
                              itemmousedown: function(item){
                                  this.clickedItem = item;
                              },
                              itemmouseup: function(item) {
                                  if (this.clickedItem == item){
                                        item.series.highlight = true;
                                        item.series.unHighlightItem()
                                        item.series.cleanHighlights();
                                        item.series.highlightItem(item);
                                        item.series.highlight = false;
                                        console.log(item);
                                  }
                            	  this.clickedItem = null;
                             }
                          },
                          highlight: false,
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
                              this.setTitle(storeItem.get('yearMonth') + ': ' + storeItem.get('amount') + ' $');
                            }
                          },
                          label: {
                            display: 'insideEnd',
                            'text-anchor': 'middle',
                              field: 'amount',
                              renderer: Ext.util.Format.numberRenderer('0'),
                              orientation: 'vertical',
                              color: '#333'
                          },
                          xField: 'yearMonth',
                          yField: 'amount'
                      }]
                }]
          },{
                region: 'center',
                layout: 'border',
                items: [{
                    region: 'west',
                    layout: 'fit',
                    border: false,
                    split: true,
                    width: 300,
                    items :[{
                    	xtype: 'chart',
                        animate: true,
                    }]
                },{
                    region: 'center',
                    items: []
                }]
          }]
    });
}
//-->
</script>
