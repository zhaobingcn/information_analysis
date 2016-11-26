/**
 * Created by zhzy on 2016/11/26.
 */
var myChart = echarts.init(document.getElementById("hot-journal"));
option = {
    title : {
        text: '热门期刊所占比例',
        subtext: '截止2016年',
        x:'center'
    },

    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        bottom: 'bottom',
        orient: 'horizontal',
        data:['计算机学报','光学学报','系统与工程','计算机工程','计算机科学','武器装备','计算机应用','中电科','中核工业','中航工业','高校','其他']
    },
    series: [
        {
            name:'文章来源',
            type:'pie',
            selectedMode: 'single',
            radius: [0, '30%'],

            label: {
                normal: {
                    position: 'inner'
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:335, name:'中电科', selected:true},
                {value:679, name:'中核工业'},
                {value:1548, name:'中航工业'},
                {valee:1225, name:'高校'}
            ]
        },
        {
            name:'所投期刊',
            type:'pie',
            radius: ['40%', '55%'],

            data:[
                {value:335, name:'计算机学报'},
                {value:310, name:'光学学报'},
                {value:234, name:'系统与工程'},
                {value:135, name:'计算机工程'},
                {value:1048, name:'计算机科学'},
                {value:251, name:'武器装备',},
                {value:147, name:'计算机应用'},
                {value:102, name:'其他'}
            ]
        }
    ]
};
myChart.setOption(option);

