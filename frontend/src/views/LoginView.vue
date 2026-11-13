<template>
  <div class="login-wrap">
    <form class="login-box" @submit.prevent="onSubmit">
      <h1>testVue</h1>
      <label>회사코드 <input v-model="form.mbId" autocomplete="organization" /></label>
      <label>아이디 <input v-model="form.loginId" autocomplete="username" /></label>
      <label>비밀번호 <input v-model="form.password" type="password" autocomplete="current-password" /></label>
      <p v-if="error" class="error">{{ error }}</p>
      <button class="btn primary" type="submit" :disabled="busy">로그인</button>
    </form>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const auth = useAuthStore()
const route = useRoute()
const router = useRouter()

const form = reactive({ mbId: 'PRD', loginId: '', password: '' })
const error = ref('')
const busy = ref(false)

async function onSubmit() {
  error.value = ''
  busy.value = true
  try {
    await auth.signIn(form)
    router.push(route.query.redirect || '/home')
  } catch (e) {
    error.value = e.message
  } finally {
    busy.value = false
  }
}
</script>
