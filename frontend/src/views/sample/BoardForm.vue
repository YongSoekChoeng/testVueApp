<template>
  <div>
    <PageTitle :title="seq ? '게시글 고치기' : '게시글 쓰기'">
      <button class="btn" @click="router.back()">취소</button>
      <button class="btn primary" @click="onSave">저장</button>
    </PageTitle>
    <section class="panel">
      <label class="full">제목 <input v-model="form.title" /></label>
      <label class="full">작성자 <input v-model="form.writerNm" placeholder="비우면 로그인한 사람" /></label>
      <label class="full">내용 <textarea v-model="form.content" rows="12"></textarea></label>
    </section>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import PageTitle from '@/components/PageTitle.vue'
import * as boardApi from '@/api/board'

// 새 글(/sample/boards/new)과 고치기(/sample/boards/:seq/edit)를 같이 맡는다.
const props = defineProps({
  seq: { type: String, default: '' }
})

const router = useRouter()
const form = ref({ title: '', writerNm: '', content: '' })

async function onSave() {
  try {
    if (props.seq) {
      await boardApi.updateBoard(props.seq, form.value)
      router.push(`/sample/boards/${props.seq}`)
    } else {
      const seq = await boardApi.createBoard(form.value)
      router.push(`/sample/boards/${seq}`)
    }
  } catch (e) {
    alert(e.message)
  }
}

onMounted(async () => {
  if (props.seq) {
    const board = await boardApi.fetchBoard(props.seq)
    form.value = { title: board.title, writerNm: board.writerNm, content: board.content }
  }
})
</script>
