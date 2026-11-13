import axios from 'axios'
import router from '@/router'

// 서버 API 를 부르는 axios 인스턴스. 모든 주소 앞에 /api 가 붙는다.
const http = axios.create({
  baseURL: '/api',
  timeout: 30000
})

// 401(로그인 안 됨)이면 로그인 화면으로 보낸다. 오류 메시지는 서버가 준 message 를 쓴다.
http.interceptors.response.use(
  (response) => response,
  (error) => {
    const status = error.response ? error.response.status : 0
    if (status === 401 && router.currentRoute.value.path !== '/login') {
      router.push({ path: '/login', query: { redirect: router.currentRoute.value.fullPath } })
    }
    const message = error.response && error.response.data && error.response.data.message
    return Promise.reject(new Error(message || error.message))
  }
)

export default http
