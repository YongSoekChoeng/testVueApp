import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// 개발할 때(npm run dev, 5173)는 /api 를 서버(7100)로 넘긴다.
// 배포할 때는 npm run build 결과(dist)를 서버 jar 의 static/ 에 넣어 같은 포트로 낸다.
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    port: 5173,
    proxy: {
      '/api': 'http://localhost:7100'
    }
  }
})
