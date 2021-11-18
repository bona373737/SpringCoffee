import { createWebHashHistory, createRouter } from 'vue-router';
import Content from '@/components/user/Content.vue'
import QnaLayout from '@/components/user/qna/QnaLayout'

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
    path: '/qnaLayout',
    name: 'QnaLayout',
    component: QnaLayout,
    // component: () => import('@/components/user/qna/QnaLayout.vue'),
    children :[
      {
        path: '',
        name: 'QnaList',
        component: () => import('@/components/user/qna/QnaList.vue'),
        props : true
      },
      {
        path: 'qnaDetail/:qnaBoardNo',
        name: 'qnaDetail',
        component: () => import('@/components/user/qna/QnaDetail.vue'),
      },
      {
        path: '/qnaAdd',
        name: 'qnaAdd',
        component: () => import('@/components/user/qna/QnaAdd.vue')
      },
      {
        path: '/qnaUpdate/:qnaBoardNo',
        name: 'qnaUpdate',
        component: () => import('@/components/user/qna/QnaUpdate.vue')
      },
    ]
  },
  {
    path: '/offstore',
    name: 'OffStore',
    component: () => import('@/components/user/OffStore.vue')
  },
  {
    path: '/mypage',
    redirect: '/mypage/profile',
    name: 'MyPage',
    component: () => import('@/components/user/MyPage.vue'),
    children: [
      {
        path: "profile",
        component: () => import('@/components/user/Profile.vue'),
      },
      {
        path: '/cart',
        name: 'Cart',
        component: () => import('@/components/user/shop/Cart.vue'),
      },
      {
        path: "/myQna",
        component: () => import('@/components/user/MyQna.vue'),
      },
    ]
  },
  {
    path: '/shop',
    name: 'Shop',
    component: () => import('@/components/user/shop/Shop.vue'),
  },
  {
    path: "/shop/item/:itemNo",
    name: 'Item',
    component: () => import('@/components/user/shop/Item.vue'),
  },
  // {
  //   path: '/cart',
  //   name: 'Cart',
  //   component: () => import('@/components/user/shop/Cart.vue'),
  // },
  {
    path: '/order',
    name: 'Order',
    component: () => import('@/components/user/shop/Order.vue'),
  },
  {
    path: '/admin/:',
    name: 'AdminPage',
    component: () => import('@/components/admin/AdminPage.vue'),
    children: [
      {
        path: "/adminList",
        component: () => import('@/components/admin/AdminList.vue'),
      },
      {
        path: "itemAdd",
        component: () => import('@/components/admin/ItemAdd.vue'),
      },
      {
        path: "/adminQna",
        component: () => import('@/components/admin/AdminQna.vue'),
      },
      {
        path: "/userList",
        component: () => import('@/components/admin/UserList.vue'),
      },
      {
        path: "/memberDetail/:email",
        name: 'memberDetail',
        component: () => import('@/components/admin/MemberDetail.vue'),
      },
    ]
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
  history: createWebHashHistory(),
  routes,
});