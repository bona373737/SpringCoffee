import { createWebHistory, createRouter } from 'vue-router';

const routes = [
  {
    path: '/',
    component: () => import('@/components/user/Content.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/components/user/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/components/user/Register.vue')
  },
  {
    path: '/admin',
    name: 'Admin',
    component: () => import('@/components/admin/Admin.vue')
  },
  {
    path: '/*',
    component: () => import('@/components/user/NotFoundPage.vue')
  },
];

export const router = createRouter({
  history: createWebHistory(),
  routes,
});