(function ($, w) {
    if ($ === undefined) {
        alert("Jquery 未引入");
    }
    w.Lee = w.Lee || {};
    let __option = {
        cache: true,
        ctx: '',
        mainTabsId: 'his_main_tab'
    };
    Lee.__option = __option;
    // 缓存所有window对象
    Lee.__window = {};
    // 默认配置信息
    Lee.curWwwPath = window.document.location.href;         //获取主机地址之后的目录，如： myproj/view/my.jsp
    Lee.pathName = window.document.location.pathname;
    Lee.pos = Lee.curWwwPath.indexOf(Lee.pathName);      //获取主机地址，如： http://localhost:8083
    Lee.localhostPaht = Lee.curWwwPath.substring(0, Lee.pos);       //获取带"/"的项目名，如：/myproj
    Lee.projectName = Lee.pathName.substring(0, Lee.pathName.substr(1).indexOf('/') + 1);      //得到了 http://localhost:8083/myproj
    Lee.realPath = Lee.localhostPaht + Lee.projectName;
})(jQuery, window);