
Pull_Event = function(o){
    this.flag = false;
    this.id = Math.random().toString().slice(2);
    o = $.extend({}, o);
    this.parameter = o;
    this.init();
};


Pull_Event.prototype.init = function(){
    var o = this.parameter;
    var _this = this;
    if (!o.scrollElm || !o.contentElm) {//默认对window进行下拉监控，可指定其他元素
        var scrollElm = $(window),
            contentElm = $(document);
    } else {//可以指定其他的div的下拉置定事件
        var scrollElm = $(o.scrollElm),
            contentElm = $(o.contentElm);
    }

    scrollElm.on('scroll.'+_this.id,function() {
        if (_this.flag === true) {//但已触发，未结束，不再触发
            return;
        }

        //判断是否置底，默认值
        var condition = (scrollElm.scrollTop() >= contentElm.height() - scrollElm.height() - 5) && contentElm.height() - scrollElm.height() >= (_this.offsetY !== undefined?_this.offsetY:20);

        if(!condition){//未置底
            return;
        }

        _this.flag = true;
        _this.loading = $('<div><p></p></div>');
        _this.loading.addClass('loading_tit');
        var t = '加载中',
            n = 1;
        //传参可置顶“加载中”出现的位置
        if (!o.prompt_selector) {
            o.prompt_selector = 'body';//默认body
        }

        var prompt_selector = $(o.prompt_selector);


        switch (o.prompt_method) {
            case 'after':
                prompt_selector.after(_this.loading);
                break;
            case 'before':
                prompt_selector.before(_this.loading);
                break;
            case 'prepend':
                prompt_selector.prepend(_this.loading);
                break;
            case 'append':
                prompt_selector.append(_this.loading);
                break;
            default ://默认append
                prompt_selector.append(_this.loading);
        }

        scrollElm.scrollTop(contentElm.outerHeight() - scrollElm.height());


        (function() {
            clearTimeout(_this.timeout);
            _this.loading.find('p').text(t + '...'.slice(0, n));
            n = n === 3 ? 0 : n + 1;
            _this.timeout = setTimeout(arguments.callee, 200);
        }());


        o.handle && o.handle.call(_this);

    });
    return this;
};


Pull_Event.prototype.done = function(){//监控完成一次
    clearTimeout(this.timeout);
    if (this.loading !== undefined) {
        this.loading.remove();
    }
    this.flag = false;
};


Pull_Event.prototype.destroy = function(){//解除监控
    this.done();
    var o = this.parameter;
    if (!o.scrollElm || !o.contentElm) {//默认对window进行下拉监控，可指定其他元素
        var scrollElm = $(window);
    } else {//可以指定其他的div的下拉置定事件
        var scrollElm = $(o.scrollElm);
    }

    scrollElm.off('scroll.'+this.id);

};

