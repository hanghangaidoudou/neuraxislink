import Mock from 'mockjs'

const data = Mock.mock({
  'items|10': [{
    id: '@id',
    groupCode: '@sentence(1, 5)',
    groupName: '@ctitle(3,5)',
    groupDesc: '@ctitle(3,10)',
    version: '@integer(1, 1000)'
  }]
})
const dataDic = Mock.mock({
  groupCode: '@sentence(1, 5)',
  groupName: '@ctitle(3,5)',
  groupDesc: '@ctitle(3,10)',
  'items|10': [{
    id: '@id',
    itemCode: '@sentence(1, 5)',
    itemName: '@ctitle(3,5)',
    itemDesc: '@ctitle(3,10)',
    version: '@integer(1, 1000)'
  }]
})


export default [
  {
    url: '/datadic/getDataDics',
    type: 'get',
    response: config => {
      const items = data.items
      return {
        code: 20000,
        data: {
          total: items.length,
          items: items
        }
      }
    }
  },
  {
    url: '/datadic/getDataDic',
    type: 'get',
    response: config => {
      return {
        code: 20000,
        data: { 
          dataDic:dataDic
        }
      }
    }
  }
]
