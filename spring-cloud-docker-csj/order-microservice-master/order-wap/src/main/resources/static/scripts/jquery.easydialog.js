/*
 * @easyDialog - jQuery Plugin
 * @version 20140624
 * @author Chvin
 * @website tcwen.com
 */

easyDialog = function(option){
    var o = {
        HTML:"",//传递待弹框源字符
        target:"",//目标弹框元素，传入css规则字符串映射元素，目标需隐藏（display:none）
        closeBtn:"#easyDialogCloseBtn",//关闭按钮，(在target内)
        position:"fixed",//absolute
        zIndex:99999,//z-index
        bgOpacity:0.3,//底透明度
        bgColor:"#000",//背景底色
        speed:200,//渐变时间
        clickClose:true,//点击背景是否关闭
        ESC:true,//是否设置ESC按键关闭
        callBack:null,//回调函数
        dialogClose:dialogClose,//返回关闭给外部调用
        beforeClose:function(){return true;},
        timeout:0,//设置自动关闭时间，单位毫秒，0为不自动关闭
        timeoutCallBack:null//自动关闭后的回调
    };

    $.extend(o,option);//传入参数
    var ie6=!-[1,]&&!window.XMLHttpRequest,//判断是否为IE6浏览器
        h = '<div id="easyDialog" style="_position:absolute;top:0;left:0;width:100%;height:100%;display:none;z-index:'+o.zIndex+';position:'+o.position+';">';
    h+='<div id="easyDialog_black" style="width:100%;height:100%;position:absolute;top:0;left:0;background:'+o.bgColor+';"></div>';
    h+='<div id="easyDialog_load" style="position:absolute;top:50%;left:50%;"></div>';
    h+='</div>';

    $("body").append(h);//添加dialog包


    var easyDialog = $("#easyDialog"),//dialog包
        black = $("#easyDialog_black"),//black底
        loadBox = $("#easyDialog_load");//待载对话框

    black.css("opacity",o.bgOpacity);//设置透明度
    $("*").trigger("blur");//取消原有元素的焦点
    if(o.HTML)//如果设置了HTML源字符
        loadBox.html(o.HTML);//传入HTML
    else//传入目标弹框元素
        loadBox.html($(o.target).clone(true).css("display","block"));//载入目标对话框

    easyDialog.css({"opacity":0,"display":"block"});//初始化

    if(ie6||(o.position=="absolute")){
        black.css({"height":$(document).height()});//设置底高度
        if(ie6)
            easyDialog.height($(window).height())//兼容IE6
    }

    var loadBoxCSS = {"margin-left":-loadBox.width()*0.5},//对话框横坐标
        loadBoxCSSTop = ie6||(o.position=="absolute")?-loadBox.height()*0.5+$(window).scrollTop():-loadBox.height()*0.5;//对话框纵坐标
    $.extend(loadBoxCSS,{"margin-top":loadBoxCSSTop});//合并坐标对象
    loadBox.css(loadBoxCSS);//设置对话框坐标
    easyDialog.stop().animate({"opacity":1},o.speed,function(){ //载入页面
        if(o.clickClose)
            black.click(function(){dialogClose()});//关闭
        if(o.callBack)//回调函数
            o.callBack();
        if(o.timeout)//自动关闭
            setTimeout(function(){dialogClose(o.timeoutCallBack)},o.timeout*1000)
    });

    function dialogClose(callBack){//关闭方法
        if (!o.beforeClose()){return;}
        easyDialog.stop().animate({"opacity":0},o.speed,function(){
            $(this).remove();
            $("body").unbind(".easyDialog");});
        //按钮传入回调函数
        setTimeout(function(){
            if(typeof callBack === "string"){
                eval(callBack);
            }else if(typeof callBack === "function"){
                callBack();
            }

        },o.speed);
    }
    easyDialog.find(o.closeBtn).click(function(){
        dialogClose($(this).attr("data-close-callBack"));
    });


    if(o.ESC)
        $("body").bind({
            "keyup.easyDialog":function(event){
                if(event.keyCode==27)
                    dialogClose();
            }
        });
}