import { defineStore } from 'pinia'
import { login, logout, fetchMe } from '@/api/auth'

// 로그인한 사용자. 새로고침하면 서버 세션(/api/auth/me)에서 다시 읽는다.
export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: null,
    checked: false
  }),
  getters: {
    isLoggedIn: (state) => state.user !== null
  },
  actions: {
    async signIn(form) {
      this.user = await login(form)
      this.checked = true
    },
    async signOut() {
      try {
        await logout()
      } finally {
        this.user = null
      }
    },
    async loadMe() {
      if (this.checked) {
        return this.user
      }
      try {
        this.user = (await fetchMe()) || null
      } catch (e) {
        this.user = null
      }
      this.checked = true
      return this.user
    }
  }
})
