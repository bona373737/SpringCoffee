import { createStore } from 'vuex'
import axios from "axios";


let store = createStore({
  state(){
    return{
      isShow: true,
      noticeBoardList: [],
      noticeBoardDetail: {},
      qnaBoardList: [],
      qnaBoardDetail : {},
      // QnaFilterList : []
    }
  },
  mutations: {
    checkShow(state) {
      state.isShow = !this.state.isShow;
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
        const category = paramObj.category;     // ''
        const isAnswered = paramObj.isAnswered; // ''

        let url = '/v3/list';
        let pageObj = {};   // {page:page}

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