var colors = ['#ff6867', '#4ebd4d', '#ffa93c', '#3277de', '#329dd8', '#a489d6', '#4dd0c8', '#f680b1', '#59c895', '#f9a78e'];
var pageSize = 10;
// 判断一个值是否为空字符串
function isEmpty(val) {
    var flag = false;
    val = val.replace(/^\s+|\s+$/g, '');
    if (val == "") {
        flag = true;
    }
    return flag;
}
// 判判是否为空,传入input的id
function checkNull(inId) {
    var flag = false;
    var $inId = $("#" + inId);
    if ($inId.length <= 0) {
        flag = true;
    }
    var val = $inId.val();
    val = val.replace(/^\s+|\s+$/g, '');
    if (val == "") {
        flag = true;
    }
    return flag;
}
var beginDay = {
    dateFmt: 'yyyy-MM-dd',
    maxDate: '#F{$dp.$D(\'endTime\')}',
    isShowClear:false,
    readOnly:true,
    isShowToday:false
};
var endDay = {
    dateFmt: 'yyyy-MM-dd',
    minDate: '#F{$dp.$D(\'beginTime\')}',
    maxDate:'%y-%M-{%d-1}',
    isShowClear:false,
    readOnly:true,
    isShowToday:false
};
var beginMonth = {
    dateFmt: 'yyyy-MM',
    maxDate: '#F{$dp.$D(\'endTime\')}',
    isShowClear:false,
    readOnly:true
};
var endMonth = {
    dateFmt: 'yyyy-MM',
    minDate: '#F{$dp.$D(\'beginTime\')}',
    maxDate: '%y-%M',
    isShowClear:false,
    readOnly:true
};
var beginYear = {
    dateFmt: 'yyyy',
    maxDate: '#F{$dp.$D(\'endTime\')}',
    isShowClear:false,
    readOnly:true
};
var endYear = {
    dateFmt: 'yyyy',
    minDate: '#F{$dp.$D(\'beginTime\')}',
    maxDate: '%y',
    isShowClear:false,
    readOnly:true
};
//var defualtBeginTime = getBeforeFor6Day();
//var defualtEndTime = getCurrDate();

var defualtBeginTime = getFromNowDate(new Date(),-7);
var defualtEndTime = getFromNowDate(new Date(),-1);
//按年按月按天查询组装
dateType = {
    writeDateTypeContent: function (type, id) {
        var htmlContent = "";
        if (type == "day") {
            //defualtBeginTime = getBeforeFor6Day();
           // defualtEndTime = getCurrDate();

            var currDate = new Date();
            defualtBeginTime = getFromNowDate(currDate,-7);
            defualtEndTime = getFromNowDate(currDate,-1);

            htmlContent = '<input type="text" class="g-input f-right w-116 g-mr-6 Wdate date2" onclick="WdatePicker(endDay)" value="' + defualtEndTime + '" readonly="" id="endTime" name="endTime"/>';
            htmlContent += '<span class="g-middleTxt f-right">到</span>';
            htmlContent += '<input type="text" class="g-input w-116 f-right Wdate date1" onclick="WdatePicker(beginDay)" value="' + defualtBeginTime + '" readonly="" id="beginTime" name="beginTime"/>';
        } else if (type == "month") {
            defualtBeginTime = getBeforeFor6Month(6);
            defualtEndTime = getCurrMonth();
            htmlContent = '<input type="text" class="g-input f-right w-116 Wdate g-mr-6 date2" onclick="WdatePicker(endMonth)" value="' + defualtEndTime + '" readonly="" id="endTime" name="endTime"/>';
            htmlContent += '<span class="g-middleTxt f-right">到</span>';
            htmlContent += '<input type="text" class="g-input w-116 f-right Wdate date1" onclick="WdatePicker(beginMonth)" value="' + defualtBeginTime + '" readonly="" id="beginTime" name="beginTime"/>';
        } else if (type == "year") {
            defualtBeginTime = getCurrYear() - 6;
            defualtEndTime = getCurrYear();
            htmlContent = '<input type="text" class="g-input w-116 Wdate f-right g-mr-6 date2" onclick="WdatePicker(endYear)" value="' + defualtEndTime + '" readonly="" id="endTime" name="endTime"/>';
            htmlContent += '<span class="g-middleTxt f-right">到</span>';//
            htmlContent += '<input type="text" class="g-input f-right w-116 Wdate date1"onclick="WdatePicker(beginYear)" value="' + defualtBeginTime + '" readonly="" id="beginTime" name="beginTime"/>';
        }
        $("#" + id).html(htmlContent);
    }
};
//获取两个日期的天数
function getDays(beginDateStr, endDateStr) {
    var endDate = new Date(endDateStr);
    var beginDate = new Date(beginDateStr);
    var lag = endDate.getTime() - beginDate.getTime();
    return parseInt(lag / 3600000 / 24);
}

