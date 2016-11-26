function loadCloudWordsData() {
    var myChart = echarts.init(document.getElementById("echarts-word-cloud"));
    myChart.showLoading();
    $.ajax({
        url:"/pages/cloudWord",
        dataType:"json",
        type:"get",
        data:{"limit": 150},
        success: function (words) {
            myChart.hideLoading();
            var option = {
                tooltip: {},
                series: [ {
                    type: 'wordCloud',
                    gridSize: 2,
                    sizeRange: [12, 50],
                    rotationRange: [0, 0],
                    shape: 'circle',
                    width: 600,
                    height: 400,
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
                    data: words.data
                } ]
            };
            myChart.setOption(option);
        }
    });
};
loadCloudWordsData();