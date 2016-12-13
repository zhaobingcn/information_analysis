/**
 * Created by hexu on 2016/12/7.
 */

function loadRelationshipPersonal() {
    var myChart = echarts.init(document.getElementById('author-relationship'));
    myChart.showLoading();
    $.ajax({
        url : "/detailOfExpert/cooperateOfAuthor",
        type: "get",
        dataType : "json",
        data :{"name":"詹毅",
                "institution":"电子科技集团36所",
                "depath":2},
        success : function (graph) {
        myChart.hideLoading();
        graph.nodes.forEach(function (node) {
            console.info("value"+ "  " + node.value);
            console.info("name" + " " + node.name);
            console.info("category" + " " + node.category);
            node.itemStyle = null;
            node.value = node.value;
            node.symbolSize = node.value*5;
            node.label = {
                normal: {
                    show: node.symbolSize > 5
                }
            };
            node.category = node.category;
        });
        option = {
            title: {
                text: '作者合作关系图',
                top: 'bottom',
                left: 'right'
            },
            tooltip: {},
            legend: {
                data: ['专家']
            },
            animationDurationUpdate: 1500,
            animationEasingUpdate: 'quinticInOut',
            series : [
                {
                    name: '作者合作关系图',
                    type: 'graph',
                    layout: 'force',
//                    circular: {
//                        rotateLabel: true
//                    },
                    data: graph.nodes,
                    links: graph.links,
                    categories: graph.categories,
                    roam: true,
                    label: {
                        normal: {
                            position: 'inside',
                            formatter: '{a}'
                        }
                    },
                    lineStyle: {
                        normal: {
                            color: 'source',
                            curveness: 0.3
                        }
                    },
                    edgeSymbol: ['none', 'arrow'],
                    force: {
                        repulsion: 100
                    }
                }
            ]
        };
        myChart.setOption(option);
    }
    });
}

loadRelationshipPersonal();