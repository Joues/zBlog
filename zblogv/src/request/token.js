export function getToken() {
  return localStorage.token
}

export function setToken(token) {
  return localStorage.token = token
}

export function removeToken() {
  return localStorage.removeItem('token')
}

// 把用户信息保存到localStorage里面
export function getUserinfos() {
  return localStorage.Userinfo
}

export function setUserinfos(Userinfo) {
  return localStorage.Userinfo = Userinfo
}

export function removeUserinfos() {
  return localStorage.removeItem('Userinfo')
}