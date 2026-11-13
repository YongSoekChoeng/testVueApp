<template>
  <div class="page-title">
    <h2>{{ title }}</h2>
    <div class="actions"><slot /></div>
  </div>
</template>

<script setup>
// 화면 제목과 오른쪽 버튼 자리
defineProps({
  title: { type: String, required: true }
})
</script>
