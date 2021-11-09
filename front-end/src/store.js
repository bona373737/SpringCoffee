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
    fetchNoticeBoardDetail(context, boardNo){
      axios.get(`/v1/${boardNo}`)
          .then(response =>{
            context.commit('setNoticeBoardDetail', response.data);
          })
    },
    fetchQnaFilterList(context,category,isAnswered){
      axios.get(`/v3/list/${category}`,
          {params:{
          category:category,
          isAnswered:isAnswered,
          }}
      )
          .then(response =>{
            context.commit('setQnaBoardList', response.data.dtoList);
          })
    },
    fetchQnaBoardList(context,){
      axios.get('/v3/list')
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
  },
})

export default store;