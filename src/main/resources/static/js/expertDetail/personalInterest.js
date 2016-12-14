/**
 * Created by hexu on 2016/12/7.
 */

function loadInterestPersonal() {
    var $loadWindow = document.getElementById('hot-research-point');
    var myChart = echarts.init($loadWindow);
    $.ajax({
        url : "/detailOfExpert/InterestOfExpert",
        type : "get",
        dataType : "json",
        data : {
            "name" : "詹毅",
            "institution" : "电子科技集团36所"
        },
        success : function (finalData) {
            var option = {
                tooltip: {},
                series: [ {
                    type: 'wordCloud',
                    gridSize: 2,
                    sizeRange: [12, 50],
                    rotationRange: [0, 0],
                    shape: 'circle',
                    width: $loadWindow.clientWidth,
                    height: $loadWindow.clientHeight,
                    textStyle: {
                        normal: {
                            color: function () {
                                return 'rgb(' + [
                                        Math.round(Math.random() * 160),
                                        Math.round(Math.random() * 160),
                                        Math.round(Math.random() * 160)
                                    ].join(',') + ')';
                            }
                        },
                        emphasis: {
                            shadowBlur: 10,
                            shadowColor: '#333'
                        }
                    },
                    data:finalData.data
                }
                ]
            };
            myChart.setOption(option);
        }
    })

}

loadInterestPersonal();