/*

This file is part of Ext JS 4

Copyright (c) 2011 Sencha Inc

Contact:  http://www.sencha.com/contact

GNU General Public License Usage
This file may be used under the terms of the GNU General Public License version 3.0 as published by the Free Software Foundation and appearing in the file LICENSE included in the packaging of this file.  Please review the following information to ensure the GNU General Public License version 3.0 requirements will be met: http://www.gnu.org/copyleft/gpl.html.

If you are unsure which license is appropriate for your use, please contact the sales department at http://www.sencha.com/contact.

*/
/**
 * Simplified Chinese translation
 * By DavidHu
 * 09 April 2007
 * 
 * update by andy_ghg
 * 2009-10-22 15:00:57
 */
Ext.onReady(function(){
    if(Ext.Updater){
        Ext.Updater.defaults.indicatorText = '<div class="loading-indicator">\u9354\u72ba\u6d47\u6d93\ufffd..</div>';
    }

    if(Ext.view.View){
       Ext.view.View.prototype.emptyText = "";
    }

    if(Ext.grid.Panel){
       Ext.grid.Panel.prototype.ddText = "\u95ab\u590b\u5ae8\u6d5c\ufffd{0} \u741b\ufffd;
    }

    if(Ext.TabPanelItem){
       Ext.TabPanelItem.prototype.closeText = "\u934f\u62bd\u68f4\u59dd\u3086\u7223\u7edb\ufffd;
    }

    if(Ext.form.field.Base){
       Ext.form.field.Base.prototype.invalidText = "\u6748\u64b3\u53c6\u934a\u5978\u6f6a\u5a09\ufffd;
    }

    if (Ext.LoadMask) {
        Ext.LoadMask.prototype.msg = "\u7487\u8bf2\u5f47\u6d93\ufffd..";
    }

    if(Ext.Date){
        Ext.Date.monthNames = [
           "\u6d93\ufffd\u6e40",
           "\u6d5c\u5c7e\u6e40",
           "\u6d93\u590b\u6e40",
           "\u9365\u6d99\u6e40",
           "\u6d5c\u65c0\u6e40",
           "\u934f\ue15f\u6e40",
           "\u6d93\u51a9\u6e40",
           "\u934f\ue0a3\u6e40",
           "\u6d94\u6fc7\u6e40",
           "\u9357\u4f79\u6e40",
           "\u9357\u4f77\u7af4\u93c8\ufffd,
           "\u9357\u4f77\u7c29\u93c8\ufffd
        ];

        Ext.Date.dayNames = [
           "\u93c3\ufffd,
           "\u6d93\ufffd,
           "\u6d5c\ufffd,
           "\u6d93\ufffd,
           "\u9365\ufffd,
           "\u6d5c\ufffd,
           "\u934f\ufffd
        ];

        Ext.Date.formatCodes.a = "(this.getHours() < 12 ? '\u6d93\u5a42\u5d0d' : '\u6d93\u5b2a\u5d0d')";
        Ext.Date.formatCodes.A = "(this.getHours() < 12 ? '\u6d93\u5a42\u5d0d' : '\u6d93\u5b2a\u5d0d')";
    }

    if(Ext.MessageBox){
       Ext.MessageBox.buttonText = {
          ok     : "\u7ead\ue1bc\u757e",
          cancel : "\u9359\u6828\u79f7",
          yes    : "\u93c4\ufffd,
          no     : "\u935a\ufffd
       };
    }

    if(Ext.util.Format){
        Ext.apply(Ext.util.Format, {
            thousandSeparator: '.',
            decimalSeparator: ',',
            currencySign: '\u00a5',  // Chinese Yuan
            dateFormat: 'y\u9a9e\u78ce\u93c8\u5749\u93c3\ufffd
        });
    }

    if(Ext.picker.Date){
       Ext.apply(Ext.picker.Date.prototype, {
          todayText         : "\u6d60\u5a42\u3049",
          minText           : "\u93c3\u30e6\u6e61\u8e47\u5474\u300f\u6fb6\u0442\u7c2c\u93c8\ufffd\u76ac\u934f\u4f7d\ue18f\u93c3\u30e6\u6e61",//update
          maxText           : "\u93c3\u30e6\u6e61\u8e47\u5474\u300f\u704f\u5fce\u7c2c\u93c8\ufffd\u3047\u934f\u4f7d\ue18f\u93c3\u30e6\u6e61",//update
          disabledDaysText  : "",
          disabledDatesText : "",
          monthNames        : Ext.Date.monthNames,
          dayNames          : Ext.Date.dayNames,
          nextText          : '\u6d93\u5b29\u91dc\u93c8\ufffd(Ctrl+Right)',
          prevText          : '\u6d93\u5a41\u91dc\u93c8\ufffd(Ctrl+Left)',
          monthYearText     : '\u95ab\u590b\u5ae8\u6d93\ufffd\u91dc\u93c8\ufffd(Control+Up/Down \u93c9\u30e6\u657c\u9359\u6a3a\u52fe\u6d60\ufffd',//update
          todayTip          : "{0} (\u7ecc\u70d8\u7278\u95bf\ue1c0\ufffd\u93b7\ufffd",
          format            : "y\u9a9e\u78ce\u93c8\u5749\u93c3\ufffd
       });
    }

    if(Ext.picker.Month) {
      Ext.apply(Ext.picker.Month.prototype, {
          okText            : "\u7ead\ue1bc\u757e",
          cancelText        : "\u9359\u6828\u79f7"
      });
    }

    if(Ext.toolbar.Paging){
       Ext.apply(Ext.PagingToolbar.prototype, {
          beforePageText : "\u7ed7\ufffd,//update
          afterPageText  : "\u6924\ufffd\u934f\ufffd{0} \u6924\ufffd,//update
          firstText      : "\u7ed7\ue0ff\u7af4\u6924\ufffd,
          prevText       : "\u6d93\u5a41\u7af4\u6924\ufffd,//update
          nextText       : "\u6d93\u5b29\u7af4\u6924\ufffd,
          lastText       : "\u93c8\ufffd\u6097\u6924\ufffd,
          refreshText    : "\u9352\u950b\u67ca",
          displayMsg     : "\u93c4\u5267\u305a {0} - {1}\u93c9\u2605\u7d1d\u934f\ufffd{2} \u93c9\ufffd,//update
          emptyMsg       : '\u5a0c\u2103\u6e41\u93c1\u7248\u5d41'
       });
    }

    if(Ext.form.field.Text){
       Ext.apply(Ext.form.field.Text.prototype, {
          minLengthText : "\u7487\u30e8\u7ded\u934f\u30e9\u300d\u9428\u52ec\u6e36\u704f\u5fdb\u66b1\u6434\ufe3d\u69f8 {0} \u6d93\ue044\u74e7\u7ed7\ufffd,
          maxLengthText : "\u7487\u30e8\u7ded\u934f\u30e9\u300d\u9428\u52ec\u6e36\u6fb6\u0447\u66b1\u6434\ufe3d\u69f8 {0} \u6d93\ue044\u74e7\u7ed7\ufffd,
          blankText     : "\u7487\u30e8\u7ded\u934f\u30e9\u300d\u6d93\u54c4\u7e40\u6748\u64bb\u300d",
          regexText     : "",
          emptyText     : null
       });
    }

    if(Ext.form.field.Number){
       Ext.apply(Ext.form.field.Number.prototype, {
          minText : "\u7487\u30e8\u7ded\u934f\u30e9\u300d\u9428\u52ec\u6e36\u704f\u5fd3\ufffd\u93c4\ufffd{0}",
          maxText : "\u7487\u30e8\u7ded\u934f\u30e9\u300d\u9428\u52ec\u6e36\u6fb6\u0443\ufffd\u93c4\ufffd{0}",
          nanText : "{0} \u6d93\u5d86\u69f8\u93c8\u590b\u6665\u93c1\u677f\ufffd"
       });
    }

    if(Ext.form.field.Date){
       Ext.apply(Ext.form.field.Date.prototype, {
          disabledDaysText  : "\u7ec2\u4f7a\u6564",
          disabledDatesText : "\u7ec2\u4f7a\u6564",
          minText           : "\u7487\u30e8\u7ded\u934f\u30e9\u300d\u9428\u52ec\u68e9\u93c8\u71b7\u7e40\u6924\u8bf2\u6e6a {0} \u6d94\u5b2a\u6097",
          maxText           : "\u7487\u30e8\u7ded\u934f\u30e9\u300d\u9428\u52ec\u68e9\u93c8\u71b7\u7e40\u6924\u8bf2\u6e6a {0} \u6d94\u5b2a\u58a0",
          invalidText       : "{0} \u93c4\ue21b\u68e4\u93c1\u5822\u6b91\u93c3\u30e6\u6e61 - \u8e47\u5474\u300f\u7ed7\ufe40\u608e\u93cd\u714e\u7d21\u951b\ufffd{1}",
          format            : "y\u9a9e\u78ce\u93c8\u5749\u93c3\ufffd
       });
    }

    if(Ext.form.field.ComboBox){
       Ext.apply(Ext.form.field.ComboBox.prototype, {
          loadingText       : "\u9354\u72ba\u6d47\u6d93\ufffd..",
          valueNotFoundText : undefined
       });
    }

    if(Ext.form.field.VTypes){
       Ext.apply(Ext.form.field.VTypes, {
          emailText    : '\u7487\u30e8\u7ded\u934f\u30e9\u300d\u8e47\u5474\u300f\u93c4\ue21c\u6578\u701b\u6130\u5056\u6d60\u8dfa\u6e74\u9367\ufffd\u7d1d\u93cd\u714e\u7d21\u6fe1\u50a6\u7d30 "user@example.com"',
          urlText      : '\u7487\u30e8\u7ded\u934f\u30e9\u300d\u8e47\u5474\u300f\u93c4\u75b7RL\u9366\u677f\u6f43\u951b\u5c7e\u7278\u5bee\u5fd3\ue706\u951b\ufffd"http:/'+'/www.example.com"',
          alphaText    : '\u7487\u30e8\u7ded\u934f\u30e9\u300d\u9359\ue047\u5158\u9356\u546d\u60c8\u9357\u5a45\ue791\u701b\u6941\u761d\u935c\u5b8a',//update
          alphanumText : '\u7487\u30e8\u7ded\u934f\u30e9\u300d\u9359\ue047\u5158\u9356\u546d\u60c8\u9357\u5a45\ue791\u701b\u6941\u761d,\u93c1\u677f\u74e7\u935c\u5b8a'//update
       });
    }
    //add HTMLEditor's tips by andy_ghg
    if(Ext.form.field.HtmlEditor){
      Ext.apply(Ext.form.field.HtmlEditor.prototype, {
        createLinkText : '\u5a23\u8bf2\u59de\u74d2\u546f\u9a87\u95be\u70ac\u5e34:',
        buttonTips : {
          bold : {
            title: '\u7eee\u693e\u7d8b (Ctrl+B)',
            text: '\u704f\u55db\ufffd\u6d93\ue160\u6b91\u93c2\u56e7\u74e7\u7481\u5267\u7586\u6d93\u8679\u77d6\u6d63\ufffd,
            cls: Ext.baseCSSPrefix + 'html-editor-tip'
          },
          italic : {
            title: '\u93c2\u6ec0\u7d8b (Ctrl+I)',
            text: '\u704f\u55db\ufffd\u6d93\ue160\u6b91\u93c2\u56e7\u74e7\u7481\u5267\u7586\u6d93\u70d8\u67a9\u6d63\ufffd,
            cls: Ext.baseCSSPrefix + 'html-editor-tip'
          },
          underline : {
            title: '\u6d93\u5b2a\u579d\u7efe\ufffd(Ctrl+U)',
            text: '\u7f01\u6b10\u588d\u95ab\u590b\u6783\u701b\u6940\u59de\u6d93\u5b2a\u579d\u7efe\ufffd,
            cls: Ext.baseCSSPrefix + 'html-editor-tip'
          },
          increasefontsize : {
            title: '\u6fa7\u70b2\u3047\u701b\u693e\u7d8b',
            text: '\u6fa7\u70b2\u3047\u701b\u6940\u5f7f',
            cls: Ext.baseCSSPrefix + 'html-editor-tip'
          },
          decreasefontsize : {
            title: '\u7f02\u2541\u76ac\u701b\u693e\u7d8b',
            text: '\u9351\u5fd3\u76ac\u701b\u6940\u5f7f',
            cls: Ext.baseCSSPrefix + 'html-editor-tip'
          },
          backcolor : {
            title: '\u6d60\u30e4\u7b09\u935a\u5c84\ue581\u9479\u832c\u734a\u9351\u70d8\u6a09\u7ec0\u70d8\u6783\u93c8\ufffd,
            text: '\u6d63\u630e\u6783\u701b\u6943\u6e45\u6d93\u5a42\u5e53\u934d\u5fd4\u69f8\u9422\u3128\u5d38\u934f\u590c\u746a\u934b\u6c2b\u7c21\u93cd\u56ea\ue187\u6d93\ufffd\u7271',
            cls: Ext.baseCSSPrefix + 'html-editor-tip'
          },
          forecolor : {
            title: '\u701b\u693e\u7d8b\u68f0\u6ec6\u58ca',
            text: '\u93c7\u5b58\u657c\u701b\u693e\u7d8b\u68f0\u6ec6\u58ca',
            cls: Ext.baseCSSPrefix + 'html-editor-tip'
          },
          justifyleft : {
            title: '\u5bb8\ufe40\ue1ee\u69bb\ufffd,
            text: '\u704f\u55d8\u6783\u701b\u6940\u4e4f\u7035\u5f52\u7d88',
            cls: Ext.baseCSSPrefix + 'html-editor-tip'
          },
          justifycenter : {
            title: '\u705e\u546c\u8151',
            text: '\u704f\u55d8\u6783\u701b\u6940\u7733\u6d93\ue15e\ue1ee\u69bb\ufffd,
            cls: Ext.baseCSSPrefix + 'html-editor-tip'
          },
          justifyright : {
            title: '\u9359\u51b2\ue1ee\u69bb\ufffd,
            text: '\u704f\u55d8\u6783\u701b\u6940\u5f78\u7035\u5f52\u7d88',
            cls: Ext.baseCSSPrefix + 'html-editor-tip'
          },
          insertunorderedlist : {
            title: '\u6924\u572d\u6d30\u7ed7\ufe40\u5f7f',
            text: '\u5bee\ufffd\ue78a\u9352\u6d98\u7f13\u6924\u572d\u6d30\u7ed7\ufe40\u5f7f\u9352\u6944\u3003',
            cls: Ext.baseCSSPrefix + 'html-editor-tip'
          },
          insertorderedlist : {
            title: '\u7f02\u6827\u5f7f',
            text: '\u5bee\ufffd\ue78a\u9352\u6d98\u7f13\u7f02\u6827\u5f7f\u9352\u6944\u3003',
            cls: Ext.baseCSSPrefix + 'html-editor-tip'
          },
          createlink : {
            title: '\u675e\ue101\u579a\u74d2\u546f\u9a87\u95be\u70ac\u5e34',
            text: '\u704f\u55d8\u588d\u95ab\u590b\u6783\u93c8\ue103\u6d46\u93b9\u3221\u579a\u74d2\u546f\u9a87\u95be\u70ac\u5e34',
            cls: Ext.baseCSSPrefix + 'html-editor-tip'
          },
          sourceedit : {
            title: '\u6d60\uff47\u721c\u7459\u55d7\u6d58',
            text: '\u6d60\u30e4\u552c\u942e\u4f7a\u6b91\u8930\u3220\u7d21\u705e\u66e0\u5e47\u93c2\u56e8\u6e70',
            cls: Ext.baseCSSPrefix + 'html-editor-tip'
          }
        }
      });
    }


    if(Ext.grid.header.Container){
       Ext.apply(Ext.grid.header.Container.prototype, {
          sortAscText  : "\u59dd\uff45\u7c2d",//update
          sortDescText : "\u934a\u6391\u7c2d",//update
          lockText     : "\u95bf\u4f78\u757e\u9352\ufffd,//update
          unlockText   : "\u7459\uff49\u6ace\u95bf\u4f78\u757e",//update
          columnsText  : "\u9352\ufffd
       });
    }

    if(Ext.grid.PropertyColumnModel){
       Ext.apply(Ext.grid.PropertyColumnModel.prototype, {
          nameText   : "\u935a\u5d87\u041e",
          valueText  : "\u934a\ufffd,
          dateFormat : "y\u9a9e\u78ce\u93c8\u5749\u93c3\ufffd
       });
    }

    if(Ext.layout.BorderLayout && Ext.layout.BorderLayout.SplitRegion){
       Ext.apply(Ext.layout.BorderLayout.SplitRegion.prototype, {
          splitTip            : "\u93b7\u6827\u59e9\u93c9\u30e6\u657c\u9359\u6a3a\u6602\u7035\ufffd",
          collapsibleSplitTip : "\u93b7\u6827\u59e9\u93c9\u30e6\u657c\u9359\u6a3a\u6602\u7035\ufffd \u9359\u5c7d\u56ae\u95c5\u612f\u68cc."
       });
    }
});
