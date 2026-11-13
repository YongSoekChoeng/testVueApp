import { createRouter, createWebHistory } from 'vue-router'
import AppLayout from '@/components/AppLayout.vue'
import LoginView from '@/views/LoginView.vue'
import HomeView from '@/views/HomeView.vue'
import { useAuthStore } from '@/stores/auth'

// 화면 주소. meta.title 이 화면 이름이다. 메뉴(TBSY_RESOURCE.RESOURCE_URL)도 이 path 를 쓴다.
const routes = [
  { path: '/login', name: 'login', component: LoginView, meta: { title: '로그인', public: true } },
  {
    path: '/',
    component: AppLayout,
    children: [
      { path: '', redirect: '/home' },
      { path: 'home', name: 'home', component: HomeView, meta: { title: '홈' } },
      {
        path: 'sys/codes',
        name: 'codeManage',
        component: () => import('@/views/sys/CodeManage.vue'),
        meta: { title: '공통코드관리' }
      },
      {
        path: 'sys/members',
        name: 'memberManage',
        component: () => import('@/views/sys/MemberManage.vue'),
        meta: { title: '회원사관리' }
      },
      {
        path: 'sys/login-hists',
        name: 'loginHist',
        component: () => import('@/views/sys/LoginHistView.vue'),
        meta: { title: '로그인이력' }
      },
      {
        path: 'sample/boards',
        name: 'boardList',
        component: () => import('@/views/sample/BoardList.vue'),
        meta: { title: '샘플게시판' }
      },
      {
        path: 'sample/boards/new',
        name: 'boardNew',
        component: () => import('@/views/sample/BoardForm.vue'),
        meta: { title: '게시글 쓰기' }
      },
      {
        path: 'sample/boards/:seq',
        name: 'boardDetail',
        component: () => import('@/views/sample/BoardDetail.vue'),
        props: true,
        meta: { title: '게시글 보기' }
      },
      {
        path: 'sample/boards/:seq/edit',
        name: 'boardEdit',
        component: () => import('@/views/sample/BoardForm.vue'),
        props: true,
        meta: { title: '게시글 고치기' }
      }
    ]
  },
  { path: '/:pathMatch(.*)*', redirect: '/home' }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 로그인하지 않았으면 로그인 화면으로 보낸다.
router.beforeEach(async (to) => {
  if (to.meta.public) {
    return true
  }
  const auth = useAuthStore()
  const user = await auth.loadMe()
  if (!user) {
    return { path: '/login', query: { redirect: to.fullPath } }
  }
  return true
})

router.afterEach((to) => {
  document.title = to.meta.title ? `${to.meta.title} - testVue` : 'testVue'
})

export default router
