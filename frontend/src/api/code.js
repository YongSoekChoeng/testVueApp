import http from './http'

// 공통코드관리 화면의 API. 그룹 아래 코드는 /sys/code-groups/{그룹코드}/codes
export const fetchCodeGroups = (keyword) =>
  http.get('/sys/code-groups', { params: { keyword } }).then((res) => res.data)

export const createCodeGroup = (group) => http.post('/sys/code-groups', group)

export const updateCodeGroup = (group) => http.put(`/sys/code-groups/${encodeURIComponent(group.grpCmmCd)}`, group)

export const deleteCodeGroup = (grpCmmCd) => http.delete(`/sys/code-groups/${encodeURIComponent(grpCmmCd)}`)

export const fetchCodes = (grpCmmCd) =>
  http.get(`/sys/code-groups/${encodeURIComponent(grpCmmCd)}/codes`).then((res) => res.data)

export const createCode = (grpCmmCd, code) =>
  http.post(`/sys/code-groups/${encodeURIComponent(grpCmmCd)}/codes`, code)

export const updateCode = (grpCmmCd, code) =>
  http.put(`/sys/code-groups/${encodeURIComponent(grpCmmCd)}/codes/${encodeURIComponent(code.cdVl)}`, code)

export const deleteCode = (grpCmmCd, cdVl) =>
  http.delete(`/sys/code-groups/${encodeURIComponent(grpCmmCd)}/codes/${encodeURIComponent(cdVl)}`)

// 콤보 상자용 코드 목록(사용 중인 코드만)
export const fetchCommonCodes = (grpCmmCd) =>
  http.get('/common/codes/' + grpCmmCd).then((res) => res.data)
