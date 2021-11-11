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
    fetchQnaFilterList(context,category,isAnswered){
      axios.get(`/v3/list/${category}`,
          {params:{
          category:category,
          isAnswered:isAnswered,
          }}
      )
          .then(response =>{
            context.commit('setQnaBoardList', response.data);
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

    // fetchQna(paramObj){
    //     const category = paramObj.category;
    //     const isAnswered = paramObj.isAnswered;
    //
    //     /*
    //     /list?cat=카테고리&isAns=상태
    //     /list/카테고리/상태
    //     */
    //     const url = '/list';
    //     let param = {};
    //
    //     if(category){
    //         param.category = category;
    //     }
    //     else if(isAnswered){
    //         param.isAnswered = isAnswered;
    //     }
    //     else if(category && isAnswered){
    //         param.category = category;
    //         param.isAnswered = isAnswered;
    //     }
    //
    //     axios.get(url, param)
    //         .then(response =>{
    //             context.commit('setQnaBoardList', response.data)
    //         })
    // }
  },
})

export default store;