<template>
  <table class="grid">
    <thead>
      <tr>
        <th v-for="col in columns" :key="col.key" :style="{ width: col.width }">{{ col.label }}</th>
      </tr>
    </thead>
    <tbody>
      <tr v-if="rows.length === 0">
        <td :colspan="columns.length" class="empty">자료가 없습니다.</td>
      </tr>
      <tr
        v-for="(row, i) in rows"
        :key="rowKey ? row[rowKey] : i"
        :class="{ selected: selected === row }"
        @click="$emit('select', row)"
      >
        <td v-for="col in columns" :key="col.key">
          <slot :name="col.key" :row="row">{{ row[col.key] }}</slot>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script setup>
// 단순한 목록 표. 행을 누르면 select 이벤트를 낸다. 칸 모양은 이름 있는 slot 으로 바꿀 수 있다.
defineProps({
  columns: { type: Array, required: true },
  rows: { type: Array, default: () => [] },
  rowKey: { type: String, default: '' },
  selected: { type: Object, default: null }
})
defineEmits(['select'])
</script>
