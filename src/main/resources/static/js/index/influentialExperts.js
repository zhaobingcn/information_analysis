/**
 * Created by zhzy on 2016/11/26.
 */
function loadExpertsData() {
var myChart = echarts.init(document.getElementById("10-hot-authors"))
var option = {
    title: {
        text: '权威专家',
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
        boundaryGap: [0, 0.01]
    },
    yAxis: {
        type: 'category',
        data: ['素还真','剑非道','谈无欲','玉清诀','红尘雪','练习生','墨倾池','叹稀奇', '无限']
    },
    series: [
        {
            name: '发表论文数量',
            type: 'bar',
            data: [18, 23, 29, 10, 13, 63,67,34,34 ]
        },

    ]
};
    myChart.setOption(option);
}
loadExpertsData();