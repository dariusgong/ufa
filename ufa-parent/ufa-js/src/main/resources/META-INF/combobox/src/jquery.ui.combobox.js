define(function(require){return function(jQuery){
    require('./jquery.ui.autocomplete.js')(jQuery);
    require('button')(jQuery);
    (function( $ ) {
        $.widget( "ui.combobox", {
            options: jQuery.extend({
                inputClass : null,
                emptyText: '',
                needPinyin: false,
                pinyinType: null,
                ignoreStr: null,
                displayPinyin: false
            }, this.options),
            _create: function() {
                var input,
                    that = this,
                    wasOpen = false,
                    select = this.element.hide(),
                    selected = select.children( ":selected" ),
                    selectedItem = select.children( ":selected" )[0],
                    value = selected.val() ? selected.text() : that.options.emptyText,
                    wrapper = this.wrapper = $( "<span>" )
                        .addClass( "ui-combobox" )
                        .insertAfter( select );

                function removeIfInvalid( element ) {
                    var value = $( element ).val(),
                        matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( value ) + "$", "i" ),
                        valid = false;
                    select.children( "option" ).each(function() {
                        var text = $( this ).text();
                        if (that.options.needPinyin && typeof $( this).data('original-text') !== 'undefined') {
                            text = $( this).data('original-text');
                        }
                        if ( text.match( matcher ) ) {
                            this.selected = valid = true;
                            return false;
                        }
                    });

                    if ( !valid ) {
                        // remove invalid value, as it didn't match anything
                        $( element )
                            .val( that.options.emptyText );
                        select.val( "" );
                        input.data( "ui-autocomplete" ).term = "";
                        selectedItem = null;
                    }
                }

                if (that.options.needPinyin) {
                    var pinyinUtil = require('pinyin');
                    select.children('option').each(function() {
                        var text = $(this).text(),
                            pinyin = pinyinUtil.make(text, that.options.pinyinType, that.options.ignoreStr);
                        $(this).text(text + pinyin).data('original-text', text);
                    });
                }

                input = $( "<input>" )
                    .appendTo( wrapper )
                    .val( value )
                    .addClass( "ui-state-default ui-combobox-input " + that.options.inputClass ? that.options.inputClass : "" )
                    .autocomplete({
                        delay: 0,
                        minLength: 0,
                        source: function( request, response ) {
                            var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
                            response( select.children( "option" ).map(function() {
                                var text = $( this ).text(),
                                    label = text;
                                if ($(this).data('original-text') !== undefined && !that.options.displayPinyin) {
                                    // if need to hide the pinyin,must bind option's original text to the option
                                    label = $( this).data('original-text');
                                }
                                if ( this.value && ( !request.term || matcher.test(text) ) ){
                                    return {
                                        label: label.replace(
                                            new RegExp(
                                                "(?![^&;]+;)(?!<[^<>]*)(" +
                                                    $.ui.autocomplete.escapeRegex(request.term) +
                                                    ")(?![^<>]*>)(?![^&;]+;)", "gi"
                                            ), "<strong>$1</strong>" ),
                                        value: label,
                                        option: this
                                    };
                                }
                            }) );
                        },
                        select: function( event, ui ) {
                            ui.item.option.selected = true;
                            that._trigger( "selected", event, {
                                item: ui.item.option
                            });
                            // 如果选择的项没有变化，不触发change事件
                            if (selectedItem !== ui.item.option) {
                                select.trigger("change");
                            }
                            selectedItem = ui.item.option;
                        },
                        change: function( event, ui ) {
                            if ( !ui.item ) {
                                removeIfInvalid( this );
                                select.trigger('change');
                            }
                        }
                    })
                    .addClass( "ui-widget ui-widget-content ui-corner-left" );

                input.data( "ui-autocomplete" )._renderItem = function( ul, item ) {
                    return $( "<li>" )
                        .append( "<a>" + item.label + "</a>" )
                        .appendTo( ul );
                };

                $( "<a>" )
                    .attr( "tabIndex", -1 )
                    .appendTo( wrapper )
                    .button({
                        icons: {
                            primary: "ui-icon-triangle-1-s"
                        },
                        text: false
                    })
                    .removeClass( "ui-corner-all" )
                    .addClass( "ui-corner-right ui-combobox-toggle" )
                    .mousedown(function() {
                        wasOpen = input.autocomplete( "widget" ).is( ":visible" );
                    })
                    .click(function() {
                        input.focus();

                        // close if already visible
                        if ( wasOpen ) {
                            return;
                        }

                        // pass empty string as value to search for, displaying all results
                        input.autocomplete( "search", "" );
                    });
            },

            _destroy: function() {
                this.wrapper.remove();
                this.element.show();
            }
        });
    })( jQuery );
};});