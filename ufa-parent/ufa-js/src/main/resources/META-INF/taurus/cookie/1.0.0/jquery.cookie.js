define("taurus/cookie/1.0.0/jquery.cookie",[],function(e){return function(e){e.cookie=function(t,n,r){if(typeof n=="undefined"){var f=null;if(document.cookie&&document.cookie!=""){var l=document.cookie.split(";");for(var c=0;c<l.length;c++){var h=e.trim(l[c]);if(h.substring(0,t.length+1)==t+"="){f=decodeURIComponent(h.substring(t.length+1));break}}}return f}r=r||{},n===null&&(n="",r.expires=-1);var i="";if(r.expires&&(typeof r.expires=="number"||r.expires.toUTCString)){var s;typeof r.expires=="number"?(s=new Date,s.setTime(s.getTime()+r.expires*24*60*60*1e3)):s=r.expires,i="; expires="+s.toUTCString()}var o=r.path?"; path="+r.path:"",u=r.domain?"; domain="+r.domain:"",a=r.secure?"; secure":"";document.cookie=[t,"=",encodeURIComponent(n),i,o,u,a].join("")}}});