import { createWebHistory, createRouter } from 'vue-router';

const routes = [
  {
    path: '/',
    name: 'App',
    component: () => import('@/App.vue'), // 동적 import
  },
  // {
  //   path: '/login',
  //   name: 'Login',
  //   component: () => import('@/views/Login'),
  // },
];

// 이렇게 해도 된다.
// const router = createRouter({
//   history: createWebHistory(),
//   routes,
// });
// export default router;

export const router = createRouter({
  history: createWebHistory(),
  routes,
});