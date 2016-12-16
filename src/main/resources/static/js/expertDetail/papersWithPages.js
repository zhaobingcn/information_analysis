/**
 * Created by hexu on 2016/12/7.
 */
var thePageSize = 8;
var theCurrentPage = 1;
var allPagesCount = Number('<%=request.getParameter("allPagesCount")%>');
document.getElementById("page-button-one").onclick = function () {
    if(theCurrentPage > 1){
        loadPreviousPage(thePageSize, theCurrentPage);
    }
    if(theCurrentPage == 1){
        document.getElementById("page-button-one").value = "首页";
    }
}

document.getElementById("page-button-two").onclick = function () {
    if(theCurrentPage < allPagesCount){
        loadNextPage(thePageSize, theCurrentPage);
    }
    if(theCurrentPage == allPagesCount){
        document.getElementById("page-button-one").value = "尾页";
    }
}

function loadPreviousPage(pageSize, currentPage) {
    var skip = (currentPage-2) * pageSize;
    var limit = pageSize;
    theCurrentPage = theCurrentPage -1;
    $.ajax({
       url: "",
       type: "get",
       dataType: "json",
       data: {
           "skip": skip,
           "limit": limit
       },
       success: function (papers) {

       }
    });
}

function loadNextPage(pageSize, currentPage) {
    var skip = currentPage * pageSize;
    var limit = pageSize;
    theCurrentPage = theCurrentPage -1;
    $.ajax({
        url: "",
        type: "get",
        dataType: "json",
        data: {
            "skip": skip,
            "limit": limit
        },
        success: function (papers) {

        }
    });
}