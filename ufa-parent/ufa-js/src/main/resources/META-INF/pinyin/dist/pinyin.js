define("taurus/pinyin/1.0.0/pinyin",[],function(a,b){"use strict";function c(a){var e,f,j,l,m,n,o,p,c=b.cache||h(),d=a.length,i=[""],k=[""];for(n=0;d>n;n++)if(e=g(a[n],c),1===e.length)for(o=0,j=i.length;j>o;o++)i[o]+=e[0],k[o]+=e[0].charAt(0);else for(m=i.concat(),l=k.concat(),j=m.length,i=[],k=[],o=0,f=e.length;f>o;o++)for(p=0;j>p;p++)i.push(m[p]+e[o]),k.push(l[p]+e[o].charAt(0));return i.concat(k).join(",")}function d(a){var e,f,j,k,l,m,n,c=b.cache||h(),d=a.length,i=[""];for(l=0;d>l;l++)if(e=g(a[l],c),1===e.length)for(m=0,j=i.length;j>m;m++)i[m]+=e.toString();else for(k=i.concat(),j=k.length,i=[],m=0,f=e.length;f>m;m++)for(n=0;j>n;n++)i.push(k[n]+e[m]);return i.join(",")}function e(a){var e,f,j,k,l,m,n,c=b.cache||h(),d=a.length,i=[""];for(l=0;d>l;l++)if(e=g(a[l],c),1===e.length)for(m=0,j=i.length;j>m;m++)i[m]+=e[0].charAt(0);else for(k=i.concat(),j=k.length,i=[],m=0,f=e.length;f>m;m++)for(n=0;j>n;n++)i.push(k[n]+e[m].charAt(0));return i.join(",")}function f(a){var b=a.charCodeAt(0);return b>=19968&&40869>=b}function g(a,b){return f(a)&&b[a]?b[a]:[a]}function h(){var d,e,f,g,h,a={},c=i||{};for(f in c)if(c.hasOwnProperty(f))for(d=c[f],g=0,h=d.length;h>g;g++)e=d.charAt(g),a[e]||(a[e]=[]),a[e].push(f);return b.cache=a,a}b.cache=null,b.make=function(a,b,f){if(f)for(var g=f.length,h=0;g>h;h++)a=a.replace(f[h],"");return"first_letter"===b?e(a):"all_with_first_letter"===b?c(a):d(a)};var i={a:"啊阿吖嗄腌锕",ai:"爱埃挨哎唉哀皑癌蔼矮艾碍隘捱嗳嗌嫒瑷暧砹锿霭",an:"安案按鞍氨俺暗岸胺谙埯揞犴庵桉铵鹌黯",ang:"肮昂盎",ao:"凹敖熬翱袄傲奥懊澳坳拗嗷岙廒遨媪骜獒聱螯鏊鳌鏖",ba:"芭捌扒叭吧笆八疤巴拔跋靶把耙坝霸罢爸茇菝岜灞钯粑鲅魃",bai:"白柏百摆佰败拜稗捭掰",ban:"版般办斑班搬扳颁板扮拌伴瓣半绊阪坂钣瘢癍舨",bang:"帮邦梆榜膀绑棒磅蚌镑傍谤蒡浜",bao:"保报包苞胞褒剥薄雹堡饱宝抱暴豹鲍爆炮曝瀑勹葆孢煲鸨褓趵龅",bei:"北备杯碑悲卑辈背贝钡倍狈惫焙被孛陂邶蓓呗悖碚鹎褙鐾鞴",ben:"本奔苯笨夯畚坌锛",beng:"蚌崩绷甭泵蹦迸嘣甏",bi:"比必币毕逼鼻鄙笔彼碧蓖蔽毙毖庇痹闭敝弊辟壁臂避陛匕俾芘荜荸萆薜吡哔狴庳愎滗濞弼妣婢嬖璧贲睥畀铋秕裨筚箅篦舭襞跸髀",bian:"变编边便鞭贬扁卞辨辩辫遍匾弁苄忭汴缏煸砭碥窆褊蝙笾鳊",biao:"表标彪膘婊骠飑飙飚镖镳瘭裱鳔",bie:"别鳖憋瘪蹩",bin:"彬斌濒滨宾摈傧豳缤玢槟殡膑镔髌鬓",bing:"并兵冰柄丙秉饼炳病屏禀邴摒槟",bo:"播博柏剥薄玻菠拨钵波勃搏铂箔伯帛舶脖膊渤泊驳卜亳啵饽檗擘礴钹鹁簸跛踣",bu:"不布部步捕卜哺补埠簿怖埔卟逋瓿晡钚钸醭",ca:"擦嚓礤",cai:"才采材彩猜裁财睬踩菜蔡",can:"参餐蚕残惭惨灿孱骖璨粲黪",cang:"藏苍舱仓沧伧",cao:"操糙槽曹草嘈漕螬艚",ce:"册策测厕侧恻",ceng:"曾层蹭噌",cha:"查插叉茬茶碴搽察岔差诧刹喳嚓猹馇汊姹杈楂槎檫锸镲衩",chai:"差拆柴豺侪钗瘥虿",chan:"产搀掺蝉馋谗缠铲阐颤冁谄蒇廛忏潺澶孱羼婵骣觇禅蟾躔",chang:"场常长昌猖尝偿肠厂敞畅唱倡裳伥鬯苌菖徜怅惝阊娼嫦昶氅鲳",chao:"超抄钞朝嘲潮巢吵炒绰剿怊晁焯耖",che:"车扯撤掣彻澈坼砗",chen:"称郴臣辰尘晨忱沉陈趁衬沈谌谶抻嗔宸琛榇碜龀",cheng:"成程称城撑橙呈乘惩澄诚承逞骋秤丞埕枨柽塍瞠铖铛裎蛏酲",chi:"持吃痴匙池迟弛驰耻齿侈尺赤翅斥炽傺墀茌叱哧啻嗤彳饬媸敕眵鸱瘛褫蚩螭笞篪豉踟魑",chong:"重充冲虫崇宠茺忡憧铳舂艟",chou:"抽酬畴踌稠愁筹仇绸瞅丑臭俦帱惆瘳雠",chu:"出除处础初橱厨躇锄雏滁楚储矗搐触畜亍刍怵憷绌杵楮樗褚蜍蹰黜",chuai:"揣搋啜嘬膪踹",chuan:"传川穿椽船喘串舛遄氚钏舡",chuang:"创疮窗幢床闯怆",chui:"吹炊捶锤垂椎陲棰槌",chun:"春椿醇唇淳纯蠢莼鹑蝽",chuo:"戳绰啜辍踔龊",ci:"次此词差疵茨磁雌辞慈瓷刺赐茈祠鹚糍",cong:"从聪葱囱匆丛苁淙骢琮璁枞",cou:"凑楱辏腠",cu:"促粗醋簇卒蔟徂猝殂酢蹙蹴",cuan:"蹿篡窜攒汆撺爨镩",cui:"摧崔催脆瘁粹淬翠衰萃啐悴璀榱毳",cun:"存村寸忖皴",cuo:"错措磋撮搓挫厝嵯脞锉矬痤瘥鹾蹉",da:"大达打答搭瘩耷哒嗒囗怛妲沓褡笪靼鞑",dai:"代带呆歹傣戴殆贷袋待逮怠埭甙呔岱迨绐玳黛",dan:"单但耽担丹郸掸胆旦氮惮淡诞弹蛋石儋凼萏菪啖澹宕殚赕眈疸瘅聃箪",dang:"当挡党荡档谠砀铛裆",dao:"到道导刀捣蹈倒岛祷稻悼盗叨氘焘纛",de:"的地得德锝",deng:"等登蹬灯瞪凳邓噔嶝戥磴镫簦",di:"的地第底弟堤低滴迪敌笛狄涤翟嫡抵蒂帝递缔氐籴诋谛邸坻荻嘀娣柢棣觌祗砥碲睇镝羝骶",dian:"电点典店颠掂滇碘靛垫佃甸惦奠淀殿丶阽坫巅玷钿癜癫簟踮",diao:"调碉叼雕凋刁掉吊钓铞貂鲷",die:"跌爹碟蝶迭谍叠垤堞揲喋牒瓞耋鲽",ding:"定丁盯叮钉顶鼎锭订仃啶玎腚碇町疔耵酊",diu:"丢铥",dong:"动东冬董懂栋侗恫冻洞垌咚岽峒氡胨胴硐鸫",dou:"都兜抖斗陡豆逗痘蔸钭窦蚪篼",du:"都度读督毒犊独堵睹赌杜镀肚渡妒芏嘟渎椟牍蠹笃髑黩",duan:"断段短端锻缎椴煅簖",dui:"对堆兑队敦怼憝碓镦",dun:"墩吨蹲敦顿囤钝盾遁沌炖砘礅盹趸",duo:"多度掇哆夺垛躲朵跺舵剁惰堕咄哚沲缍铎裰踱",e:"阿蛾峨鹅俄额讹娥恶厄扼遏鄂饿哦噩谔垩苊莪萼呃愕屙婀轭腭锇锷鹗颚鳄",en:"恩蒽摁嗯",er:"而二儿耳尔饵洱贰迩珥铒鸸鲕",fa:"发法罚筏伐乏阀珐垡砝",fan:"范藩帆番翻樊矾钒繁凡烦反返贩犯饭泛蕃蘩幡夂梵攵燔畈蹯",fang:"方放访房坊芳肪防妨仿纺邡枋钫舫鲂",fei:"费非菲啡飞肥匪诽吠肺废沸芾狒悱淝妃绯榧腓斐扉砩镄痱蜚篚翡霏鲱",fen:"分份芬酚吩氛纷坟焚汾粉奋忿愤粪偾瀵棼鲼鼢",feng:"风丰封枫蜂峰锋疯烽逢冯缝讽奉凤俸酆葑唪沣砜",fo:"佛",fou:"否缶",fu:"服复府父负福富夫敷肤孵扶拂辐幅氟符伏俘浮涪袱弗甫抚辅俯釜斧脯腑腐赴副覆赋傅付阜腹讣附妇缚咐莆匐凫郛芙芾苻茯莩菔拊呋呒幞怫滏艴孚驸绂绋桴赙祓黻黼罘稃馥蚨蜉蝠蝮麸趺跗鲋鳆",ga:"噶嘎夹轧垓尬尕尜旮钆",gai:"改该概钙盖溉芥丐陔戤赅",gan:"感敢干甘杆柑竿肝赶秆赣坩苷尴擀泔淦澉绀橄旰矸疳酐",gang:"港冈刚钢缸肛纲岗杠戆罡筻",gao:"告高篙皋膏羔糕搞镐稿睾诰郜藁缟槔槁杲锆",ge:"个格合歌各革哥搁戈鸽胳疙割葛蛤阁隔铬咯鬲仡哿圪塥嗝纥搿膈硌镉袼颌虼舸骼",gei:"给",gen:"根跟亘茛哏艮",geng:"更耕庚羹埂耿梗哽赓绠鲠",gong:"公工供功共贡攻恭龚躬宫弓巩汞拱珙肱蚣觥",gou:"构够购钩勾沟苟狗垢佝诟岣遘媾缑枸觏彀笱篝鞲",gu:"告故辜菇咕箍估沽孤姑鼓古蛊骨谷股顾固雇贾嘏诂菰崮汩梏轱牯牿臌毂瞽罟钴锢鸪鹄痼蛄酤觚鲴鹘",gua:"刮瓜剐寡挂褂卦诖呱栝胍鸹",guai:"乖拐怪掴",guan:"关管观棺官冠馆罐惯灌贯纶倌莞掼涫盥鹳矜鳏",guang:"广光逛咣犷桄胱",gui:"规瑰圭硅归龟闺轨鬼诡癸桂柜跪贵刽匦刿庋宄妫桧炅晷皈簋鲑鳜",gun:"辊滚棍衮绲磙鲧",guo:"国过果锅郭裹馘埚掴呙帼崞猓椁虢聒蜾蝈",ha:"哈蛤铪",hai:"还海孩骸氦亥害骇嗨胲醢",han:"韩酣憨邯含涵寒函喊罕翰撼捍旱憾悍焊汗汉邗菡撖犴阚瀚晗焓顸颔蚶鼾",hang:"行杭夯航吭沆绗颃",hao:"好号壕嚎豪毫郝耗浩貉蒿薅嗥嚆濠灏昊皓颢蚝",he:"何合呵喝荷菏核禾和盒貉阂河涸赫褐鹤贺诃劾壑嗬阖曷盍颌蚵翮",hei:"嘿黑",hen:"很痕狠恨",heng:"哼亨横衡恒蘅珩桁",hong:"轰哄烘虹鸿洪宏弘红黉訇讧荭蕻薨闳泓",hou:"后候喉侯猴吼厚堠後逅瘊篌糇鲎骺",hu:"户护乎互和呼忽瑚壶葫胡蝴狐糊湖弧虎唬沪冱唿囫岵猢怙惚浒滹琥槲轷觳烀煳戽扈祜瓠鹄鹕鹱笏醐斛鹘",hua:"话华化划花哗猾滑画骅桦砉铧",huai:"槐徊怀淮坏踝",huan:"还欢环桓缓换患唤痪豢焕涣宦幻郇奂萑擐圜獾洹浣漶寰逭缳锾鲩鬟",huang:"荒慌黄磺蝗簧皇凰惶煌晃幌恍谎隍徨湟潢遑璜肓癀蟥篁鳇",hui:"会回恢挥灰辉徽蛔毁悔慧卉惠晦贿秽烩汇讳诲绘诙茴荟蕙咴喙隳洄彗缋珲桧晖恚虺蟪麾",hun:"荤昏婚魂浑混诨馄阍溷珲",huo:"活或获和豁伙火惑霍货祸劐藿攉嚯夥钬锪镬耠蠖",ji:"系己机技计级积际及基击记济辑即几继集极给绩激圾畸稽箕肌饥迹讥鸡姬缉吉棘籍急疾汲嫉挤脊蓟冀季伎祭剂悸寄寂既忌妓纪藉骑亟乩剞佶偈墼芨芰荠蒺蕺掎叽咭哜唧岌嵴洎屐骥畿玑楫殛戟戢赍觊犄齑矶羁嵇稷瘠虮笈笄暨跻跽霁鲚鲫髻麂",jia:"家加价佳嘉枷夹荚颊贾甲钾假稼架驾嫁茄伽郏葭岬浃迦珈戛胛恝铗镓痂瘕蛱笳袈跏",jian:"间件建简荐见健检坚监减键歼尖笺煎兼肩艰奸缄茧柬碱硷拣捡俭剪槛鉴践贱箭舰剑饯渐溅涧僭谏谫菅蒹搛囝湔蹇謇缣枧楗戋戬牮犍毽腱睑锏鹣裥笕翦踺鲣鞯",jiang:"江强僵姜将浆疆蒋桨奖讲匠酱降茳洚绛缰犟礓耩糨豇靓",jiao:"教交校较蕉椒礁焦胶郊浇骄娇嚼搅铰矫侥脚狡角饺缴绞剿酵轿叫窖佼僬艽茭挢噍峤徼湫姣敫皎鹪蛟醮跤鲛",jie:"接结解介界阶姐揭皆秸街截劫节桔杰捷睫竭洁戒藉芥借疥诫届讦诘拮喈嗟婕孑桀碣疖颉蚧羯鲒骱",jin:"进今金近仅津尽巾筋斤襟紧锦谨靳晋禁烬浸劲卺荩堇噤馑廑妗缙瑾槿赆觐衿矜",jing:"经京精境睛竞竟荆兢茎晶鲸惊粳井警景颈静敬镜径痉靖净刭儆阱菁獍憬泾迳弪婧肼胫腈旌箐",jiong:"炯窘迥扃",jiu:"就究酒揪纠玖韭久灸九厩救旧臼舅咎疚僦啾阄柩桕鸠鹫赳鬏",ju:"具且据车举巨鞠拘狙疽居驹菊局咀矩沮聚拒距踞锯俱句惧炬剧倨讵苣苴莒掬遽屦琚枸椐榘榉橘犋飓钜锔窭裾趄醵踽龃雎鞫",juan:"捐鹃娟倦眷卷绢鄄狷涓桊蠲锩镌隽",jue:"觉决绝嚼撅攫抉掘倔爵诀厥劂谲矍蕨噘噱崛獗孓珏桷橛爝镢蹶觖",jun:"均菌钧军君峻俊竣浚郡骏捃皲筠麇",ka:"喀咖卡咯佧咔胩",kai:"开揩楷凯慨剀垲蒈忾恺铠锎锴",kan:"看槛刊堪勘坎砍侃莰阚戡龛瞰",kang:"康慷糠扛抗亢炕伉闶钪",kao:"考拷烤靠尻栲犒铐",ke:"可科客课坷苛柯棵磕颗壳咳渴克刻嗑岢恪溘骒缂珂轲氪瞌钶锞稞疴窠颏蝌髁",ken:"肯啃垦恳裉龈",keng:"坑吭铿",kong:"控空恐孔倥崆箜",kou:"口抠扣寇芤蔻叩囗眍筘",ku:"枯哭窟苦酷库裤刳堀喾绔骷",kua:"夸垮挎跨胯侉",kuai:"会快块筷侩蒯郐哙狯浍脍",kuan:"款宽髋",kuang:"况匡筐狂框矿眶旷诓诳邝圹夼哐纩贶",kui:"亏盔岿窥葵奎魁傀馈愧溃馗匮夔隗蒉揆喹喟悝愦逵暌睽聩蝰篑跬",kun:"坤昆捆困悃阃琨锟醌鲲髡",kuo:"括扩廓阔蛞",la:"垃拉喇蜡腊辣啦落剌邋旯砬瘌",lai:"来莱赖崃徕涞濑赉睐铼癞籁",lan:"览蓝婪栏拦篮阑兰澜谰揽懒缆烂滥岚漤榄斓罱镧褴",lang:"琅榔狼廊郎朗浪蒗啷阆稂螂",lao:"老捞劳牢佬姥酪烙涝潦唠崂忉栳铑铹痨耢醪",le:"了乐勒仂叻泐鳓",lei:"类雷镭蕾磊累儡垒擂肋泪羸诔嘞嫘缧檑耒酹",leng:"棱楞冷塄愣",li:"理里力立历离利丽厘梨犁黎篱狸漓李鲤礼莉荔吏栗厉励砾傈例俐痢粒沥隶璃哩俪俚郦坜苈莅蓠藜呖唳喱猁溧澧逦娌嫠骊缡枥栎轹膦戾砺詈罹锂鹂疠疬蛎蜊蠡笠篥粝醴跞雳鲡鳢黧",lia:"俩",lian:"联链连练脸莲镰廉怜涟帘敛恋炼蔹奁潋濂琏楝殓臁裢裣蠊鲢",liang:"量两良亮俩粮凉梁粱辆晾谅墚莨椋锒踉靓魉",liao:"了料疗撩聊僚燎寥辽潦撂镣廖蓼尥嘹獠寮缭钌鹩",lie:"列裂烈劣猎冽埒捩咧洌趔躐鬣",lin:"琳林磷霖临邻鳞淋凛赁吝拎蔺啉嶙廪懔遴檩辚瞵粼躏麟",ling:"领另铃令玲菱零龄伶羚凌灵陵岭酃苓呤囹泠绫柃棂瓴聆蛉翎鲮",liu:"留浏流溜琉榴硫馏刘瘤柳六遛骝绺旒熘锍镏鹨鎏","long":"龙聋咙笼窿隆垄拢陇垅茏泷珑栊胧砻癃",lou:"楼娄搂篓漏陋露偻蒌喽嵝镂瘘耧蝼髅",lu:"录陆芦卢颅庐炉掳卤虏鲁麓碌露路赂鹿潞禄戮垆撸噜泸渌漉逯璐栌橹轳辂辘氇胪镥鸬鹭簏舻鲈",lv:"律旅虑驴吕铝侣履屡缕氯率滤绿偻捋闾榈膂稆褛",luan:"峦挛孪滦卵乱脔娈栾鸾銮",lue:"略掠锊",lun:"论抡轮伦仑沦纶囵",luo:"络咯烙萝螺罗逻锣箩骡裸落洛骆倮蠃荦摞猡泺漯珞椤脶镙瘰跞雒",ma:"码妈马麻玛蚂骂嘛吗抹唛犸杩蟆麽",mai:"买埋麦卖迈脉劢荬霾",man:"满瞒馒蛮蔓曼慢漫谩墁幔缦熳镘颟螨鳗鞔",mang:"芒茫盲氓忙莽邙漭硭蟒",mao:"贸猫茅锚毛矛铆卯茂冒帽貌袤茆峁泖瑁昴牦耄旄懋瞀蝥蟊髦",me:"么麽",mei:"没美每媒魅玫枚梅酶霉煤眉镁昧寐妹媚糜莓嵋猸浼湄楣镅鹛袂",men:"们门闷扪焖懑钔",meng:"盟萌蒙檬锰猛梦孟勐甍瞢懵朦礞虻蜢蠓艋艨",mi:"密眯醚靡糜迷谜弥米秘觅泌蜜幂芈谧咪嘧猕汨宓弭脒祢敉縻麋",mian:"面免棉眠绵冕勉娩缅沔渑湎腼眄",miao:"描苗瞄藐秒渺庙妙喵邈缈缪杪淼眇鹋",mie:"蔑灭乜咩蠛篾",min:"民抿皿敏悯闽苠岷闵泯缗玟珉愍黾鳘",ming:"明名命螟鸣铭冥茗溟暝瞑酩",miu:"谬缪",mo:"没模脉摸摹蘑膜磨摩魔抹末莫墨默沫漠寞陌谟茉蓦馍嫫嬷殁镆秣瘼耱貊貘麽",mou:"谋牟某侔哞缪眸蛑鍪",mu:"目母牟拇牡亩姆墓暮幕募慕木睦牧穆仫坶苜沐毪钼",na:"那拿哪呐钠娜纳讷捺肭镎衲",nai:"氖乃奶耐奈鼐佴艿萘柰",nan:"男南难喃囝囡楠腩蝻赧",nang:"囊攮囔馕曩",nao:"脑挠恼闹淖孬垴呶猱瑙硇铙蛲",ne:"呢讷",nei:"内馁",nen:"嫩恁",neng:"能",ni:"你妮霓倪泥尼拟匿腻逆溺伲坭蘼猊怩昵旎睨铌鲵",nian:"年蔫拈碾撵捻念粘廿埝辇黏鲇鲶",niang:"娘酿",niao:"鸟尿茑嬲脲袅",nie:"捏聂孽啮镊镍涅乜陧蘖嗫颞臬蹑",nin:"您",ning:"柠狞凝宁拧泞佞咛甯聍",niu:"牛扭钮纽拗狃忸妞",nong:"农脓浓弄侬哝",nu:"努奴怒弩胬孥驽",nv:"女恧钕衄",nuan:"暖",nue:"虐疟挪",nuo:"娜懦糯诺傩搦喏锘",o:"哦噢",ou:"欧鸥殴藕呕偶沤讴怄瓯耦",pa:"扒耙啪趴爬帕怕琶葩杷筢",pai:"牌派排拍徘湃俳蒎哌",pan:"攀潘盘磐盼畔判叛胖拚爿泮袢襻蟠蹒",pang:"磅乓庞旁耪胖彷夂滂逄攵螃",pao:"抛咆刨炮袍跑泡匏狍庖脬疱",pei:"培配呸胚裴赔陪佩沛辔帔旆锫醅霈",pen:"喷盆湓",peng:"朋砰抨烹澎彭蓬棚硼篷膨鹏捧碰堋嘭怦蟛",pi:"否被辟坯砒霹批披劈琵毗啤脾疲皮匹痞僻屁譬丕仳陴邳郫圮埤鼙芘擗噼庀淠媲纰枇甓罴铍癖疋蚍蜱貔",pian:"片便篇偏骗谝骈犏胼翩蹁",piao:"漂飘瓢票剽莩嘌嫖缥殍瞟螵",pie:"撇瞥丿苤彡氕",pin:"品频聘拼贫姘嫔榀牝颦",ping:"评平冯乒坪苹萍凭瓶屏俜娉枰鲆",po:"坡泼颇婆破魄迫粕叵鄱珀钋钷皤笸",pu:"普暴扑铺仆莆葡菩蒲埔朴圃浦谱曝瀑匍噗溥濮璞氆镤镨蹼",qi:"其企起期汽器启气奇缉欺栖戚妻七凄漆柒沏棋歧畦崎脐齐旗祈祁骑岂乞契砌迄弃泣讫亓俟圻芑芪萁萋葺蕲嘁屺岐汔淇骐绮琪琦杞桤槭耆祺憩碛颀蛴蜞綦鳍麒",qia:"夹掐恰洽葜袷髂",qian:"前钱牵扦钎铅千迁签仟谦乾黔钳潜遣浅谴堑嵌欠歉纤倩佥阡芊芡茜荨掮岍悭慊骞搴褰缱椠肷愆钤虔箝",qiang:"强将枪呛腔羌墙蔷抢戕嫱樯戗炝锖锵镪襁蜣羟跄",qiao:"壳橇锹敲悄桥瞧乔侨巧鞘撬翘峭俏窍削劁诮谯荞峤愀憔缲樵硗跷鞒",qie:"且切茄怯窃郄惬慊妾挈锲箧趄",qin:"亲钦侵秦琴勤芹擒禽寝沁芩揿吣嗪噙溱檎锓矜覃螓衾",qing:"情请庆轻青氢倾卿清擎晴氰顷苘圊檠磬蜻罄綮謦鲭黥",qiong:"琼穷邛茕穹蛩筇跫銎",qiu:"求球秋丘邱囚酋泅俅巯犰湫逑遒楸赇虬蚯蝤裘糗鳅鼽",qu:"区去取曲趋蛆躯屈驱渠娶龋趣诎劬苣蕖蘧岖衢阒璩觑氍朐祛磲鸲癯蛐蠼麴瞿黢",quan:"全权圈颧醛泉痊拳犬券劝诠荃犭悛绻辁畎铨蜷筌鬈",que:"确缺炔瘸却鹊榷雀阕阙悫",qun:"裙群逡麇",ran:"然燃冉染苒蚺髯",rang:"让瓤壤攘嚷禳穰",rao:"饶扰绕荛娆桡",re:"惹热喏",ren:"人任认壬仁忍韧刃妊纫仞荏饪轫稔衽",reng:"扔仍",ri:"日",rong:"容戎茸蓉荣融熔溶绒冗嵘狨榕肜蝾",rou:"揉柔肉糅蹂鞣",ru:"如入茹蠕儒孺辱乳汝褥蓐薷嚅洳溽濡缛铷襦颥",ruan:"软阮朊",rui:"蕊瑞锐芮蕤枘睿蚋",run:"闰润",ruo:"若弱偌箬",sa:"撒洒萨卅挲脎飒",sai:"赛腮鳃塞噻",san:"三叁伞散仨彡馓毵糁",sang:"桑嗓丧搡磉颡",sao:"搔骚扫嫂埽缫臊瘙鳋",se:"色塞瑟涩啬铯穑",sen:"森",seng:"僧",sha:"莎砂杀刹沙纱傻啥煞厦唼挲歃铩痧裟霎鲨",shai:"筛晒酾",shan:"删山珊苫杉煽衫闪陕擅赡膳善汕扇缮栅剡讪鄯埏芟潸姗嬗骟膻钐疝蟮舢跚鳝",shang:"上商尚赏墒伤晌裳垧泷绱殇熵觞",shao:"少绍鞘梢捎稍烧芍勺韶哨邵劭苕潲杓蛸筲艄",she:"设社奢赊蛇舌舍赦摄射慑涉厍佘揲猞滠歙畲麝",shen:"什身参深神甚申砷呻伸娠绅沈审婶肾慎渗诜谂莘葚哂渖椹胂矧蜃糁",sheng:"生声乘甥牲升绳省盛剩胜圣嵊渑晟眚笙",shi:"是时式什市实使事示始世施识视试师史十食势释失室匙狮湿诗尸虱石拾蚀矢屎驶士柿拭誓逝嗜噬适仕侍饰氏恃嘘谥埘莳蓍弑轼贳炻礻铈螫舐筮豕鲥鲺",shou:"手首受授收售守寿瘦兽狩绶艏",shu:"数术束输属殊述蔬枢梳抒叔舒淑疏书赎孰熟薯暑曙署蜀黍鼠树戍竖墅庶漱恕俞丨倏塾菽摅沭澍姝纾毹腧殳秫",shua:"刷耍唰",shuai:"率摔衰甩帅蟀",shuan:"栓拴闩涮",shuang:"霜双爽孀",shui:"说水谁睡税",shun:"吮瞬顺舜",shuo:"数说硕朔烁蒴搠妁槊铄",si:"司思似食四斯撕嘶私丝死肆寺嗣伺饲巳厮俟兕厶咝汜泗澌姒驷缌祀锶鸶耜蛳笥",song:"松送耸怂颂宋讼诵凇菘崧嵩忪悚淞竦",sou:"搜艘擞嗽叟薮嗖嗾馊溲飕瞍锼螋",su:"速诉苏素酥俗粟僳塑溯宿肃缩夙谡蔌嗉愫涑簌觫稣",suan:"算酸蒜狻",sui:"虽随隋绥髓碎岁穗遂隧祟谇荽濉邃燧眭睢",sun:"孙损笋荪狲飧榫隼",suo:"所索莎蓑梭唆缩琐锁唢嗦嗍娑桫挲睃羧",ta:"他它她塌塔獭挞蹋踏嗒闼溻漯遢榻沓铊趿鳎",tai:"态台胎苔抬泰酞太汰邰薹骀肽炱钛跆鲐",tan:"坛弹坍摊贪瘫滩檀痰潭谭谈坦毯袒碳探叹炭郯澹昙忐钽锬覃",tang:"汤塘搪堂棠膛唐糖倘躺淌趟烫傥帑惝溏瑭樘铴镗耥螗螳羰醣",tao:"讨掏涛滔绦萄桃逃淘陶套鼗叨啕洮韬饕",te:"特忒忑慝铽",teng:"藤腾疼誊滕",ti:"题提体梯剔踢锑蹄啼替嚏惕涕剃屉倜荑悌逖绨缇鹈裼醍",tian:"天添填田甜恬舔腆掭忝阗殄畋",tiao:"调条挑迢眺跳佻苕祧窕蜩笤粜龆鲦髫",tie:"帖贴铁萜餮",ting:"庭听厅烃汀廷停亭挺艇莛葶婷梃铤蜓霆",tong:"同统通桐酮瞳铜彤童桶捅筒痛佟仝茼嗵恸潼砼",tou:"投头偷透骰",tu:"图突凸秃徒途涂屠土吐兔堍荼菟钍酴",tuan:"团湍抟彖疃",tui:"推颓腿蜕褪退忒煺",tun:"囤褪吞屯臀氽饨暾豚",tuo:"拖托脱鸵陀驮驼椭妥拓唾乇佗坨庹沱柝橐砣箨酡跎鼍",wa:"挖哇蛙洼娃瓦袜佤娲腽",wai:"外歪崴",wan:"完湾万晚玩蔓豌弯顽丸烷碗挽皖惋宛婉腕剜芄莞菀纨绾琬脘畹蜿",wang:"网望汪王亡枉往旺忘妄罔惘辋魍",wei:"为位威未围维委巍微危韦违桅唯惟潍苇萎伟伪尾纬蔚味畏胃喂魏渭谓尉慰卫偎诿隈隗圩葳薇帏帷崴嵬猥猬闱沩洧涠逶娓玮韪軎炜煨痿艉鲔",wen:"文问闻稳瘟温蚊纹吻紊刎夂阌汶璺攵雯",weng:"嗡翁瓮蓊蕹",wo:"我挝蜗涡窝斡卧握沃倭莴喔幄渥肟硪龌",wu:"务无误物午恶巫呜钨乌污诬屋芜梧吾吴毋武五捂舞伍侮坞戊雾晤勿悟兀仵阢邬圬芴唔庑怃忤寤迕妩婺骛杌牾焐鹉鹜痦蜈鋈鼯",xi:"系息戏习希细西喜析栖昔熙硒矽晰嘻吸锡牺稀悉膝夕惜熄烯溪汐犀檄袭席媳铣洗隙僖兮隰郗茜菥葸蓰奚唏徙饩阋浠淅屣嬉玺樨曦觋欷歙熹禊禧皙穸裼蜥螅蟋舄舾羲粞翕醯蹊鼷",xia:"下瞎虾匣霞辖暇峡侠狭厦夏吓呷狎遐瑕柙硖罅黠",xian:"现限线显先见献衔险铣掀锨仙鲜纤咸贤舷闲涎弦嫌县腺馅羡宪陷冼苋莶藓岘猃暹娴氙燹祆鹇痫蚬筅籼酰跣跹霰",xiang:"相想项享详象响香像向降厢镶箱襄湘乡翔祥巷橡芗葙饷庠骧缃蟓鲞飨",xiao:"小销消效校萧硝霄削哮嚣宵淆晓孝肖啸笑哓崤潇逍骁绡枭枵蛸筱箫魈",xie:"些谢械楔歇蝎鞋协挟携邪斜胁谐写卸蟹懈泄泻屑偕亵勰燮薤撷獬廨渫瀣邂绁缬榭榍颉蹀躞",xin:"信新心欣薪芯锌辛忻衅囟馨莘昕歆镡鑫",xing:"行形型性幸姓省星腥猩惺兴刑邢醒杏陉荇荥擤饧悻硎",xiong:"兄凶胸匈汹雄熊芎",xiu:"修秀臭休羞朽嗅锈袖绣咻岫馐庥溴鸺貅髹",xu:"需许须序续墟戌虚嘘徐蓄酗叙旭畜恤絮婿绪吁诩勖圩蓿洫溆顼栩肷煦盱胥糈醑",xuan:"选宣轩喧悬旋玄癣眩绚儇谖萱揎泫渲漩璇楦暄炫煊碹铉镟痃",xue:"学削靴薛穴雪血谑噱泶踅鳕",xun:"询训讯迅寻浚勋熏循旬驯巡殉汛逊巽郇埙荀荨蕈薰峋徇獯恂洵浔曛窨醺鲟",ya:"压押鸦鸭呀丫芽牙蚜崖衙涯雅哑亚讶轧伢垭揠岈迓娅琊桠氩砑睚痖",yan:"言研验眼严颜焉咽阉烟淹盐蜒岩延阎炎沿奄掩衍演艳堰燕厌砚雁唁彦焰宴谚殷厣赝剡俨偃兖谳郾鄢埏芫菸崦恹闫阏湮滟妍嫣琰檐晏胭腌焱罨筵酽趼魇餍鼹",yang:"样央阳养殃鸯秧杨扬佯疡羊洋氧仰痒漾徉怏泱炀烊恙蛘鞅",yao:"要邀腰妖瑶摇尧遥窑谣姚咬舀药耀钥夭爻吆崾徭幺珧杳轺曜肴铫鹞窈繇鳐麽",ye:"业也页椰噎耶爷野冶掖叶曳腋夜液拽靥谒邺揶揲晔烨铘",yi:"一以已意易议医移艺益义艾蛇壹揖铱依伊衣颐夷遗仪胰疑沂宜姨彝椅蚁倚乙矣抑邑屹亿役臆逸肄疫亦裔毅忆溢诣谊译异翼翌绎刈劓佚佾诒圯埸懿苡荑薏弈奕挹弋呓咦咿嗌噫峄嶷猗饴怿怡悒漪迤驿缢殪轶贻欹旖熠眙钇镒镱痍瘗癔翊蜴舣羿翳酏黟",yin:"因音引银茵荫殷阴姻吟淫寅饮尹隐印胤鄞垠堙茚吲喑狺夤洇氤铟瘾窨蚓霪龈",ying:"影应营英迎樱婴鹰缨莹萤荧蝇赢盈颖硬映嬴郢茔荥莺萦蓥撄嘤膺滢潆瀛瑛璎楹媵鹦瘿颍罂",yo:"哟唷",yong:"用拥永佣臃痈庸雍踊蛹咏泳涌恿勇俑壅墉喁慵邕镛甬鳙饔",you:"有游由友优右邮幽悠忧尤铀犹油酉佑釉诱又幼卣攸侑莠莜莸尢呦囿宥柚猷牖铕疣蚰蚴蝣蝤繇鱿黝鼬",yu:"于育语域娱与遇尉迂淤盂榆虞愚舆余俞逾鱼愉渝渔隅予雨屿禹宇羽玉芋郁吁喻峪御愈欲狱誉浴寓裕预豫驭粥禺毓伛俣谀谕萸蓣揄圄圉嵛狳饫馀庾阈鬻妪妤纡瑜昱觎腴欤於煜熨燠聿畲钰鹆鹬瘐瘀窬窳蜮蝓竽臾舁雩龉",yuan:"员原源元院远鸳渊冤垣袁援辕园圆猿缘苑愿怨垸塬芫掾圜沅媛瑗橼爰眢鸢螈箢鼋",yue:"说乐阅越曰约跃钥岳粤月悦龠哕瀹栎樾刖钺",yun:"运耘云郧匀陨允蕴酝晕韵孕郓芸狁恽愠纭韫殒昀氲熨筠",za:"匝砸杂咋拶咂",zai:"在载子再栽哉灾宰崽甾",zan:"暂咱攒赞瓒昝簪糌趱錾",zang:"赃脏葬奘驵臧藏",zao:"造遭糟凿藻枣早澡蚤躁噪皂灶燥唣",ze:"责择则泽咋仄赜啧帻迮昃笮箦舴",zei:"贼",zen:"怎谮",zeng:"增综曾憎赠缯甑罾锃",zha:"扎喳渣札轧铡闸眨栅榨咋乍炸诈柞揸吒咤哳楂砟痄蚱齄",zhai:"翟摘斋宅窄债寨砦瘵",zhan:"站展战瞻毡詹粘沾盏斩辗崭蘸栈占湛绽谵搌旃",zhang:"章长张樟彰漳掌涨杖丈帐账仗胀瘴障仉鄣幛嶂獐嫜璋蟑",zhao:"着照找招朝昭沼赵罩兆肇召爪诏棹钊笊",zhe:"这者着浙遮折哲蛰辙锗蔗乇谪摺柘辄磔鹧褶蜇螫赭",zhen:"真圳珍斟甄砧臻贞针侦枕疹诊震振镇阵帧蓁浈缜桢椹榛轸赈胗朕祯畛稹鸩箴",zheng:"正政整证争蒸挣睁征狰怔拯症郑诤峥徵钲铮筝",zhi:"只之知制置址支直至织治质执职值致指志芝枝吱蜘肢脂汁植殖侄止趾旨纸挚掷帜峙智秩稚炙痔滞窒卮陟郅埴芷摭帙忮彘咫骘栉枳栀桎轵轾贽胝膣祉黹雉鸷痣蛭絷酯跖踬踯豸觯",zhong:"中种重终盅忠钟衷肿仲众冢忪锺螽舯踵",zhou:"州舟周洲诌粥轴肘帚咒皱宙昼骤荮啁妯纣绉胄碡籀繇酎",zhu:"注主助筑属珠株蛛朱猪诸诛逐竹烛煮拄瞩嘱著柱蛀贮铸住祝驻伫侏邾苎茱洙渚潴杼槠橥炷铢疰瘃竺箸舳翥躅麈",zhua:"挝抓爪",zhuai:"拽",zhuan:"专传转砖撰赚篆啭馔颛",zhuang:"状装幢桩庄妆撞壮僮",zhui:"椎锥追赘坠缀萑惴骓缒隹",zhun:"准屯谆肫窀",zhuo:"着捉拙卓桌琢茁酌啄灼浊倬诼擢浞涿濯禚斫镯",zi:"自资子字咨兹姿滋淄孜紫仔籽滓渍谘茈呲嵫姊孳缁梓辎赀恣眦锱秭耔笫粢趑觜訾龇鲻髭",zong:"综总鬃棕踪宗纵偬腙粽",zou:"邹走奏揍诹陬鄹驺鲰",zu:"组足租卒族祖诅阻俎菹镞",zuan:"钻纂攥缵躜",zui:"最嘴醉罪蕞觜",zun:"尊遵撙樽鳟",zuo:"作左昨佐柞做坐座阼唑嘬怍胙祚笮酢"}});