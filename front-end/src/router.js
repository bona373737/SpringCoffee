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
    path: '/noticeLayout',
    name: 'NoticeLayout',
    component: () => import('@/components/user/notice/NoticeLayout.vue'),
    children :[
      {
        path: '',
        name: 'noticeList',
        component: () => import('@/components/user/notice/NoticeList.vue'),
        props : true
      },
      {
        path: 'noticeDetail/:boardNo',
        name: 'noticeDetail',
        component: () => import('@/components/user/notice/NoticeDetail.vue')
      },
      {
        path: '/noticeAdd',
        name: 'noticeAdd',
        component: () => import('@/components/user/notice/NoticeAdd.vue')
      },
      {
        path: '/noticeUpdate:boardNo',
        name: 'noticeUpdate',
        component: () => import('@/components/user/notice/NoticeUpdate.vue')
      },
    ]
  },
  {
    path: '/inquiry',
    name: 'Inquiry',
    component: () => import('@/components/user/Inquiry.vue')
  },
  {
    path: '/offstore',
    name: 'OffStore',
    component: () => import('@/components/user/OffStore.vue')
  },
  {
    path: '/mypage/:',
    redirect: '/mypage/profile',
    name: 'MyPage',
    component: () => import('@/components/user/MyPage.vue'),
    children: [
      {
        path: "profile",
        component: () => import('@/components/user/Profile.vue'),
      },
    ]
  },
  {
    path: '/shop',
    name: 'Shop',
    component: () => import('@/components/user/shop/ShopLayout.vue')
  },
  {
    path: '/admin',
    name: 'Admin',
    component: () => import('@/components/admin/Admin.vue')
  },
  // {
  //   path: '/:pathMatch(.*)*',
  //   redirect: "/404"
  // },
  // {
  //   path: '/404',
  //   name: 'NotFoundPage',
  //   component: () => import('@/components/user/NotFoundPage.vue')
  // },
];

export const router = createRouter({
  history: createWebHistory(),
  routes,
});