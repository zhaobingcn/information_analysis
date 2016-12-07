/**
 * Created by hexu on 2016/12/7.
 */

function loadAbilityPersonal() {
    var myChart = echarts.init(document.getElementById("person-ability"));
    var option = {
        title: {
            text: '专家能力雷达图'
        },
        tooltip: {},
        radar: {
            // shape: 'circle',
            indicator: [
                { name: '论文数量', max: 100},
                { name: '引用次数', max: 100},
                { name: '研究深度', max: 100},
                { name: '研究广度', max: 100},
                { name: '合作圈子', max: 100},
                { name: '研究影响力', max: 100}
            ]
        },
        series: [{
            name: '专家能力度量',
            type: 'radar',
            // areaStyle: {normal: {}},
            data : [
                {
                    value : [45, 13, 56, 44, 35, 22],
                    name : '专家能力'
                }
            ]
        }]
    };
    myChart.setOption(option);
}

loadAbilityPersonal();