import Mock from 'mockjs'

Mock.mock(/getNewsList/, {
    'list|4': [{
        'url': '#',
        'title': '最新热销产品'
    }]
})
Mock.mock(/getPrice/, {
	'number|1-100': 100 
})
Mock.mock(/createOrder/, 'number|1-100')

Mock.mock(/getBoardList/, [
        {
          title: '智能手表',
          description: '智能手表是具有信息处理能力的手表。',
          id: 'car',
          toKey: 'count',
          saleout: '@boolean'
        },
        {
          title: '显示器',
          description: '全新HDMI高清液晶显示器',
          id: 'earth',
          toKey: 'analysis',
          saleout: '@boolean'
        },
        {
          title: '电风扇',
          description: '家用节能低噪音风扇',
          id: 'loud',
          toKey: 'forecast',
          saleout: '@boolean'
        },
        {
          title: '打印机',
          description: '家用黑白静音打印机',
          id: 'hill',
          toKey: 'publish',
          saleout: '@boolean'
        }
])

Mock.mock(/getProductList/, {
        pc: {
          title: '家用电器',
          list: [
            {
              name: '穿戴智能设备',
              url: '#',
              hot: '@boolean'
            },
            {
              name: '办公电脑设备',
              url: '#',
              hot: '@boolean'
            },
            {
              name: '家用日常设备',
              url: '#',
              hot: '@boolean'
            },
            {
              name: '打印设备',
              url: '#',
              hot: '@boolean'
            }
          ]
        },
        app: {
          title: '运动户外商品',
          last: true,
          list: [
            {
              name: '男鞋',
              url: '#',
              hot: '@boolean'
            },
            {
              name: '户外旅游装备',
              url: '#',
              hot: '@boolean'
            },
            {
              name: '运动服装',
              url: '#',
              hot: '@boolean'
            },
            {
              name: '帐篷',
              url: '#',
              hot: '@boolean'
            }
          ]
        }
})
Mock.mock(/getTableData/, {
    "total": 25,
    "list|25": [
      {
        "orderId": "@id",
        "product": "@ctitle(4)",
        "version": "@ctitle(3)",
        "period": "@integer(1,5)年",
        "buyNum": "@integer(1,8)",
        "date": "@date()",
        "amount": "@integer(10, 500)元"
      }
    ]
})