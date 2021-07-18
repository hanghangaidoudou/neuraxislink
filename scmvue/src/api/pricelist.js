import request from '@/utils/request'

export function getList(query) {
  return request({
    url: '/ETong/list',
    method: 'get',
    params:query
  })
}
 