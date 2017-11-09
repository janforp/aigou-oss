$(function() {
    $(".login, .popup2_yes").click(function() {
        download()
    });
    $(".close").click(function() {
        $(".meng2").hide();
        $(".meng3").show()
    });
    $(".close2, .popup2_no").click(function() {
        $(".meng3").hide()
    });
    $(".rule_down").click(function() {
        $(".meng_rule").show();
    });
    $(".rule_up").click(function() {
        $(".meng_rule").hide()
    });
    function stop(txt) {
        $(".pointer").bind("click",
        function() {
            get_number_2();
            $(".meng2").show();
            $(".btn_pop_in").text("想要！");
            $(".meng1_top").text("想要吗？");
            $("#awards2").text(txt);
            $(".awards_name1").hide();
            $(".awards_name2").show()
        })
    }
    function stop2(txt) {
        var many = rnd(40, 45);
        $(".meng2").show();
        $(".btn_pop_in").text("心动！");
        $(".meng1_top").text("心动吗？");
        $(".popup_img img").attr("src", "/rotate/img/1.jpg");
        $(".awards_name").html("已经有" + many + '人获得“<span class="fcred" id="awards">宝马320I 时尚型 </span>”');
        $(".close2, .close2_in").bind("click",
        function() {
            $(".meng2").hide();
            $(".btn_pop_in").text("想要！");
            $(".meng1_top").text("想要吗？")
        })
    }
    $(function() {
        var rotateTimeOut = function() {
            $("#rotate").rotate({
                angle: 0,
                animateTo: 2160,
                duration: 8000,
                callback: function() {
                    alert("网络超时，请检查您的网络设置！")
                }
            })
        };
        var bRotate = false;
        var rotateFn = function(awards, angles, txt, item) {
            bRotate = !bRotate;
            $("#rotate").stopRotate();
            $("#rotate").rotate({
                angle: 0,
                animateTo: angles + 3600,
                duration: 5000,
                callback: function() {
                    $(".popup_img img").attr("src", "/rotate/img/" + awards + ".jpg")
                    $('.meng1_top').html('中奖啦！')
                    $(".awards_name").html('恭喜您获得“<span class="fcred" id="awards">1元买' + txt + "</span>”的机会");
                    $(".btn_pop").attr("src", "/rotate/img/btn_pop2.jpg");
                    $('.btn_pop_in').html('立即领取')
                    $(".awards_name").css({
                        "text-align": "center"
                    });
                    $(".meng2").addClass("show");
                    $(".meng2").show();
                    bRotate = !bRotate;
                    $(".pointer").unbind("click");
                    stop(txt);
                    $(".close2, .close2_in").click(function() {
                        $(".meng2").hide();
                        $(".meng2").removeClass("show");
                        stop2(txt)
                    })
                }
            })
        };
        function rotate() {
            if (bRotate) {
                return
            }
            var item = rnd(1, 4);
            numb1 = rnd(310, 350);
            numb2 = rnd(250, 290);
            numb3 = rnd(190, 230);
            numb4 = rnd(130, 170);
            numb5 = rnd(70, 110);
            numb6 = rnd(10, 50);
            switch (item) {
            case 0:
                rotateFn(0, numb1, "iWatch");
                break;
            case 1:
                rotateFn(1, numb2, "宝马320I");
                break;
            case 2:
                rotateFn(2, numb3, "iPad Pro");
                break;
            case 3:
                rotateFn(3, numb6, "iphone6s");
                break;
            case 4:
                rotateFn(4, numb5, "路虎2016款");
                break;
            case 5:
                rotateFn(5, numb4, "50元话费");
                break
            }
        }
        $(".btn").click(rotate)
    });
    function rnd(n, m) {
        return Math.floor(Math.random() * (m - n + 1) + n)
    }
    $(document).ready(function() {
        var awards = ["iWatch", "iPad Pro", "iPhone6s", "路虎2016款", "宝马320I"];
        //$.getJSON("data.json",
        //function(data) {
        //    var name_arry = data.name;
        //    $.each(name_arry,
        //    function(index, name) {
        //        $(".news").find("ul:first").append("<li>" + name + '&nbsp;抽中&nbsp;<span class="prize">' + awards[Math.floor(Math.random() * 5)] + "</span>的机会</li>")
        //    })
        //})
    });
    var timer;
    function run() {
        $("#tp1").find("li:first").animate({
            marginLeft: "-16rem"
        },
        2000,
        function() {
            $(this).css("marginLeft", "0").appendTo($("#tp1"))
        })
    }
    timer = setInterval(run, 1000);
    $("#tp1").hover(function() {
        clearInterval(timer)
    },
    function() {
        timer = setInterval(run, 1000)
    });
    //$.getJSON("show.json",
    //function(res) {
    //    datetimes = ["18秒前", "42秒前", "1分钟前", "1分钟前", "5分钟前", "10分钟前", "30分钟前", "1小时前", "1小时前", "1小时前"];
    //    datetime_index = 0;
    //    res.data.forEach(function(item) {
    //        tpl_obj = $("#latest_container_tpl").clone();
    //        tpl_obj.attr("id", item.prize_id);
    //        tpl_obj.find(".user_info .avatar").attr("src", item.avatar);
    //        tpl_obj.find(".user_info .nickname").text(item.nickname);
    //        tpl_obj.find(".user_info .prize_name").text(item.prize_name);
    //        tpl_obj.find(".video_image img").attr("src", item.video_image);
    //        tpl_obj.find(".video_image img").click(function() {
    //            window.location.href = item.video_href
    //        });
    //        $("#latest_container_tpl").before(tpl_obj);
    //        tpl_obj.show();
    //        tpl_obj.parent().append(tpl_obj);
    //        tpl_obj.show();
    //        datetime_index += 1
    //    });
    //    start_animate()
    //});
    function start_animate() {
        $("#latest_container_tpl").remove();
        var _wrap = $(".share_container");
        var _interval = 3500;
        var _moving;
        _wrap.hover(function() {
            clearInterval(_moving)
        },
        function() {
            _moving = setInterval(function() {
                var _field = _wrap.find(".latest_container:first");
                var _h = _field.height();
                _field.animate({
                    marginTop: -_h + "px"
                },
                800,
                function() {
                    _field.css("marginTop", "10px").appendTo(_wrap)
                })
            },
            _interval)
        }).trigger("mouseleave")
    }
});
