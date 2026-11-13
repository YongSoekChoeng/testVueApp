<template>
  <div>
    <PageTitle title="홈" />
    <p>왼쪽 메뉴에서 화면을 고르세요.</p>
    <ul class="home-links">
      <li><router-link to="/sys/codes">공통코드관리</router-link></li>
      <li><router-link to="/sys/members">회원사관리</router-link></li>
      <li><router-link to="/sys/login-hists">로그인이력</router-link></li>
      <li><router-link to="/sample/boards">샘플게시판</router-link></li>
    </ul>
  </div>
</template>

<script setup>
import PageTitle from '@/components/PageTitle.vue'
</script>
