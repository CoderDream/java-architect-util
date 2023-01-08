{
    title: {
        text:'${title}',
        x: 'middle',
        textAlign: 'center'
    },
    xAxis: {
        type: 'category',
        name: "时间m",
        data: ${categories}
    },
    yAxis: {
        type: 'value',
        name: "温度℃",
    },
    series: [{
        data: ${values},
        type: 'line',
        smooth: true,
        label: {
            normal: {
                show: true,
                position: 'top'
            }
        },
    }]
}