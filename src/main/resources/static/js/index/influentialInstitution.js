function loadInstitutionData(){
    var myChart = echarts.init(document.getElementById("10-hot-institutions"));
    var option = {
        title: {
            text: '热门科研机构',
            subtext: '数据截止2016年'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['2011年']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.0001]
        },
        yAxis: {
            type: 'category',
            data: ['中电科20所','中电科54所','中航科工','哈尔滨工业大学','清华大学','中电科创新院','中国兵器集团','北京大学', '中电科电科院']
        },
        series: [
            {
                name: '发表论文数量',
                type: 'bar',
                data: [18203, 23489, 29034, 104970, 131744, 630230,676767,34224,342345 ]
            },

        ]
    };

    myChart.setOption(option);
};
loadInstitutionData();