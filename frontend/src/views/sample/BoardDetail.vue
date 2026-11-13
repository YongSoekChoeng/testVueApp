<template>
  <div v-if="board">
    <PageTitle :title="board.title">
      <router-link class="btn" to="/sample/boards">목록</router-link>
      <router-link class="btn primary" :to="`/sample/boards/${seq}/edit`">고치기</router-link>
      <button class="btn danger" @click="onDelete">삭제</button>
    </PageTitle>
    <p class="meta">{{ board.writerNm }} · {{ board.inDtm }}</p>
    <pre class="board-content">{{ board.content }}</pre>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import PageTitle from '@/components/PageTitle.vue'
import { fetchBoard, deleteBoard } from '@/api/board'

const props = defineProps({
  seq: { type: String, required: true }
})

const router = useRouter()
const board = ref(null)

async function onDelete() {
  if (!confirm('이 글을 지울까요?')) {
    return
  }
  await deleteBoard(props.seq)
  router.push('/sample/boards')
}

onMounted(async () => {
  try {
    board.value = await fetchBoard(props.seq)
  } catch (e) {
    alert(e.message)
    router.push('/sample/boards')
  }
})
</script>
