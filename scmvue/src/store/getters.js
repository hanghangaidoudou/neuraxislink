const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  userCode: state => state.user.userCode,
  userName: state => state.user.userName,
  userId: state => state.user.userId
}
export default getters
