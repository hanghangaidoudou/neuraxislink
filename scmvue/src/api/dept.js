import request from '@/utils/request'

export function getDepts(query) {
  return request({
    url: '/ETong/getDeptInfos',
    method: 'get',
    params:query
  })
}

export function getDept(deptId) {
  return request({
    url: '/ETong/getDeptById',
    method: 'get',
    params:{deptId}
  })
}

export function addDept(data) {
  return request({
    url: '/ETong/insertDept',
    method: 'post',
    data
  })
}

export function updateDept(data) {
  return request({
    url: '/ETong/updateDept',
    method: 'post',
    data
  })
}

export function delDept(deptId) {
  return request({
    url: '/ETong/delDept',
    method: 'get',
    params: {deptId}
  })
}

export function addTeam(data) {
  return request({
    url: '/ETong/insertTeam',
    method: 'post',
    data
  })
}

export function updateTeam(data) {
  return request({
    url: '/ETong/updateTeam',
    method: 'post',
    data
  })
}

export function delTeam(teamId) {
  return request({
    url: '/ETong/delTeam',
    method: 'get',
    params: {teamId}
  })
}