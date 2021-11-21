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
        props: true
        // props: (route) => ({query})
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
      {
        path: '/qnaReply',
        name: 'qnaReply',
        component: () => import('@/components/user/qna/QnaReply.vue')
      }
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
    component: () => import('@/components/user/mypage/MyPage.vue'),
    children: [
      {
        path: "profile",
        component: () => import('@/components/user/mypage/Profile.vue'),
      },
      {
        path: '/cart',
        name: 'Cart',
        component: () => import('@/components/user/shop/Cart.vue'),
      },
      {
        path: "/myQna",
        component: () => import('@/components/user/mypage/MyQna.vue'),
      },
      {
        path: "/myorder",
        component: () => import('@/components/user/mypage/MyOrder.vue'),
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
  {
    path: '/admin/:',
    redirect: '/userlist',
    name: 'AdminPage',
    component: () => import('@/components/admin/AdminPage.vue'),
    children: [
      {
        path: "/adminList",
        component: () => import('@/components/admin/AdminList.vue'),
      },
      {
        path: "/itemadd",
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
        path :'/adminOrder',
        component:() => import('@/components/admin/AdminOrder.vue')
      },
      {
        path :'/memberInfoUpdate/:email',
        name : 'memberInfoUpdate',
        component:() => import('@/components/admin/MemberInfoUpdate')
      }
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