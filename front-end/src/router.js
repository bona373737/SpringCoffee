import { createWebHistory, createRouter } from 'vue-router';
import Content from '@/components/user/Content.vue'

const routes = [
  {
    path: '/',
    name: 'Content',
    component : Content
    // component: () => import('@/components/user/Content.vue')
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
    path: '/noticeForm',
    name: 'NoticeForm',
    component: () => import('@/components/user/NoticeForm.vue')
  },
  {
    path: '/inquiry',
    name: 'Inquiry',
    component: () => import('@/components/user/Inquiry.vue')
  },
  {
    path: '/shop',
    name: 'Shop',
    component: () => import('@/components/user/Shop.vue')
  },
  {
    path: '/admin',
    name: 'Admin',
    component: () => import('@/components/admin/Admin.vue')
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: "/404"
  },
  {
    path: '/404',
    name: 'NotFoundPage',
    component: () => import('@/components/user/NotFoundPage.vue')
  },
];

export const router = createRouter({
  history: createWebHistory(),
  routes,
});