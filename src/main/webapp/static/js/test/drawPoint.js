$(document).ready(function () {
    let app = new Vue({
        el: '#app',
        data: {
            clickPoint: {
                x: 0,
                y: 0
            },
            html: ''
        },
        methods: {
            getClickPoint: function (event) {
                app.clickPoint.x = event.offsetX;
                app.clickPoint.y = event.offsetY;
                this.drawPoint(app.clickPoint.x, app.clickPoint.y);
            },
            drawPoint: function (x, y) {
                let shouPointDiv = '';
                shouPointDiv += '<div id="newPoint"  class="circle" style="width: 15px; height: 15px; border-radius: 2px; left: ' + x + 'px; top: ' + y + 'px;" title="新增站点">';
                shouPointDiv += '</div>';
                $("#newPoint").remove();
                $("#app").append(shouPointDiv);
            }
        },
        mounted: function () {
            $("#app").css("background-image", "url("+Lee.realPath+"/static/img/timg.jpeg)");
            // 定义从后台传过来的坐标
            let showPointArr = [
                {x: 100, y: 100},
                {x: 200, y: 200}
            ];
            let shouPointDiv = '';
            for (let i = 0; i < showPointArr.length; i++) {
                let showPoint = showPointArr[i];
                shouPointDiv += '<div  class="circle" style="width: 15px; height: 15px; border-radius: 2px; left: ' + showPoint.x + 'px; top: ' + showPoint.y + 'px;" title="已有站点">';
                shouPointDiv += '</div>';
            }
            this.html = shouPointDiv;
        }
    })
});