import http from './http'

// 왼쪽 메뉴. menuUrl 이 화면 경로다(상위 메뉴는 NONE).
export const fetchMenus = () => http.get('/menus').then((res) => res.data)
