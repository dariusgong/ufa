define("taurus/tree/3.5.01/tree.excheck",[],function(e){return function(e){(function(e){var t={event:{CHECK:"ztree_check"},id:{CHECK:"_check"},checkbox:{STYLE:"checkbox",DEFAULT:"chk",DISABLED:"disable",FALSE:"false",TRUE:"true",FULL:"full",PART:"part",FOCUS:"focus"},radio:{STYLE:"radio",TYPE_ALL:"all",TYPE_LEVEL:"level"}},n={check:{enable:!1,autoCheckTrigger:!1,chkStyle:t.checkbox.STYLE,nocheckInherit:!1,radioType:t.radio.TYPE_LEVEL,chkboxType:{Y:"ps",N:"ps"}},data:{key:{checked:"checked"}},callback:{beforeCheck:null,onCheck:null}},r=function(e){var t=E.getRoot(e);t.radioCheckedList=[]},i=function(e){},s=function(e){var t=e.treeObj,n=b.event;t.bind(n.CHECK,function(t,n,r,i){y.apply(e.callback.onCheck,[n?n:t,r,i])})},o=function(e){var t=e.treeObj,n=b.event;t.unbind(n.CHECK)},u=function(e){var t=e.target,n=E.getSetting(e.data.treeId),r="",i=null,s="",o="",u=null,a=null;y.eqs(e.type,"mouseover")?n.check.enable&&y.eqs(t.tagName,"span")&&t.getAttribute("treeNode"+b.id.CHECK)!==null&&(r=t.parentNode.id,s="mouseoverCheck"):y.eqs(e.type,"mouseout")?n.check.enable&&y.eqs(t.tagName,"span")&&t.getAttribute("treeNode"+b.id.CHECK)!==null&&(r=t.parentNode.id,s="mouseoutCheck"):y.eqs(e.type,"click")&&n.check.enable&&y.eqs(t.tagName,"span")&&t.getAttribute("treeNode"+b.id.CHECK)!==null&&(r=t.parentNode.id,s="checkNode");if(r.length>0){i=E.getNodeCache(n,r);switch(s){case"checkNode":u=p.onCheckNode;break;case"mouseoverCheck":u=p.onMouseoverCheck;break;case"mouseoutCheck":u=p.onMouseoutCheck}}var f={stop:!1,node:i,nodeEventType:s,nodeEventCallback:u,treeEventType:o,treeEventCallback:a};return f},a=function(e,t,n,r,i,s,o){if(!n)return;var u=e.data.key.checked;typeof n[u]=="string"&&(n[u]=y.eqs(n[u],"true")),n[u]=!!n[u],n.checkedOld=n[u],typeof n.nocheck=="string"&&(n.nocheck=y.eqs(n.nocheck,"true")),n.nocheck=!!n.nocheck||e.check.nocheckInherit&&r&&!!r.nocheck,typeof n.chkDisabled=="string"&&(n.chkDisabled=y.eqs(n.chkDisabled,"true")),n.chkDisabled=!!n.chkDisabled||r&&!!r.chkDisabled,typeof n.halfCheck=="string"&&(n.halfCheck=y.eqs(n.halfCheck,"true")),n.halfCheck=!!n.halfCheck,n.check_Child_State=-1,n.check_Focus=!1,n.getCheckStatus=function(){return E.getCheckStatus(e,n)}},f=function(e,t,n){var r=e.data.key.checked;if(e.check.enable){E.makeChkFlag(e,t);if(e.check.chkStyle==b.radio.STYLE&&e.check.radioType==b.radio.TYPE_ALL&&t[r]){var i=E.getRoot(e);i.radioCheckedList.push(t)}n.push("<span ID='",t.tId,b.id.CHECK,"' class='",w.makeChkClass(e,t),"' treeNode",b.id.CHECK,t.nocheck===!0?" style='display:none;'":"","></span>")}},l=function(t,n){n.checkNode=function(n,r,i,s){var o=this.setting.data.key.checked;if(n.chkDisabled===!0)return;r!==!0&&r!==!1&&(r=!n[o]),s=!!s;if(n[o]===r&&!i)return;if(s&&y.apply(this.setting.callback.beforeCheck,[this.setting.treeId,n],true)==0)return;if(y.uCanDo(this.setting)&&this.setting.check.enable&&n.nocheck!==!0){n[o]=r;var u=e("#"+n.tId+b.id.CHECK);(i||this.setting.check.chkStyle===b.radio.STYLE)&&w.checkNodeRelation(this.setting,n),w.setChkClass(this.setting,u,n),w.repairParentChkClassWithSelf(this.setting,n),s&&t.treeObj.trigger(b.event.CHECK,[null,t.treeId,n])}},n.checkAllNodes=function(e){w.repairAllChk(this.setting,!!e)},n.getCheckedNodes=function(e){var n=this.setting.data.key.children;return e=e!==!1,E.getTreeCheckedNodes(this.setting,E.getRoot(t)[n],e)},n.getChangeCheckedNodes=function(){var e=this.setting.data.key.children;return E.getTreeChangeCheckedNodes(this.setting,E.getRoot(t)[e])},n.setChkDisabled=function(e,t){t=!!t,w.repairSonChkDisabled(this.setting,e,t),t||w.repairParentChkDisabled(this.setting,e,t)};var r=n.updateNode;n.updateNode=function(t,i){r&&r.apply(n,arguments);if(!t||!this.setting.check.enable)return;var s=e("#"+t.tId);if(s.get(0)&&y.uCanDo(this.setting)){var o=e("#"+t.tId+b.id.CHECK);(i==1||this.setting.check.chkStyle===b.radio.STYLE)&&w.checkNodeRelation(this.setting,t),w.setChkClass(this.setting,o,t),w.repairParentChkClassWithSelf(this.setting,t)}}},c={getRadioCheckedList:function(e){var t=E.getRoot(e).radioCheckedList;for(var n=0,r=t.length;n<r;n++)E.getNodeCache(e,t[n].tId)||(t.splice(n,1),n--,r--);return t},getCheckStatus:function(e,t){if(!e.check.enable||t.nocheck)return null;var n=e.data.key.checked,r={checked:t[n],half:t.halfCheck?t.halfCheck:e.check.chkStyle==b.radio.STYLE?t.check_Child_State===2:t[n]?t.check_Child_State>-1&&t.check_Child_State<2:t.check_Child_State>0};return r},getTreeCheckedNodes:function(e,t,n,r){if(!t)return[];var i=e.data.key.children,s=e.data.key.checked,o=n&&e.check.chkStyle==b.radio.STYLE&&e.check.radioType==b.radio.TYPE_ALL;r=r?r:[];for(var u=0,a=t.length;u<a;u++){if(t[u].nocheck!==!0&&t[u][s]==n){r.push(t[u]);if(o)break}E.getTreeCheckedNodes(e,t[u][i],n,r);if(o&&r.length>0)break}return r},getTreeChangeCheckedNodes:function(e,t,n){if(!t)return[];var r=e.data.key.children,i=e.data.key.checked;n=n?n:[];for(var s=0,o=t.length;s<o;s++)t[s].nocheck!==!0&&t[s][i]!=t[s].checkedOld&&n.push(t[s]),E.getTreeChangeCheckedNodes(e,t[s][r],n);return n},makeChkFlag:function(e,t){if(!t)return;var n=e.data.key.children,r=e.data.key.checked,i=-1;if(t[n]){var s=!1;for(var o=0,u=t[n].length;o<u;o++){var a=t[n][o],f=-1;if(e.check.chkStyle==b.radio.STYLE){a.nocheck===!0?f=a.check_Child_State:a.halfCheck===!0?f=2:a.nocheck!==!0&&a[r]?f=2:f=a.check_Child_State>0?2:0;if(f==2){i=2;break}f==0&&(i=0)}else if(e.check.chkStyle==b.checkbox.STYLE){a.nocheck===!0?f=a.check_Child_State:a.halfCheck===!0?f=1:a.nocheck!==!0&&a[r]?f=a.check_Child_State===-1||a.check_Child_State===2?2:1:f=a.check_Child_State>0?1:0;if(f===1){i=1;break}if(f===2&&s&&f!==i){i=1;break}if(i===2&&f>-1&&f<2){i=1;break}f>-1&&(i=f),s||(s=a.nocheck!==!0)}}}t.check_Child_State=i}},h={},p={onCheckNode:function(t,n){if(n.chkDisabled===!0)return!1;var r=E.getSetting(t.data.treeId),i=r.data.key.checked;if(y.apply(r.callback.beforeCheck,[r.treeId,n],true)==0)return!0;n[i]=!n[i],w.checkNodeRelation(r,n);var s=e("#"+n.tId+b.id.CHECK);return w.setChkClass(r,s,n),w.repairParentChkClassWithSelf(r,n),r.treeObj.trigger(b.event.CHECK,[t,r.treeId,n]),!0},onMouseoverCheck:function(t,n){if(n.chkDisabled===!0)return!1;var r=E.getSetting(t.data.treeId),i=e("#"+n.tId+b.id.CHECK);return n.check_Focus=!0,w.setChkClass(r,i,n),!0},onMouseoutCheck:function(t,n){if(n.chkDisabled===!0)return!1;var r=E.getSetting(t.data.treeId),i=e("#"+n.tId+b.id.CHECK);return n.check_Focus=!1,w.setChkClass(r,i,n),!0}},d={},v={checkNodeRelation:function(t,n){var r,i,s,o=t.data.key.children,u=t.data.key.checked,a=b.radio;if(t.check.chkStyle==a.STYLE){var f=E.getRadioCheckedList(t);if(n[u])if(t.check.radioType==a.TYPE_ALL){for(i=f.length-1;i>=0;i--)r=f[i],r[u]=!1,f.splice(i,1),w.setChkClass(t,e("#"+r.tId+b.id.CHECK),r),r.parentTId!=n.parentTId&&w.repairParentChkClassWithSelf(t,r);f.push(n)}else{var l=n.parentTId?n.getParentNode():E.getRoot(t);for(i=0,s=l[o].length;i<s;i++)r=l[o][i],r[u]&&r!=n&&(r[u]=!1,w.setChkClass(t,e("#"+r.tId+b.id.CHECK),r))}else if(t.check.radioType==a.TYPE_ALL)for(i=0,s=f.length;i<s;i++)if(n==f[i]){f.splice(i,1);break}}else n[u]&&(!n[o]||n[o].length==0||t.check.chkboxType.Y.indexOf("s")>-1)&&w.setSonNodeCheckBox(t,n,!0),!n[u]&&(!n[o]||n[o].length==0||t.check.chkboxType.N.indexOf("s")>-1)&&w.setSonNodeCheckBox(t,n,!1),n[u]&&t.check.chkboxType.Y.indexOf("p")>-1&&w.setParentNodeCheckBox(t,n,!0),!n[u]&&t.check.chkboxType.N.indexOf("p")>-1&&w.setParentNodeCheckBox(t,n,!1)},makeChkClass:function(e,t){var n=e.data.key.checked,r=b.checkbox,i=b.radio,s="";t.chkDisabled===!0?s=r.DISABLED:t.halfCheck?s=r.PART:e.check.chkStyle==i.STYLE?s=t.check_Child_State<1?r.FULL:r.PART:s=t[n]?t.check_Child_State===2||t.check_Child_State===-1?r.FULL:r.PART:t.check_Child_State<1?r.FULL:r.PART;var o=e.check.chkStyle+"_"+(t[n]?r.TRUE:r.FALSE)+"_"+s;return o=t.check_Focus&&t.chkDisabled!==!0?o+"_"+r.FOCUS:o,"button "+r.DEFAULT+" "+o},repairAllChk:function(e,t){if(e.check.enable&&e.check.chkStyle===b.checkbox.STYLE){var n=e.data.key.checked,r=e.data.key.children,i=E.getRoot(e);for(var s=0,o=i[r].length;s<o;s++){var u=i[r][s];u.nocheck!==!0&&(u[n]=t),w.setSonNodeCheckBox(e,u,t)}}},repairChkClass:function(t,n){if(!n)return;E.makeChkFlag(t,n);var r=e("#"+n.tId+b.id.CHECK);w.setChkClass(t,r,n)},repairParentChkClass:function(e,t){if(!t||!t.parentTId)return;var n=t.getParentNode();w.repairChkClass(e,n),w.repairParentChkClass(e,n)},repairParentChkClassWithSelf:function(e,t){if(!t)return;var n=e.data.key.children;t[n]&&t[n].length>0?w.repairParentChkClass(e,t[n][0]):w.repairParentChkClass(e,t)},repairSonChkDisabled:function(e,t,n){if(!t)return;var r=e.data.key.children;t.chkDisabled!=n&&(t.chkDisabled=n,t.nocheck!==!0&&w.repairChkClass(e,t));if(t[r])for(var i=0,s=t[r].length;i<s;i++){var o=t[r][i];w.repairSonChkDisabled(e,o,n)}},repairParentChkDisabled:function(e,t,n){if(!t)return;t.chkDisabled!=n&&(t.chkDisabled=n,t.nocheck!==!0&&w.repairChkClass(e,t)),w.repairParentChkDisabled(e,t.getParentNode(),n)},setChkClass:function(e,t,n){if(!t)return;n.nocheck===!0?t.hide():t.show(),t.removeClass(),t.addClass(w.makeChkClass(e,n))},setParentNodeCheckBox:function(t,n,r,i){var s=t.data.key.children,o=t.data.key.checked,u=e("#"+n.tId+b.id.CHECK);i||(i=n),E.makeChkFlag(t,n),n.nocheck!==!0&&n.chkDisabled!==!0&&(n[o]=r,w.setChkClass(t,u,n),t.check.autoCheckTrigger&&n!=i&&n.nocheck!==!0&&t.treeObj.trigger(b.event.CHECK,[null,t.treeId,n]));if(n.parentTId){var a=!0;if(!r){var f=n.getParentNode()[s];for(var l=0,c=f.length;l<c;l++)if(f[l].nocheck!==!0&&f[l][o]||f[l].nocheck===!0&&f[l].check_Child_State>0){a=!1;break}}a&&w.setParentNodeCheckBox(t,n.getParentNode(),r,i)}},setSonNodeCheckBox:function(t,n,r,i){if(!n)return;var s=t.data.key.children,o=t.data.key.checked,u=e("#"+n.tId+b.id.CHECK);i||(i=n);var a=!1;if(n[s])for(var f=0,l=n[s].length;f<l&&n.chkDisabled!==!0;f++){var c=n[s][f];w.setSonNodeCheckBox(t,c,r,i),c.chkDisabled===!0&&(a=!0)}n!=E.getRoot(t)&&n.chkDisabled!==!0&&(a&&n.nocheck!==!0&&E.makeChkFlag(t,n),n.nocheck!==!0?(n[o]=r,a||(n.check_Child_State=n[s]&&n[s].length>0?r?2:0:-1)):n.check_Child_State=-1,w.setChkClass(t,u,n),t.check.autoCheckTrigger&&n!=i&&n.nocheck!==!0&&t.treeObj.trigger(b.event.CHECK,[null,t.treeId,n]))}},m={tools:d,view:v,event:h,data:c};e.extend(!0,e.fn.zTree.consts,t),e.extend(!0,e.fn.zTree._z,m);var g=e.fn.zTree,y=g._z.tools,b=g.consts,w=g._z.view,E=g._z.data,S=g._z.event;E.exSetting(n),E.addInitBind(s),E.addInitUnBind(o),E.addInitCache(i),E.addInitNode(a),E.addInitProxy(u),E.addInitRoot(r),E.addBeforeA(f),E.addZTreeTools(l);var x=w.createNodes;w.createNodes=function(e,t,n,r){x&&x.apply(w,arguments);if(!n)return;w.repairParentChkClassWithSelf(e,r)};var T=w.removeNode;w.removeNode=function(e,t){var n=t.getParentNode();T&&T.apply(w,arguments);if(!t||!n)return;w.repairChkClass(e,n),w.repairParentChkClass(e,n)};var N=w.appendNodes;w.appendNodes=function(e,t,n,r,i,s){var o="";return N&&(o=N.apply(w,arguments)),r&&E.makeChkFlag(e,r),o}})(jQuery)}});