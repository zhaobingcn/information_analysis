/**
 * Created by zhzy on 2016/11/26.
 */
var myChart = echarts.init(document.getElementById('mainas'));
myChart.showLoading();
$.get('graph?limit=30', function (webkitDep) {
    myChart.hideLoading();
    option = {
        legend: {
            data: ['机构', '作者', '论文']
        },
        series: [{
            symbolSize: 15,
            type: 'graph',
            layout: 'force',
            animation: false,
            label: {
                normal: {
                    position: 'right',
                    formatter: '{b}'
                }
            },
            draggable: true,
            data: webkitDep.nodes.map(function (node, idx) {
                node.id = idx;
                return node;
            }),
            categories: webkitDep.categories,
            force: {
//                     initLayout: 'circular',
//                     gravity: 0.4,
//                     repulsion: 20,
                edgeLength: 150,
                repulsion: 30
            },
            edges: webkitDep.links
        }]
    };

    myChart.setOption(option);
});
