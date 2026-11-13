<template>
  <nav class="side">
    <div v-for="group in menu.tree" :key="group.menuId" class="menu-group">
      <div class="menu-title">{{ group.menuNm }}</div>
      <router-link v-for="item in group.children" :key="item.menuId" :to="item.menuUrl" class="menu-item">
        {{ item.menuNm }}
      </router-link>
    </div>
  </nav>
</template>

<script setup>
import { useMenuStore } from '@/stores/menu'

// 왼쪽 메뉴. 메뉴 주소(menuUrl)가 곧 화면 주소다.
const menu = useMenuStore()
</script>
