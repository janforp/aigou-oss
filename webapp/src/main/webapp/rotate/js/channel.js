//http://v1.mob.pages.zhuquzhou.com/js/utils.js required
//jquery required

function isWechat(){
    var ua = navigator.userAgent.toLowerCase();
    return ua.indexOf('micromessenger') != -1;
}
function isIphone(){
    var ua = navigator.userAgent.toLowerCase();

    var params = parseParams();
    var channel_id = params.channel_id;

    if(channel_id == '9ff9' || channel_id == '9ff8'){
	return true;
    }

    return ua.indexOf('iphone') != -1 || ua.indexOf('ipad') != -1;
}

function isPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
                "SymbianOS", "Windows Phone",
                "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}

function getChannelId(){
    var params = parseParams();
    var channel_id = params.channel_id;
    if(isIphone()){
        channel_id = channel_id || '2b24';
    } else{
        channel_id = channel_id || '2b04';
    }
    if (channel_id.indexOf('#') != -1) {
        channel_id = channel_id.split('#')[0];
    }
    return channel_id;
}

function channelDisplay(channel_id){
    //if (isPC() && window.location.host == 'dl.zhuquzhou.com' && channel_id == '58d5') {
     //   window.location.href = 'http://www.zhuquzhou.com/?channel_id=' + channel_id;
    //}
    //
    //var platform = 'android';
    //var v = $('#v').attr('data-value');
    //if( isIphone()){
     //   platform = 'ios';
    //}
    //var api = rp_host + '/page_display?platform=' + platform + '&channel_id=' + channel_id + '&ver=' + v;
    //var d = $('<iframe>').attr('src', api).css('display','none');
    //$('body').append(d);
    //
    //trackEvent( v, channel_id, '展示');
    ////trackEvent('展示' + v, channel_id);
    //
    //if (channel_id == '5927' || channel_id == '5928' || channel_id == '5929' || channel_id == '592a') {
     //   // add by linjf 2016-02-24 liulongxian
     //   var _html = "<script type='text/javascript'>var _py=_py||[];_py.push(['a','als..Qhp60aQ3WGk0tL5Spuk21X']);_py.push(['domain','stats.ipinyou.com']);_py.push(['e','']);-function(d){var s=d.createElement('script'),e=d.body.getElementsByTagName('script')[0];e.parentNode.insertBefore(s,e),f='https:'==location.protocol;s.src=(f?'https':'http')+'://'+(f?'fm.ipinyou.com':'fm.p0y.cn')+'/j/adv.js'}(document);function pyRegisterCvt(){var w=window,d=document,e=encodeURIComponent;var b=location.href,c=d.referrer,f,g=d.cookie,h=g.match(/(^|;)\s*ipycookie=([^;]*)/),i=g.match(/(^|;)\s*ipysession=([^;]*)/);if(w.parent!=w){f=b;b=c;c=f};u='//stats.ipinyou.com/cvt?a='+e('als.43s.rPLndIkO__xde9gcbXK53P')+'&c='+e(h?h[2]:'')+'&s='+e(i?i[2].match(/jump\%3D(\d+)/)[1]:'')+'&u='+e(b)+'&r='+e(c)+'&rd='+(new Date()).getTime()+'&e=';(new Image()).src=u}</script><noscript><img src='//stats.ipinyou.com/adv.gif?a=als..Qhp60aQ3WGk0tL5Spuk21X&e='style='display:none;'/></noscript>"
     //   $('body').append(_html);
    //}
    ///*if (channel_id == '59a8') {
     //   // add by linjf 2016-03-09 lucky
     //   var _html = '<script type="text/javascript" src="http://f2.adpush.cn/zc/w.js?rr=.cntv.cn/adplayer/adBlockDetector/adv_index"></script>';
     //   $('body').append(_html);
    //}*/
    //if (channel_id == '18sg' || channel_id == '9vgf' || channel_id == 's3ev' || channel_id == '59a9' || channel_id == '59a8') {
     //   // add by linjf 2016-04-25 lucky
     //   var _html = '<script type="text/javascript" id="monitorpoint_t_p_c_e" src="http://f2.adpush.cn/stat/r3.js?t=1&l=0&bl=60&delay=0&r=.cntv.cn/adplayer/adBlockDetector/adv_index"></script>';
     //   _html += '<script type="text/javascript" src="http://f2.adpush.cn/zc/w.js?rr=.cntv.cn/adplayer/adBlockDetector/adv_index"></script>';
     //   _html += '<script type="text/javascript" id="monitorpoint_t_p_c_e_d" src="http://f2.adpush.cn/stat/m3.js?t=2&l=2&count=1&r=.cntv.cn/adplayer/adBlockDetector/adv_index"></script>';
     //   $('body').append(_html);
    //}
    //
    //
	//if (channel_id == '64vo' || channel_id == 'erp1'){
	//	//$('body').attr('tt-data-show','show');
    //
	//	var _html = '<script type="text/javascript">';
	//	_html +="(function() {_tt_config = true;var ta = document.createElement('script'); ta.type = 'text/javascript'; ta.async = true;";
	//	_html +="ta.src = document.location.protocol + '//' + 's0.pstatp.com/adstatic/resource/landing_log/dist/1.1.15/static/js/toutiao-tetris-analytics.js';";
	//	_html +="var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ta, s);})();";
	//	_html +='</script>';
    //
     //   $('head').append(_html);
	//}

}

