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
      QnaFilterList : []
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
    setQnaFilterList(state,payload){
      state.QnaFilterList = payload
    }
  },
  actions: {
    fetchNoticeBoardList(context,page){
      /*
      // GET , POST 정의
      // axios 작성하면 어떻게 되는지

      let param = {};
      if(category){
        param.category = 변수값;   // /list?category=변수값
      }
      if(isAnswered){
        param.isAnswered = 변수값; // /list?category=변수값&isAnswered=변수값
      }

      /list -> category만 선택
      params: data :  param
       */
      axios.get(`/v1/list`,{params:{
          page:page,
      }})
      .then(response => {
        context.commit('setNoticeBoardList', response.data);
      })

    },
    fetchNoticeBoardDetail(context, boardNo){
      axios.get(`/v1/${boardNo}`)
          .then(response =>{
            context.commit('setNoticeBoardDetail', response.data);
          })
    },
    fetchQnaBoardList(context,category,isAnswered){
      axios.get('/v3/list',{params:{
          category:category,
          isAnswered:isAnswered, a
        }})
          .then(response =>{
            context.commit('setQnaBoardList', response.data.dtoList);
          })
    },
    fetchQnaBoardDetail(context,qnaBoardNo){
      console.log(qnaBoardNo)
      axios.get(`/v3/${qnaBoardNo}`)
          .then(response => {
            context.commit('setQnaBoardDetail',response.data)
          })
    },
    fetchQnaFilterList(context,category,isAnswered){
      axios.get(`/list/${category}/${isAnswered}`)
          .then(response =>{
            context.commit('setQnaFilterList',response.data)
          })
    }

  },
})

export default store;