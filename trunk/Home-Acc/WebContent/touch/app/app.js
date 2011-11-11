AccountingApp = new Ext.Application({
    name: 'FamilyAccounting',
    tabletStartupScreen: 'tablet_startup.jpg',
    phoneStartupScreen: 'phone_startup.jpg',
    tabletIcon: 'icon-ipad.png',
    phoneIcon: 'icon-iphone.png',
    glossOnIcon: false,
    launch: function(){
        this.views.viewport = new this.views.LoginView({title: 'Family Accounting'});
    }
});
