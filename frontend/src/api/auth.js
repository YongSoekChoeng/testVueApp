import http from './http'

// 로그인/로그아웃, 내 정보
export function login(form) {
  return http.post('/auth/login', form).then((res) => res.data)
}

export function logout() {
  return http.post('/auth/logout')
}

export function fetchMe() {
  return http.get('/auth/me').then((res) => res.data)
}
