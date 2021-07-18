import request from '@/utils/request'

export function getDataDicItem(code) {
  return request({
    url: '/ETong/getDataDicItem',
    method: 'get',
    params:{groupCode:code}
  })
}

export function getDeptSelInfo() {
  return request({
    url: '/ETong/getDeptSelInfo',
    method: 'get'
  })
}