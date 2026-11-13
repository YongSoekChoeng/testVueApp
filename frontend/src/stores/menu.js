import { defineStore } from 'pinia'
import { fetchMenus } from '@/api/menu'

// 왼쪽 메뉴 트리. 서버는 상위 메뉴 다음에 하위 메뉴가 오는 순서로 준다.
export const useMenuStore = defineStore('menu', {
  state: () => ({
    menus: []
  }),
  getters: {
    tree: (state) => {
      const roots = []
      const byId = {}
      for (const m of state.menus) {
        byId[m.menuId] = { ...m, children: [] }
      }
      for (const m of state.menus) {
        const node = byId[m.menuId]
        if (m.prntMenuId && byId[m.prntMenuId]) {
          byId[m.prntMenuId].children.push(node)
        } else {
          roots.push(node)
        }
      }
      return roots
    }
  },
  actions: {
    async load() {
      this.menus = await fetchMenus()
    },
    clear() {
      this.menus = []
    }
  }
})
