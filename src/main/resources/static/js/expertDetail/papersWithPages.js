/**
 * Created by hexu on 2016/12/7.
 */
var thePageSize = 8;
var theCurrentPage = 1;
// var allPagesCount = Number('<%=request.getParameter("allPagesCount")%>');
var allPagesCount = 4;
document.getElementById("page-button-one").onclick = function () {
    if(theCurrentPage > 1){
        loadPreviousPage(thePageSize, theCurrentPage);
    }
    setButtonValue();
}

document.getElementById("page-button-two").onclick = function () {
    if(theCurrentPage < allPagesCount){
        loadNextPage(thePageSize, theCurrentPage);
    }
    setButtonValue();
}

function setButtonValue() {
    if(theCurrentPage > 1){
        document.getElementById("page-button-one").innerHTML = "上一页";
    } else{
        document.getElementById("page-button-one").innerHTML = "首页";
    }
    if(theCurrentPage < allPagesCount){
        document.getElementById("page-button-two").innerHTML = "下一页";
    }else{
        document.getElementById("page-button-two").innerHTML = "尾页";
    }
}

function loadPreviousPage(pageSize, currentPage) {
    var skip = (currentPage-2) * pageSize;
    var limit = pageSize;
    theCurrentPage = theCurrentPage -1;
    $.ajax({
       url: "/detailOfExpert/papersWithPages",
       type: "get",
       dataType: "json",
       data: {
           "name" : "袁俊",
           "institution" : "中国航空工业第1集团公司",
           "skip": skip,
           "limit": limit
       },
       success: function (papers) {
            var papersData = papers.data;
            var len = papersData.length;
            var $papersList = $("#paper-list-div").empty();
            for(var i=0; i<len; i++){
                $papersList.append(
                "<a href= \"#\" class=\"list-group-item\">" +
                    "<i class=\"fa fa-star fa-fw\"></i>" + papersData[i].title +
                    "<span class=\"pull-right text-muted small\">" + "引用量：" + "<em>" + papersData[i].quote + "次" + "</em>" +
                "</span>" +
                "</a>"
                );
            }
       }
    });
}

function loadNextPage(pageSize, currentPage) {
    var skip = currentPage * pageSize;
    var limit = pageSize;
    theCurrentPage = theCurrentPage +1;
    $.ajax({
        url: "/detailOfExpert/papersWithPages",
        type: "get",
        dataType: "json",
        data: {
            "name" : "袁俊",
            "institution" : "中国航空工业第1集团公司",
            "skip": skip,
            "limit": limit
        },
        success: function (papers) {
            var papersData = papers.data;
            var len = papersData.length;
            var $papersList = $("#paper-list-div").empty();
            for(var i=0; i<len; i++){
                $papersList.append(
                    "<a href=\"" + papersData[i].link + "\" class=\"list-group-item\">" +
                    "<i class=\"fa fa-star fa-fw\"></i>" + papersData[i].title +
                    "<span class=\"pull-right text-muted small\">" + "引用量：" + "<em>" + papersData[i].quote + "次</em>" +
                    "</span>" +
                    "</a>"
                );
            }
        }
    });
}