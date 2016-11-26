var myChart = echarts.init(document.getElementById('echarts-word-cloud'));
$.get("cloudWord?limit=150",function (words) {
    var wordoption = {
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
    myChart.setOption(wordoption);
}, "json" );