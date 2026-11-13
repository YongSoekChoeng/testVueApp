<template>
  <div>
    <PageTitle title="회원사관리">
      <input v-model="search.mbNm" placeholder="회원사명" @keyup.enter="search_" />
      <select v-model="search.useYn">
        <option value="">전체</option>
        <option v-for="c in useYnCodes" :key="c.cdVl" :value="c.cdVl">{{ c.cdVlNm }}</option>
      </select>
      <button class="btn" @click="search_">조회</button>
    </PageTitle>

    <DataGrid :columns="columns" :rows="members" row-key="mbId" :selected="selected" @select="onSelect" />

    <section class="panel">
      <div class="form-grid">
        <label>회원사ID <input v-model="form.mbId" :disabled="!isNew" /></label>
        <label>회원사명 <input v-model="form.mbNm" /></label>
        <label>대표자 <input v-model="form.representNm" /></label>
        <label>전화번호 <input v-model="form.telno" /></label>
        <label>계약시작일 <input v-model="form.contFrymd" placeholder="yyyyMMdd" /></label>
        <label>계약종료일 <input v-model="form.contToymd" placeholder="yyyyMMdd" /></label>
        <label>사용여부
          <select v-model="form.useYn">
            <option v-for="c in useYnCodes" :key="c.cdVl" :value="c.cdVl">{{ c.cdVlNm }}</option>
          </select>
        </label>
        <label>메모 <input v-model="form.memo" /></label>
      </div>
      <div class="buttons">
        <button class="btn" @click="onNew">신규</button>
        <button class="btn primary" @click="onSave">저장</button>
      </div>
    </section>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue'
import PageTitle from '@/components/PageTitle.vue'
import DataGrid from '@/components/DataGrid.vue'
import memberApi from '@/api/member'
import { useCommonCode } from '@/composables/useCommonCode'

// 회원사관리. 회원사는 지우지 않고 사용여부만 바꾼다.
const columns = [
  { key: 'mbId', label: '회원사ID', width: '12%' },
  { key: 'mbNm', label: '회원사명' },
  { key: 'representNm', label: '대표자', width: '12%' },
  { key: 'telno', label: '전화번호', width: '15%' },
  { key: 'contFrymd', label: '계약시작', width: '12%' },
  { key: 'contToymd', label: '계약종료', width: '12%' },
  { key: 'useYn', label: '사용', width: '8%' }
]
const emptyMember = () => ({
  mbId: '', mbNm: '', representNm: '', telno: '', contFrymd: '', contToymd: '', useYn: 'Y', memo: ''
})

const { codes: useYnCodes } = useCommonCode('USE_YN')
const search = reactive({ mbNm: '', useYn: '' })
const members = ref([])
const selected = ref(null)
const form = ref(emptyMember())
const isNew = ref(true)

async function search_() {
  members.value = await memberApi.list(search)
  onNew()
}

function onSelect(row) {
  selected.value = row
  form.value = { ...row }
  isNew.value = false
}

function onNew() {
  selected.value = null
  form.value = emptyMember()
  isNew.value = true
}

async function onSave() {
  try {
    if (isNew.value) {
      await memberApi.create(form.value)
    } else {
      await memberApi.update(form.value)
    }
    alert('저장했습니다.')
    await search_()
  } catch (e) {
    alert(e.message)
  }
}

onMounted(search_)
</script>