var rp_host = get_rp_host();

var channel_id = getChannelId();
channelDisplay(channel_id);

function get_rp_host() {
    var host = window.location.host;
    if (host.indexOf('dingronghua.com') != -1) {
        return 'http://rp.zhuquzhou.com';
    }
    var port_array = host.split(':');
    if (port_array.length > 0) {
        host = port_array[0];
    }
    var arrs = host.split('.');
    var length = arrs.length;
    return 'http://rp.' + arrs[length - 2] + '.' + arrs[length - 1];
}
//点击‘立即领取’按钮，执行下载操作（分IOS，Android，PC3个系统，链接不同）
function download(mark){
    // donwload_tip
    if (typeof($('#download_tip').data('download_tip_exist')) == 'undefined') {
        var download_tip_html = '<div data-download_tip_exist=1 style="position:fixed; z-index:999999; bottom:80px; width:100%; text-align:center; display:none;" id="download_tip"><p style="color:#fff; background-color:rgba(0, 0, 0, 0.8); font-size:12px; display:inline-block; padding:5px 10px; border-radius:3px;">正在加载中，请稍候...</p></div>';
        $('body').append(download_tip_html);
    }
    $('#download_tip').show();
    setTimeout(function() {$('#download_tip').hide();}, 5000);

    if(isIphone()) {
        //把下载链接改成想要的即可
        window.location.href= "https://itunes.apple.com/cn/app/yi-yuan-ai-gou-tian-tian-quan/id1107985306?mt=8";
    }else {
        window.location.href= "http://ag-aw-p.oss-cn-hangzhou.aliyuncs.com/com.iask.yiyuanlegou1_412_jiagu_sign.apk";
    }


//     //获取取到ID（分IOS，Android，PC3个系统，链接不同）＊＊＊＊＊
//     var channel_id = getChannelId();
//     //trackEvent('渠道','点击' + mark, channel_id);
//     //<div id="v" data-value="v20IC" style="display:none"></div> <body>的第一个div
//     var v = $('#v').attr('data-value');
//     trackEvent( v, channel_id, '点击');
//     //trackEvent('点击' + v, channel_id);
//
//     var target = rp_host + '/52duobao_android/rd/'+channel_id ;
//     if(isIphone()){
//         target = rp_host + '/52duobao_ios/rd/'+channel_id;
//         if(isWechat()){
//             target = target + '?osfrom=weixin';
//         }
//     } else {
//         target = rp_host + '/52duobao_android/rd/'+channel_id ;
//         if(isWechat()){
//             target = target + '?osfrom=weixin';
// //            target = 'http://a.app.qq.com/o/simple.jsp?pkgname=fw.cn.quanmin' ;
//         }
//     }
//     if(target.indexOf('?') == -1){
//         target = target + '?';
//     }else{
//         target = target + '&';
//     }
//     target = target + 'ver=' + v;
//     //window.setTimeout(function(){ window.location.href = target;}, 200 );
//
// 	if(window['youyuan']){
// 		youyuan.download('',target,'install');
// 	}
//     window.location.href = target;
//
//     if (channel_id == '5927' || channel_id == '5928' || channel_id == '5929' || channel_id == '592a') {
//         pyRegisterCvt();
//     }
    //if (channel_id == '5793') {
    //    setTimeout(function() {
    //        var timeout_url = 'http://a.app.qq.com/o/simple.jsp?pkgname=fw.cn.quanmin';
    //        if (isIphone() && !isWechat()) {
    //            timeout_url = 'https://itunes.apple.com/cn/app/quan-min-duo-bao-yi-yuan-gou/id977933863?mt=8';
    //        }
    //        window.location.href = timeout_url;
    //    }, 10 * 1000);
    //}
}

// attach channel id
$('#download').on('click', function(){
    download('按钮');
});

//$(window).on('scroll', function(){
//    var v = $('#v').attr('data-value');
//    trackEvent( v, channel_id, '滑动');
//    //trackEvent('滑动'+v, channel_id );
//});

//$(window).on('load',function(){
//    var v = $('#v').attr('data-value');
//    download('自动下载');
//});
