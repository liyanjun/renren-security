    //html root的字体计算应该放在最前面，这样计算就不会有误差了/
    //2016.3.23 wjq update 之所以要加个判断返回一个20.5，是因为当用户在谷歌等浏览器直接输入手机端网站网址时，如果用户设置模块自定义样式的高度比较小，由于这时候的clientWidth为1920px，及返回的_htmlFontSize为40，这时候就会使模块太小，展示不完全，因此先取一个较为准确的值去展示。Mobi.resetHtmlFontSize()顺便也加了
    var _htmlFontSize = (function(){
        var clientWidth = document.documentElement ? document.documentElement.clientWidth : document.body.clientWidth;
        if(clientWidth > 640) clientWidth = 640;
        document.documentElement.style.fontSize = clientWidth * 1/6.4+"px";
        return clientWidth * 1/6.4;
    })();