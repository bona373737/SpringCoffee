import { createStore } from 'vuex'
import axios from "axios";


let store = createStore({
  state(){  // 데이터보관하고 싶으면 여기에 기재
    return {
      email: "user95@springCoffee.com",
      cartNo: '',
      itemList: [],
      itemDetail: {},
      cartList: [],
      cartDetail: {},
      noticeBoardList: [],
      noticeBoardDetail: {},
      qnaBoardList: [],
      qnaBoardDetail : {},
    }
  },
  mutations: { // 변경하길 원하는 것들은 이곳에다가 기재한다
    setMember(state, email) {
      state.email = email;
    },
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
        })
    },
    fetchItemCategory(context,category) {
        axios.get(`/v2/list/${category}`,{params:{category:category}})
            .then(response => {
                context.commit('setItem', response.data);
            })
    },
    fetchItemDetail(context, itemNo){
      axios.get(`/v2/list/${itemNo}`)      // axios dynamic URL,
        .then(response =>{
          context.commit('setItemDetail', response.data);
        })
    },
    fetchCart(context, email) {
      axios.get(`/v4/cart/${email}`)
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
    }
  },
})

export default store;