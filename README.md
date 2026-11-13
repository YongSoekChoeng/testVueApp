# testVueApp

Vue 3 화면 + Spring Boot REST 서버로 만든 시험용 업무 앱입니다. dstone-knowledge가 Vue 화면(SPA)을 분석하는지 시험하려고 만들었습니다.

- 기능: `/app/testApp`, `/app/testNexacroApp`, `/app/testWebSqureApp`과 같습니다. 로그인/로그아웃, 메뉴, 공통코드관리, 회원사관리, 로그인이력, 샘플게시판이 있습니다.
- 화면: Vue 3.5(SFC), vue-router 4, Pinia 3, axios, Vite 6. JavaScript로 썼습니다.
- 서버: Spring Boot 3.5(Java 21), MyBatis(매퍼 인터페이스 + XML), MySQL. `/api/**` REST만 맡습니다.
- 실행: jar 하나에 화면 빌드 결과가 함께 들어갑니다. 포트는 **7100**, DB는 로컬 MySQL `testVue`입니다.
- 짝: 같은 기능을 React로 만든 `/app/testReactApp`(포트 7105)이 있습니다. 서버 코드는 패키지 이름만 다릅니다.

## 1. 바로 실행

```bash
cd /app/testVueApp
bin/startApp.sh        # 화면 빌드(npm) + 서버 빌드(mvn) + 기동 (http://localhost:7100/)
bin/startApp.sh -n     # 빌드 없이 target/testVue.jar 그대로 기동
bin/statusApp.sh       # RUNNING / STARTING / STOPPED
bin/stopApp.sh
```

로그인 정보는 회사코드 `PRD`, 아이디 `admin`, 비밀번호 `test1234!`입니다.

DB는 처음에 한 번 만듭니다.

```bash
sudo mysql < db/00-init.sql                                                   # DB와 계정 tvueapp
mysql -h127.0.0.1 -utvueapp -p'Tvueapp1234!' testVue < db/01-schema.sql       # 테이블 (다시 돌리면 비우고 새로 만든다)
mysql -h127.0.0.1 -utvueapp -p'Tvueapp1234!' testVue < db/02-seed.sql         # 시험 데이터
```

화면만 고칠 때는 서버를 띄워 둔 채 개발 서버를 쓰면 편합니다. 개발 서버(5173)는 `/api`를 7100으로 넘깁니다.

```bash
cd frontend && npm run dev      # http://localhost:5173/
```

## 2. 구조

```
testVueApp/
├── bin/                         startApp.sh, stopApp.sh, statusApp.sh (PID: bin/application.pid)
├── db/                          00-init.sql, 01-schema.sql, 02-seed.sql
├── logs/                        testVue.log(앱 로그), testVue.out(표준 출력)
├── pom.xml                      frontend/dist 를 jar 안 static/ 으로 넣는다
├── src/main/java/com/testvue/
│   ├── common/                  auth(LoginUser, SessionUtil, LoginInterceptor), exception, util, web(WebConfig, SpaController)
│   ├── auth/                    AuthController(/api/auth/*, /api/menus), AuthService, AuthMapper
│   ├── sys/code/                CodeController(/api/sys/code-groups), CommonCodeController(/api/common/codes/{grp})
│   ├── sys/member/              MemberController(/api/sys/members)
│   ├── sys/loginhist/           LoginHistController(/api/sys/login-hists)
│   └── sample/board/            BoardController(/api/sample/boards)
├── src/main/resources/
│   ├── application.yml
│   └── mapper/{auth,sys,sample}/*Mapper.xml
└── frontend/
    ├── vite.config.js           @ = src, 개발 서버 /api → 7100
    ├── jsconfig.json            @/* 경로
    └── src/
        ├── main.js, App.vue
        ├── router/index.js      화면 주소와 meta.title(화면 이름)
        ├── api/                 http.js(axios 인스턴스, baseURL /api), auth, menu, code, member, board
        ├── stores/              auth.js, menu.js (Pinia)
        ├── composables/         useCommonCode.js (콤보용 공통코드)
        ├── components/          AppLayout, SideMenu, PageTitle, DataGrid
        └── views/               LoginView, HomeView, sys/{CodeManage,MemberManage,LoginHistView}, sample/{BoardList,BoardDetail,BoardForm}
```

