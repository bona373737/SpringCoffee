import { createStore } from 'vuex'
import axios from 'axios';
import { router } from './router'

let store = createStore({
  state() {  // 데이터보관하고 싶으면 여기에 기재
    return {
      email: '',
      token: '',
      role: '',
      exp: 0,
      isLogin: false,
      cartNo: '',
      thumbnail: [],
      itemList: [],
      itemDetail: {},
      cartList: [],
      cartDetail: {},
      noticeBoardList: [],
      noticeBoardDetail: {},
      qnaBoardList: [],
      qnaBoardDetail : {},
      memberProfile: {},
      memberList:[],
      memberDetail:{},
    }
  },
  mutations: { // 변경하길 원하는 것들은 이곳에다가 기재한다
    setNoticeBoardList(state, payload) {
      state.noticeBoardList = payload;
    },
    setNoticeBoardDetail(state,payload){
      state.noticeBoardDetail = payload;
    },
    setQnaBoardList(state, payload){
      state.qnaBoardList = payload;
    },
    setQnaBoardDetail(state,payload){
      state.qnaBoardDetail = payload
    },
    setItem(state, item) {
      state.itemList = item;
    },
    setItemDetail(state, itemDetail){
      state.itemDetail = itemDetail;
    },
    setCart(state, cart) {
      state.cartList = cart;
    },
    setEmail(state, email) {
      state.email = email;
    },
    setToken(state, token) {
      state.token = token;
    },
    setMemberProfile(state, member) {
      state.memberProfile = member;
    },
    setMemberList(state,payload){
        state.memberList = payload;
    },
    setMemberDetail(state,payload){
        state.memberDetail = payload;
    }


  },
  actions: {
    fetchNoticeBoardList(context,page){
      axios.get(`/v1/list`,{params:{
          page:page,
      }})
      .then(response => {
        context.commit('setNoticeBoardList', response.data);
      })
    },
    fetchNoticeBoardSearch(context,keyword){
      axios.get('v1/search/keyword', {params:{keyword:keyword}})
          .then( response =>{
            context.commit('setNoticeBoardList',response.data)
          })
    },
    fetchNoticeBoardCategory(context, paramObj){
      const page = paramObj.page;
      const category = paramObj.category;

      let url = '/v1/list';
      let pageObj = {};

      console.log(`카테고리 조회`);
      
      url = '/v2/list/' + category; // 'v1/list/notice'
      pageObj = {
        page: page
      };
      
      axios.get(url, {params:pageObj})
      .then(response =>{
          context.commit('setNoticeBoardList', response.data)
      });
    },
    fetchNoticeBoardDetail(context, boardNo){
      axios.get(`/v1/${boardNo}`)
          .then(response =>{
            context.commit('setNoticeBoardDetail', response.data);
          })
    },
    fetchQnaBoardList(context,page){
      axios.get('/v3/list',{params:{page:page}})
          .then(response =>{
            context.commit('setQnaBoardList', response.data);
          })
    },
    fetchItem(context) {
      axios.get(`/v2/list`)
        .then(response => {
          context.commit('setItem', response.data);
          console.log('성공', response)
          // context.commit('setItem', response.data);
        }).catch(err => {
          console.log('error', err)
        })
    },
    fetchItemDetail(context, itemNo){
      axios.get(`/v2/${itemNo}`)      // axios dynamic URL,
        .then(response =>{
          context.commit('setItemDetail', response.data);
        })
    },
    fetchCart(context) {
      axios.get(`/v4/cart/${this.state.email}`)
        .then(response => {
          context.commit('setCart', response.data);
        })
    },
    // patchCart(context, cartItemNo, count) {
    //   axios.get(`/v4/${cartItemNo}/${count}`)
    //     .then(response => {
    //       context.commit('setCart', response.data);
    //     })
    // },
    fetchQnaBoardDetail(context,qnaBoardNo){
      axios.get(`/v3/${qnaBoardNo}`)
          .then(response => {
            context.commit('setQnaBoardDetail',response.data)
          })
    },
    fetchQnaBoardSearch(context,keyword){
      axios.get('v3/search/keyword',{params:{keyword:keyword}})
        .then(response =>{
            context.commit('setQnaBoardList', response.data)
        })
    },
    fetchQnaBoard(context, paramObj){
        const page = paramObj.page;
        const category = paramObj.category;
        const isAnswered = paramObj.isAnswered;

        let url = '/v3/list';
        let pageObj = {};

        if(category && isAnswered){
            url = '/v3/list' + '/' + category + '/' + isAnswered;
            pageObj = {
                page : page
            };
            // /v3/list/상품문의/답변상태?page=1
        }
        else if(category){
            console.log(`카테고리만 선택됨`);

            url = '/v3/list' + '/' + category;  // '/v3/list/상품문의';
            pageObj = {
                page : page
            };
            // /v3/list/상품문의?page=1
        }
        else if(isAnswered){
            url = '/v3/list/all' + '/' + isAnswered;
            pageObj = {
                page : page
            };
            // /v3/list/all/답변상태?page=1
        }
        else {
            url = '/v3/list';
            pageObj = {
                page:page
            }
            // /v3/list?page=1
        }
        // console.log(pageObj);
        axios.get(url, {params:pageObj})
        .then(response =>{
            context.commit('setQnaBoardList', response.data)
        });
    },
    fetchMyQna(context,email){
      axios.get('/v3/search/email',{params:{email:email}})
        .then(response =>{
            context.commit('setQnaBoardList', response.data);
        })
    },
    fetchAdminQna(context,isAnswered){
        axios.get(`/v3/list/all/${isAnswered}`,{params:{page:this.page}})
            .then(response =>{
                context.commit('setQnaBoardList', response.data)
            });
    },
    fetchLogin({dispatch}, loginForm){
      axios.post('/v5/login', loginForm)
        .then(response => {
          let token = response.data.token;
          localStorage.setItem('access_token', token);
          dispatch('getMemberInfo')
          console.log('로그인 성공')
          router.replace('/')
        })
        .catch(err => {
          alert('로그인에 실패하였습니다.', err)
        })
    },
    getMemberInfo() {
      if(localStorage.getItem('access_token')) {
        let token = localStorage.getItem("access_token");
        let base64Payload = token.split('.')[1];
        let payload = Buffer.from(base64Payload, 'base64');
        let result = JSON.parse(payload.toString())
        this.state.email=result.sub;
        this.state.role=result.roles[0];
        this.state.isLogin = true;
        this.state.exp=result.exp;
      }
    },
    fetchMemberProfile(context) {
      axios.get('v5/mypage', {params:
      {email: this.state.email}})
        .then(response => {
          context.commit('setMemberProfile', response.data)
        })
    },

    logout() {
      alert('로그아웃 완료');
      localStorage.removeItem("access_token")
      router.go('#')
    },

    getItemCategory(context, category) {
      axios.get(`v2/list/${category}`)
        .then(res => {
          context.commit("setItem", res.data.dtoList);
        }).catch(err => {
            console.log('failed', err)
        })
    },
    fetchUserList(context){
        axios.get('/v5/userlist')
            .then(response => {
                context.commit('setMemberList',response.data)
            })
    },
    fetchAdminList(context){
        axios.get('/v5/adminlist')
            .then(response => {
                context.commit('setMemberList',response.data)
            })
    },
    fetchMemberDetail(context,email)  {
        axios.get('/v5/mypage',{params:{email:email}})
            .then(response =>{
                context.commit('setMemberDetail',response.data)
            })
    }
  },
})

export default store;