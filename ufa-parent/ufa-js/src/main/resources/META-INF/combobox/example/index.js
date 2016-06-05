;define(function(require){
    require('../../taurus/combobox/1.10.0/jquery.ui.combobox-debug.js')($);
    $( "#combobox" ).change(function(){
        seajs.log(this.value);
    }).combobox({
        inputClass : 'width250'
    });
    $( "#toggle" ).click(function() {
        $( "#combobox" ).toggle();
    });

});