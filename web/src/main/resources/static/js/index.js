
$(function () {
    //奇数行点击事件
    $("li:odd").click(function () {
        confirm("确定变成红色吗");
        this.css("background: red");
    });

    //偶数行点击事件
    $("li:even").click(function () {
        confirm("确定变成红灰色吗");
        this.css("background: gray");
    });
});

