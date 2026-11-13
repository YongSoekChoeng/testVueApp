<template>
  <div>
    <PageTitle title="샘플게시판">
      <input v-model="title" placeholder="제목" @keyup.enter="search" />
      <button class="btn" @click="search">조회</button>
      <router-link class="btn primary" to="/sample/boards/new">글쓰기</router-link>
    </PageTitle>
    <DataGrid :columns="columns" :rows="boards" row-key="seq" @select="open">
      <template #title="{ row }">
        <a href="#" @click.prevent="open(row)">{{ row.title }}</a>
      </template>
    </DataGrid>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import PageTitle from '@/components/PageTitle.vue'
import DataGrid from '@/components/DataGrid.vue'
import { fetchBoards } from '@/api/board'

const columns = [
  { key: 'seq', label: '번호', width: '10%' },
  { key: 'title', label: '제목' },
  { key: 'writerNm', label: '작성자', width: '15%' },
  { key: 'inDtm', label: '작성일시', width: '20%' }
]

const router = useRouter()
const title = ref('')
const boards = ref([])

async function search() {
  boards.value = await fetchBoards(title.value)
}

function open(row) {
  router.push({ name: 'boardDetail', params: { seq: row.seq } })
}

onMounted(search)
</script>
