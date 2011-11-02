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
              layout: 'border',
              defaults : {
                  border: false,
                  split : true
              },
              items: [{
                  region: 'west',
                  width: 300,
                  layout: 'anchor',
                  items:[{
                        xtype: 'form',
                        padding: '10',
                        border: false,
                        items: [{
                            xtype: 'combo',
                            name: 'month',
                            fieldLabel: '<%= i18n.getI18nText("accounting.realtime.report.label.month") %>',
                            store: 'RealTimeQueryMonthStore',
                            value: 3,
                            valueField : 'id',
                            displayField : 'value',
                            queryMode: 'local'
                        }],
                        buttons: [{
                            text: '<%= i18n.getI18nText("button.submit") %>',
                            handler: function(){
                                this.up('form').submit({
                                    
                                });
                            }
                      }]
                  }]
              },{
                  region: 'center',
                  tbar: [{
                      text: '<%= i18n.getI18nText("accounting.realtime.report.full.reload") %>',
                      handler: function() {
                          store1.loadData(generateData());
                      }
                  }],
                  items: [{
                	  xtype: 'chart',
                	  animate: true,
                	  store: Ext.create('Ext.data.ArrayStore', {
                          fields: [ {name: 'month', type: 'string'}, {name: 'amount',  type: 'int'}],
                          proxy: {
                              type: 'ajax',
                              url : 'realtimeReport.action?month=3'
                          },
                          autoLoad: true
                      }),
                	  axes: [{
                          type: 'Numeric',
                          position: 'left',
                          fields: ['data1'],
                          label: {
                              renderer: Ext.util.Format.currency(value, '¥', 2)
                          },
                          title: '<%= i18n.getI18nText("accounting.realtime.report.full.axes.y.title") %>',
                          grid: true,
                          minimum: 0
                      }, {
                          type: 'Category',
                          position: 'bottom',
                          fields: ['name'],
                          title: '<%= i18n.getI18nText("accounting.realtime.report.full.axes.x.title") %>'
                      }],
                      series: [{
                          type: 'column',
                          axis: 'left',
                          highlight: true,
                          tips: {
                            trackMouse: true,
                            width: 140,
                            height: 28,
                            renderer: function(storeItem, item) {
                              this.setTitle(storeItem.get('name') + ': ' + storeItem.get('data1') + ' $');
                            }
                          },
                          label: {
                            display: 'insideEnd',
                            'text-anchor': 'middle',
                              field: 'data1',
                              renderer: Ext.util.Format.numberRenderer('0'),
                              orientation: 'vertical',
                              color: '#333'
                          },
                          xField: 'name',
                          yField: 'data1'
                      }]
                  }]
              }]
          },{
        	  region: 'center'
          }]
    });
}
//-->
</script>
