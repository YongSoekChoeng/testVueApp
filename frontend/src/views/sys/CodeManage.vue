<template>
  <div>
    <PageTitle title="공통코드관리">
      <input v-model="keyword" placeholder="그룹코드/그룹명" @keyup.enter="searchGroups" />
      <button class="btn" @click="searchGroups">조회</button>
    </PageTitle>

    <div class="split">
      <section class="panel">
        <h3>코드그룹</h3>
        <DataGrid :columns="groupColumns" :rows="groups" row-key="grpCmmCd" :selected="selectedGroup" @select="selectGroup" />
        <div class="form-grid">
          <label>그룹코드 <input v-model="groupForm.grpCmmCd" :disabled="!groupIsNew" /></label>
          <label>그룹명 <input v-model="groupForm.grpCmmCdNm" /></label>
          <label>설명 <input v-model="groupForm.grpCmmCdDesc" /></label>
          <label>시스템구분 <input v-model="groupForm.systemGubun" /></label>
          <label>정렬 <input v-model.number="groupForm.sortNo" type="number" /></label>
        </div>
        <div class="buttons">
          <button class="btn" @click="newGroup">신규</button>
          <button class="btn primary" @click="saveGroup">저장</button>
          <button class="btn danger" :disabled="groupIsNew" @click="removeGroup">삭제</button>
        </div>
      </section>

      <section class="panel">
        <h3>코드 <small v-if="selectedGroup">({{ selectedGroup.grpCmmCd }})</small></h3>
        <DataGrid :columns="codeColumns" :rows="codes" row-key="cdVl" :selected="selectedCode" @select="selectCode" />
        <div class="form-grid">
          <label>코드값 <input v-model="codeForm.cdVl" :disabled="!codeIsNew" /></label>
          <label>코드명 <input v-model="codeForm.cdVlNm" /></label>
          <label>사용여부
            <select v-model="codeForm.useYn">
              <option v-for="c in useYnCodes" :key="c.cdVl" :value="c.cdVl">{{ c.cdVlNm }}</option>
            </select>
          </label>
          <label>정렬 <input v-model.number="codeForm.sortNo" type="number" /></label>
        </div>
        <div class="buttons">
          <button class="btn" :disabled="!selectedGroup" @click="newCode">신규</button>
          <button class="btn primary" :disabled="!selectedGroup" @click="saveCode">저장</button>
          <button class="btn danger" :disabled="codeIsNew" @click="removeCode">삭제</button>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
// 공통코드관리. 이 화면만 Options API 로 썼다(다른 화면은 <script setup>).
import PageTitle from '@/components/PageTitle.vue'
import DataGrid from '@/components/DataGrid.vue'
import * as codeApi from '@/api/code'

const emptyGroup = () => ({ grpCmmCd: '', grpCmmCdNm: '', grpCmmCdDesc: '', systemGubun: '', sortNo: 0 })
const emptyCode = () => ({ cdVl: '', cdVlNm: '', useYn: 'Y', sortNo: 0 })

export default {
  name: 'CodeManage',
  components: { PageTitle, DataGrid },
  data() {
    return {
      keyword: '',
      groups: [],
      selectedGroup: null,
      groupForm: emptyGroup(),
      groupIsNew: true,
      codes: [],
      selectedCode: null,
      codeForm: emptyCode(),
      codeIsNew: true,
      useYnCodes: [],
      groupColumns: [
        { key: 'grpCmmCd', label: '그룹코드', width: '30%' },
        { key: 'grpCmmCdNm', label: '그룹명' },
        { key: 'systemGubun', label: '시스템', width: '15%' },
        { key: 'sortNo', label: '정렬', width: '10%' }
      ],
      codeColumns: [
        { key: 'cdVl', label: '코드값', width: '25%' },
        { key: 'cdVlNm', label: '코드명' },
        { key: 'useYn', label: '사용', width: '12%' },
        { key: 'sortNo', label: '정렬', width: '10%' }
      ]
    }
  },
  async mounted() {
    this.useYnCodes = await codeApi.fetchCommonCodes('USE_YN')
    await this.searchGroups()
  },
  methods: {
    async searchGroups() {
      this.groups = await codeApi.fetchCodeGroups(this.keyword)
      this.newGroup()
    },
    async selectGroup(row) {
      this.selectedGroup = row
      this.groupForm = { ...row }
      this.groupIsNew = false
      this.codes = await codeApi.fetchCodes(row.grpCmmCd)
      this.newCode()
    },
    newGroup() {
      this.selectedGroup = null
      this.groupForm = emptyGroup()
      this.groupIsNew = true
      this.codes = []
      this.newCode()
    },
    async saveGroup() {
      try {
        if (this.groupIsNew) {
          await codeApi.createCodeGroup(this.groupForm)
        } else {
          await codeApi.updateCodeGroup(this.groupForm)
        }
        alert('저장했습니다.')
        await this.searchGroups()
      } catch (e) {
        alert(e.message)
      }
    },
    async removeGroup() {
      if (!confirm(`그룹 ${this.groupForm.grpCmmCd} 와 아래 코드를 모두 지울까요?`)) {
        return
      }
      await codeApi.deleteCodeGroup(this.groupForm.grpCmmCd)
      await this.searchGroups()
    },
    selectCode(row) {
      this.selectedCode = row
      this.codeForm = { ...row }
      this.codeIsNew = false
    },
    newCode() {
      this.selectedCode = null
      this.codeForm = emptyCode()
      this.codeIsNew = true
    },
    async saveCode() {
      const grpCmmCd = this.selectedGroup.grpCmmCd
      try {
        if (this.codeIsNew) {
          await codeApi.createCode(grpCmmCd, this.codeForm)
        } else {
          await codeApi.updateCode(grpCmmCd, this.codeForm)
        }
        this.codes = await codeApi.fetchCodes(grpCmmCd)
        this.newCode()
      } catch (e) {
        alert(e.message)
      }
    },
    async removeCode() {
      const grpCmmCd = this.selectedGroup.grpCmmCd
      await codeApi.deleteCode(grpCmmCd, this.codeForm.cdVl)
      this.codes = await codeApi.fetchCodes(grpCmmCd)
      this.newCode()
    }
  }
}
</script>
