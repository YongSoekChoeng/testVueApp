<template>
  <div class="layout">
    <header class="top">
      <router-link to="/home" class="logo">testVue</router-link>
      <div class="user" v-if="auth.user">
        {{ auth.user.mbNm }} · {{ auth.user.empNm }}({{ auth.user.loginId }})
        <button class="btn" @click="onLogout">로그아웃</button>
      </div>
    </header>
    <div class="body">
      <SideMenu />
      <main class="content">
        <router-view />
      </main>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
import SideMenu from './SideMenu.vue'
import { useAuthStore } from '@/stores/auth'
import { useMenuStore } from '@/stores/menu'

// 상단(로고, 사용자, 로그아웃) + 왼쪽 메뉴 + 업무 화면
const auth = useAuthStore()
const menu = useMenuStore()
const router = useRouter()

onMounted(() => {
  menu.load()
})

async function onLogout() {
  await auth.signOut()
  menu.clear()
  router.push('/login')
}
</script>
