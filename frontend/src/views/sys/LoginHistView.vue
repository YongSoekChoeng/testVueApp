<template>
  <div>
    <PageTitle title="로그인이력">
      <input v-model="fromYmd" type="date" />
      ~
      <input v-model="toYmd" type="date" />
      <input v-model="loginId" placeholder="로그인ID" @keyup.enter="search" />
      <button class="btn" @click="search">조회</button>
    </PageTitle>
    <p class="count">{{ rows.length }}건 (최근 500건까지)</p>
    <DataGrid :columns="columns" :rows="rows" />
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import PageTitle from '@/components/PageTitle.vue'
import DataGrid from '@/components/DataGrid.vue'
import http from '@/api/http'

// 로그인이력. 이 화면은 API 모듈을 거치지 않고 axios 인스턴스로 바로 부른다.
const columns = [
  { key: 'loginDtm', label: '로그인 일시', width: '22%' },
  { key: 'mbId', label: '회원사', width: '12%' },
  { key: 'loginId', label: '로그인ID', width: '18%' },
  { key: 'empNm', label: '이름' },
  { key: 'loginIp', label: 'IP', width: '18%' }
]

const today = new Date().toISOString().slice(0, 10)
const fromYmd = ref(today.slice(0, 8) + '01')
const toYmd = ref(today)
const loginId = ref('')
const rows = ref([])

async function search() {
  const res = await http.get('/sys/login-hists', {
    params: {
      fromYmd: fromYmd.value.replaceAll('-', ''),
      toYmd: toYmd.value.replaceAll('-', ''),
      loginId: loginId.value
    }
  })
  rows.value = res.data
}

onMounted(search)
</script>
