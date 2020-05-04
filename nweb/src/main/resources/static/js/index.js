
$(function () {
    //奇数行点击事件
    $("li:odd").click(function () {
        confirm("奇数行变成红色吗");
        $("li:odd").css("background", "red");
    });

    //偶数行点击事件
    $("li:even").click(function () {
        confirm("偶数行变成灰色吗");
        $("li:even").css("background", "gray");
    });
});