//获取两个日期的月份
function getMonths(beginDateStr, endDateStr) {
    var endDate = new Date(endDateStr);
    var beginDate = new Date(beginDateStr);
    var lag = endDate.getTime() - beginDate.getTime();
    return parseInt(lag / 3600000 / 24 / 12);
}

//获取距离date，num天的日期
//num正数就是距离date未来的num天，为负数就是 距离date的过去num天
//date 和 num都是必须传的参数
function getFromNowDate(date,num){
    if(date == null){
        date = new Date();
    }

    var targetDaytime = date.getTime()+num*24*60*60*1000;
    var targetDate = new Date(targetDaytime);

    var year = targetDate.getFullYear();
    var month = targetDate.getMonth()+1;
    var day = targetDate.getDate();

    return year+"-"+("00"+month).substr((""+month).length)+"-"+("00"+day).substr((""+day).length);
}

//获取6个月以前的月份
function getBeforeFor6Month(n) {
    var today = new Date().pattern('yyyy-MM');
    var d = new Date(today.replace(/[^\d]/g, "/") + "/1");
    var result = [today];
    for (var i = 0; i < (n-1); i++) {
        d.setMonth(d.getMonth() - 1);
        var m = d.getMonth() + 1;
        m = m < 10 ? "0" + m : m;
        result.push(d.getFullYear() + "-" + m);
    }
    return result[result.length - 1];
}
//获取当前月份
function getCurrMonth() {
    var today = new Date();
    var strYear = today.getFullYear();
    var strMonth = today.getMonth() + 1;
    if (strMonth < 10) {
        strMonth = "0" + strMonth;
    }
    return strYear + "-" + strMonth;
}
//获取当前月份
function getCurrYear() {
    var today = new Date();
    return today.getFullYear();
}

//对列表中的list数据排序事件进行绑定
listSort = {
    bundlingEvent: function (callback, path) {
        $('th.e-toggle').on('click', function () {
            var sortico = $(this).children('img');
            var url = path || '/images/common/';
            if (sortico.attr('src') == url + 'toggle-ico.png') {
                sortico.attr('src', url + 'toggle-ico2.png');
            } else if (sortico.attr('src') == url + 'toggle-ico2.png') {
                sortico.attr('src', url + 'toggle-ico1.png');
            } else if (sortico.attr('src') == url + 'toggle-ico1.png') {
                sortico.attr('src', url + 'toggle-ico2.png');
            }
            callback($(this));
        });
    }
};


Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    var week = {
        "0": "\u65e5",
        "1": "\u4e00",
        "2": "\u4e8c",
        "3": "\u4e09",
        "4": "\u56db",
        "5": "\u4e94",
        "6": "\u516d"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f" : "\u5468") : "") + week[this.getDay() + ""]);
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};


/**
 * 时间类型转换 将Date 转化为指定格式的String
 * @param fmt
 * @returns {*}
 */
Date.prototype.pattern = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    var week = {
        "0": "\u65e5",
        "1": "\u4e00",
        "2": "\u4e8c",
        "3": "\u4e09",
        "4": "\u56db",
        "5": "\u4e94",
        "6": "\u516d"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f" : "\u5468") : "") + week[this.getDay() + ""]);
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};
