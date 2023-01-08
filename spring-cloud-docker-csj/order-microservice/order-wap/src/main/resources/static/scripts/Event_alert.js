/**
 * 字符提示
 * @param {string} str 需要提示的字符串
 * @param {object} option 设定按钮，回调函数，当设定按钮时提示不再自动关闭
 */
var alertEC = function(str,option){
    var str = str.toString(),
        dialog = $('<div class="alert"></div>'),
        word = $('<p>'+str+'</p>'),
        option = $.extend({timeout:2000},option),
        dialog_bg,
        btn;

    if(option.yes || option.no){
        word.css({paddingBottom:0});
        btn = $('<div class="btn"></div>');
        if(option.yes){
            var yes = $('<a>'+option.yes+'</a>');
            btn.append(yes);
            yes.on('click',function(){
                hide(option.yes_handle);
            });
        }
        if(option.no){
            var no = $('<a>'+option.no+'</a>');
            btn.append(no);
            no.on('click',function(){
                hide(option.no_handle);
            });

        }
        if(option.yes && option.no){
            yes.css({marginRight:'1em'});
            no.css({marginLeft:'1em'});
        }

    }else{
        setTimeout(hide,option.timeout);
    }

    dialog.append(word);
    if(btn){
        dialog.append(btn);
        dialog_bg = $('<div class="alert_bg"></div>');
        $('body').append(dialog_bg);
    }


    $('body').append(dialog);
    dialog.css({webkitTransform:'scale(1)',top:'48%','left':'50%',marginTop:-dialog.outerHeight()/2,marginLeft:-dialog.outerWidth()/2});
    dialog.animate({opacity:1},100);


    function hide(callback){
        dialog.css({webkitTransform:'scale(.7)'});
        dialog.animate({opacity:0},100,function(){
            dialog.remove();
            if(dialog_bg){
                dialog_bg.remove();
            }
            if(typeof callback === 'function'){//btn使用
                callback();
            }
            if(typeof option.callback === 'function'){
                option.callback();
            }
        });
    }
};