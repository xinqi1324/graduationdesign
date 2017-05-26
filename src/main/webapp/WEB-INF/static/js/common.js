/**
 * 日期格式化
 * @param time
 * @returns {*}
 */
function formatDate(time) {
    if (time == '' || time == null || time == undefined) return '';
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "年" + month + "月" + date + "日 " + hour + ":" + minute + ":" + second;
}


/**
 * 简单删除方法
 * @param id 删除实体ID
 */
function delEntity(id) {
    //询问框
    layui.use('layer', function () {
        var layer = layui.layer;
        layer.confirm('确定删除吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            $.ajax({
                type: "POST",
                url: delUrl + "?id=" + id,
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        $('#dataTable').bootstrapTable('refresh', {url: dataUrl});
                        layer.closeAll();
                    } else {
                        layer.alert(data.message, {icon: 2}, function (index) {
                            layer.closeAll();
                        });
                    }
                }
            });
        }, function () {
            return;
        });
    })
}

/**
 * 简单增加修改方法
 * @param id 当ID为空的时候，为增加实体；当ID不为空的时候，为修改实体
 */
function editEntity(id) {
    var title = "新增";
    var url = editUrl;
    if (id != null) {
        title = "编辑";

        var separator = "?";
        if (url.indexOf("?") > 0) {
            separator = "&";
        }
        url = url + separator + "id=" + id;
    }
    layui.use('layer', function () {
        var layer = layui.layer;
        layer.open({
            type: 2,
            title: title,
            shadeClose: false,
            shade: 0.8,
            area: area,
            fix: false,
            maxmin: true,
            content: url
        });
    })
}
/**
 * 通用查询方法
 */
function _search() {
    $('#dataTable').bootstrapTable('refresh', {url: dataUrl});
}
/**
 * 多选项规则
 * 必须在每个多选项的div前加入name为findDiv的 input标签
 */
function checkboxVerify() {
    var findDiv = $("input[name='findDiv']");
    var flag = true;
    findDiv.each(function (index, data) {
        var childrenDiv = $($(data).next().children());
        var checkedCount = 0;
        childrenDiv.each(function (childIndex, childData) {
            var isChecked = $(childData).is(":checked");
            if (isChecked) {
                checkedCount++;
            }
        })
        if (checkedCount < 1) {
            flag = false;
        }
    })
    if (!flag) {
        return false;
    } else {
        return true;
    }
}
function radioVerify() {
    var findDiv = $("input[name='singleDiv']");
    var inputs = findDiv.next().children("input");
}
//字符串合法判断
function legalJudgement(value) {
    var illgal=["\'","\"","\<","\>","\\","script",","];
    for(var i = 0 ; i < illgal.length ; i++){
        if(value.indexOf(illgal[i]) != -1){
            return false;
            break;
        }
    }
    return true;
}
