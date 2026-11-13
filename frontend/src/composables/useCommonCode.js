import { ref, onMounted } from 'vue'
import { fetchCommonCodes } from '@/api/code'

// 콤보 상자에 넣을 공통코드. 예: const { codes } = useCommonCode('USE_YN')
export function useCommonCode(grpCmmCd) {
  const codes = ref([])
  onMounted(async () => {
    codes.value = await fetchCommonCodes(grpCmmCd)
  })
  return { codes }
}
