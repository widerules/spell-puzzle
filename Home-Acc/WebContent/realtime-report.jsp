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
                  
              }]
          },{
        	  region: 'center'
          }]
    });
}
//-->
</script>
