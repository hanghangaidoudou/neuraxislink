import request from '@/utils/request'

export function getDataDics(query) {
  return request({
    url: '/ETong/getDataDics',
    method: 'get',
    params:query
  })
}

export function getDataDic(dataDicId) {
  return request({
    url: '/ETong/getDataDic',
    method: 'get',
    params:{dataDicId}
  })
}

export function addDataDic(data) {
  return request({
    url: '/ETong/addDataDic',
    method: 'post',
    data
  })
}

export function updateDataDic(data) {
  return request({
    url: '/ETong/updateDataDic',
    method: 'post',
    data
  })
}

export function delDataDic(dataDicId) {
  return request({
    url: '/ETong/delDataDic',
    method: 'get',
    params: {dataDicId}
  })
}

export function addDataDicItem(data) {
  return request({
    url: '/ETong/addDataDicItem',
    method: 'post',
    data
  })
}

export function updateDataDicItem(data) {
  return request({
    url: '/ETong/updateDataDicItem',
    method: 'post',
    data
  })
}

export function delDataDicItem(dataDicItemId) {
  return request({
    url: '/ETong/delDataDicItem',
    method: 'get',
    params: {dataDicItemId}
  })
}