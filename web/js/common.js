/**
 * Created by song on 17-3-21.
 *
 * 公用初始化
 */

$(document).ready(function () {
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 3, // Creates a dropdown of 15 years to control year,
        format: 'yyyy-mm-dd',   //日期显示格式
        today: '今天',
        clear: '清空',
        close: '关闭',
        min: new Date(),
        closeOnSelect: true
    });

    $('.carousel').carousel();

    $('.modal').modal();
});

/**
 * 获取url中参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]);

    return null; //返回参数值
}
