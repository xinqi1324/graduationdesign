/**
 * jquery.sort.js
 * 商品发布-选择分类
 * author: 锐不可挡
 * date: 2016-07-07
 **/
/*定义三级分类数据*/
//一级分类
var province = [];
//分类集合
var productCategory;
//当前选中的id
var selectProductCategoryId;
//当前选中的id
var selectProductCategoryName;
$.ajax({
    url: ajaxUrl,
    type: "post",
    async: false,
    success: function (data) {
        if (data) {
            //一级分类
            productCategory = JSON.parse(data)
            $(productCategory).each(function (index, data) {
                if (data.pId == "0") {
                    province.push(data);
                }
            });
        }
    },
    error: function () {

    }
});


var expressP, expressC, expressD, areaCont;

/*初始化一级目录*/
function intProvince() {
    areaCont = "";
    for (var i = 0; i < province.length; i++) {
        areaCont += '<li onClick="selectP(' + province[i].id + ',this);"><a href="javascript:void(0)">' + province[i].name + '</a></li>';

    }

    $("#sort1").html(areaCont);
}
intProvince();

/*选择一级目录*/
function selectP(id, li) {
    areaCont = "";
    var name = "";
    for (var i = 0; i < productCategory.length; i++) {
        if (id == productCategory[i].id) {
            name += productCategory[i].name;
        }
        if (id == productCategory[i].pId) {
            areaCont += '<li onClick="selectC(' + productCategory[i].id + ',this);"><a href="javascript:void(0)">' + productCategory[i].name + '</a></li>';
        }
    }
    $("#sort2").html(areaCont).show();
    $("#sort3").hide();
    $(li).addClass("active").siblings("li").removeClass("active");
    expressP = name;
    $("#selectedSort").html(expressP);
    $("#releaseBtn").attr("disabled", true);
}

/*选择二级目录*/
function selectC(id, li) {
    areaCont = "";
    expressC = "";
    var name = "";

    for (var i = 0; i < productCategory.length; i++) {
        if (id == productCategory[i].id) {
            name += productCategory[i].name;
        }
        if (productCategory[i].pId == id) {
            areaCont += '<li onClick="selectD(' + '\'' + productCategory[i].id + '\'' + ',this);"><a href="javascript:void(0)">' + productCategory[i].name + '</a></li>';
        }
    }
    $("#sort3").html(areaCont).show();
    $(li).addClass("active").siblings("li").removeClass("active");

    expressC = expressP + ">" + name;
    $("#selectedSort").html(expressC);
    $("#releaseBtn").attr("disabled", true);

}

/*选择三级目录*/
function selectD(id, li) {
    $(li).addClass("active").siblings("li").removeClass("active");
    var name = "";
    for (var i = 0; i < productCategory.length; i++) {
        if (id == productCategory[i].id) {
            name = productCategory[i].name;
        }
    }
    expressD = expressC + ">" + name;
    selectProductCategoryId = id;
    selectProductCategoryName = name

    $("#selectedSort").html(expressD);
    $("#releaseBtn").removeAttr("disabled");
}
