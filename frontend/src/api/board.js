import http from './http'

// 샘플게시판 API
export async function fetchBoards(title) {
  const res = await http.get('/sample/boards', { params: { title } })
  return res.data
}

export async function fetchBoard(seq) {
  const res = await http.get(`/sample/boards/${seq}`)
  return res.data
}

export async function createBoard(board) {
  const res = await http.post('/sample/boards', board)
  return res.data.seq
}

export function updateBoard(seq, board) {
  return http.put(`/sample/boards/${seq}`, board)
}

export function deleteBoard(seq) {
  return http.delete(`/sample/boards/${seq}`)
}
