import http from './http'

// 회원사관리 화면의 API
export default {
  list(params) {
    return http.get('/sys/members', { params }).then((res) => res.data)
  },
  create(member) {
    return http.post('/sys/members', member)
  },
  update(member) {
    return http.put('/sys/members/' + member.mbId, member)
  }
}
