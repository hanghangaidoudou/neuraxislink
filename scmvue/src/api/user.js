import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/ETong/login',
    method: 'post',
    params:{ 
      userCode:data.userCode,
      password:data.password
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/ETong/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/ETong/logout',
    method: 'post'
  })
}

export function getUserInfos(params) {
  debugger
  return request({
    url: '/ETong/getUserInfos',
    method: 'get',
    params:{
      userCode:params.userCode,
      userName:params.userName,
      deptId:params.deptId,
      teamId:params.teamId,
      state:params.state,
      pageNum:params.pageNum,
      pageSize:params.pageSize
    }
  })
}

export function getUserInfo(id) {
  return request({
    url: '/ETong/getUserInfoById',
    method: 'get',
    params:{ id : id }
  })
}

export function addUserInfo(data) {
  return request({
    url: '/ETong/insertUser',
    method: 'post',
    data
  })
}

export function updUserInfo(data) {
  return request({
    url: '/ETong/updateUser',
    method: 'post',
    data
  })
}

export function updUserInfoBySelf(data) {
  return request({
    url: '/ETong/updUserInfoBySelf',
    method: 'post',
    data
  })
}

export function updatePwd(data) {
  return request({
    url: '/ETong/updatePassword',
    method: 'post',
    data
  })
}