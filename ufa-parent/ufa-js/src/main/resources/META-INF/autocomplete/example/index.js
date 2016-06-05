;define(function(require){
    require('../../taurus/autocomplete/1.1.0/autocomplete.js')($);
        $('#down').AutoComplete({
                                    'data': ['One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine', 'Ten', 'Eleven', 'Twelve'],
                                    'itemHeight': 20,
                                    'width': 280
                                }).AutoComplete('show');

    $('#up').AutoComplete({
        'data': ['One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine', 'Ten', 'Eleven', 'Twelve'],
        'itemHeight': 20,
        'listDirection': 'up',
        'width': 280
        }).AutoComplete('show');

    var teams = [{
        'label': '江苏舜天队',
        'value': '江苏舜天队',
        'win': 14,
        'tie': 9,
        'lose': 3,
        'score': 51
        },{
        'label': '广州恒大队',
        'value': '广州恒大队',
        'win': 15,
        'tie': 6,
        'lose': 5,
        'score': 51
        },{
        'label': '广州富力队',
        'value': '广州富力队',
        'win': 13,
        'tie': 2,
        'lose': 11,
        'score': 41
        },{
        'label': '北京国安队',
        'value': '北京国安队',
        'win': 12,
        'tie': 5,
        'lose': 9,
        'score': 41
        },{
        'label': '贵州人和队',
        'value': '贵州人和队',
        'win': 10,
        'tie': 7,
        'lose': 9,
        'score': 37
        },{
        'label': '天津泰达队',
        'value': '天津泰达队',
        'win': 9,
        'tie': 8,
        'lose': 9,
        'score': 35
        },{
        'label': '长春亚泰队',
        'value': '长春亚泰队',
        'win': 9,
        'tie': 8,
        'lose': 9,
        'score': 35
        },{
        'label': '上海申花队',
        'value': '上海申花队',
        'win': 7,
        'tie': 13,
        'lose': 6,
        'score': 34
        },{
        'label': '大连阿尔滨队',
        'value': '大连阿尔滨队',
        'win': 8,
        'tie': 10,
        'lose': 8,
        'score': 34
        },{
        'label': '山东鲁能队',
        'value': '山东鲁能队',
        'win': 7,
        'tie': 10,
        'lose': 9,
        'score': 31
        },{
        'label': '大连实德队',
        'value': '大连实德队',
        'win': 7,
        'tie': 10,
        'lose': 9,
        'score': 31
        },{
        'label': '青岛中能队',
        'value': '青岛中能队',
        'win': 8,
        'tie': 6,
        'lose': 12,
        'score': 30
        },{
        'label': '辽宁宏运队',
        'value': '辽宁宏运队',
        'win': 6,
        'tie': 11,
        'lose': 9,
        'score': 29
        },{
        'label': '杭州绿城队',
        'value': '杭州绿城队',
        'win': 7,
        'tie': 8,
        'lose': 11,
        'score': 29
        },{
        'label': '上海申鑫队',
        'value': '上海申鑫队',
        'win': 5,
        'tie': 11,
        'lose': 10,
        'score': 26
        },{
        'label': '河南建业队',
        'value': '河南建业队',
        'win': 7,
        'tie': 4,
        'lose': 15,
        'score': 25
        }];

    $.each(teams, function(index, data){
        new Image().src = 'image/football/' + data['value'] + '.jpg'; // 预加载图片
        data['image'] = 'image/football/' + data['value'] + '.jpg';
        });

    $('#custom').AutoComplete({
        'data': teams,
        'width':280,
        'listStyle': 'custom',
        'maxHeight': 480,
        'createItemHandler': function(index, data){
        var div = $("<div></div>")
        var cell1 = $("<div style='display:table-cell;vertical-align:top;'></div>").appendTo(div);
        var cell1_1 = $("<img style='width:32px;height:32px;'/>").attr('src', data.image).appendTo(cell1);
        var cell2 = $("<div style='display:table-cell;vertical-align:top;'></div>").appendTo(div);
        var cell2_1 = $("<div></div>").append(data.label).appendTo(cell2);
        var cell2_2 = $("<div style='vertical-align:top;'></div>")
        .append("<div style='display:table-cell;width:40px;'>胜:"+data.win+"</span>")
        .append("<div style='display:table-cell;width:40px;'>平:"+data.tie+"</span>")
        .append("<div style='display:table-cell;width:40px;'>负:"+data.lose+"</span>")
        .append("<div style='display:table-cell;width:40px;'>积:"+data.score+"</span>")
        .appendTo(cell2);
        return div;
        }
    }).AutoComplete('show');

    var alltest = {};

    alltest.testIconList = function(input){
        var world = ['Cambodia', 'Cameroon', 'Canada', 'Cape-Verde', 'Cayman-Islands', 'Central-African-Republic', 'Chad', 'Chile', 'China', 'Colombia', 'Commonwealth', 'Comoros', 'Costa-Rica', "Cote-d'Ivoire", 'Croatia', 'Cuba', 'Cyprus', 'Czech-Republic'],
            result = [];

        $.each(world, function(index, name){
            new Image().src = 'image/worldFlag/' + name + '.png'; // 预加载图片
            result.push({
                            'value': name,
                            'label': name,
                            'image': 'image/worldFlag/' + name + '.png'
                        });
        });

        $(input).AutoComplete({
                                  'data': result,
                                  'listStyle': 'iconList',
                                  'itemHeight': 24,
                                  'width': 280,
                                  'onerror': function(msg){alert(msg);}
                              });
    }

    alltest.testIconList('#iconlist');
    $('#iconlist').AutoComplete('show');


});