## 3. 업무 하나가 흐르는 길

공통코드 그룹 조회를 예로 들면 다음 순서로 흐릅니다.

1. `views/sys/CodeManage.vue`의 `searchGroups()`가 `codeApi.fetchCodeGroups(keyword)`를 부릅니다. `import * as codeApi from '@/api/code'`로 가져온 함수입니다.
2. `api/code.js`가 `http.get('/sys/code-groups', { params })`를 부르고, `api/http.js`의 axios 인스턴스가 앞에 `/api`를 붙입니다.
3. `CodeController.list`(`@RequestMapping("/api/sys/code-groups")` + `@GetMapping`) → `CodeService.getCodeGroups` → `CodeMapper.selectCodeGroupList` → `TBCM_COMMON_CODE_GROUP` 순서로 이어집니다.
4. 오류가 나면 `GlobalExceptionHandler`가 `{"message": ...}`로 바꿉니다. 세션이 없으면 `LoginInterceptor`가 401을 돌려주고, `http.js`가 로그인 화면으로 보냅니다.

## 4. 화면 → API 대응표

dstone-knowledge가 찾아야 할 정답입니다. 화면에서 서버 주소까지 가는 길을 함께 적었습니다.

| 화면 (route) | 부르는 방법 | API |
|---|---|---|
| `LoginView` (`/login`) | `stores/auth.js` `signIn` → `api/auth.js` `login` | `POST /api/auth/login` |
| `AppLayout` | `stores/auth.js` `signOut` → `logout` | `POST /api/auth/logout` |
| (라우터 가드) | `stores/auth.js` `loadMe` → `fetchMe` | `GET /api/auth/me` |
| `AppLayout` → `SideMenu` | `stores/menu.js` `load` → `api/menu.js` `fetchMenus` | `GET /api/menus` |
| `sys/CodeManage` (`/sys/codes`) | `import * as codeApi` (Options API) | `GET/POST /api/sys/code-groups`, `PUT/DELETE /api/sys/code-groups/{grpCmmCd}`, `GET/POST …/{grpCmmCd}/codes`, `PUT/DELETE …/codes/{cdVl}`, `GET /api/common/codes/{grpCmmCd}` |
| `sys/MemberManage` (`/sys/members`) | `api/member.js` default 객체의 메소드, `composables/useCommonCode` | `GET/POST /api/sys/members`, `PUT /api/sys/members/{mbId}`, `GET /api/common/codes/{grpCmmCd}` |
| `sys/LoginHistView` (`/sys/login-hists`) | API 모듈 없이 `http.get` 직접 호출 | `GET /api/sys/login-hists` |
| `sample/BoardList` (`/sample/boards`) | `fetchBoards` | `GET /api/sample/boards` |
| `sample/BoardDetail` (`/sample/boards/:seq`) | `fetchBoard`, `deleteBoard` | `GET/DELETE /api/sample/boards/{seq}` |
| `sample/BoardForm` (`/sample/boards/new`, `/:seq/edit`) | `import * as boardApi` | `GET/POST /api/sample/boards`, `PUT /api/sample/boards/{seq}` |

일부러 섞어 둔 모양은 다음과 같습니다(분석기가 모두 따라가야 합니다).

- API 모듈 함수: `export function`, `export const x = () =>`, `export async function`, `export default { list() {} }`
- 주소 글자: 따옴표(`'/sys/members'`), 따옴표 + 이어 붙이기(`'/sys/members/' + member.mbId`), 템플릿 리터럴(`` `/sample/boards/${seq}` ``)
- import: 이름 지정(`import { fetchBoards }`), 전체(`import * as codeApi`), default(`import memberApi`), `@` alias와 상대 경로(`./http`)
- 여러 단계: 화면 → Pinia store → API 모듈 → axios 인스턴스
- 컴포넌트: 정적 import(라우터의 `AppLayout`, `LoginView`)와 지연 import(`() => import('@/views/…')`)
- 화면 이름: 라우터 `meta.title`, 메뉴 `TBSY_RESOURCE.RESOURCE_URL` = route path
