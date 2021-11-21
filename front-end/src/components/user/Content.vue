<template>
  <div class="outterdiv">
    <div class="content-list">
      <img class="bg2" src="../.././assets/background2.jpg" alt="">
      <div class="carouseldiv">
        <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
          <div class="carousel-indicators">
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-label="슬라이드 1" aria-current="true"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="슬라이드 2" class=""></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="슬라이드 3" class=""></button>
          </div>

          <div class="carousel-inner">
            <div class="carousel-item active">
              <div class="container-fluid content-wrap">
                <div class="text-white">
                  <img  src="../.././assets/content3.jpg" alt="">
                </div>
              </div>
            </div>

            <div class="carousel-item">
              <div class="container-fluid content-wrap">
                <div class="text-white">
                  <img  src="../.././assets/content2.jpg" alt="">

                </div>
              </div>
            </div>
            
            <div class="carousel-item">
              <div class="container-fluid content-wrap">
                <div class="text-white">
                  <img  src="../.././assets/content1.jpg" alt="">
                </div>
              </div>
            </div>
          </div>

          <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">이전</font></font></span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">다음과 같은</font></font></span>
          </button>
        </div>
      </div>

      <div class="align-items-md-stretch">
        <div class="contents">
          <div class="content1">
            <h4>Best 상품</h4><hr style="width: 20%; color: #663C2A;">
          </div>
          <div class="content2">
              <h4>NOTICE</h4><hr style="width: 20%; color: #663C2A;">
              <div class="notice-list">
                <table class="table table-bordered table-condensed">
                  <tbody>
                  <tr class="tbody-th1" v-for="board in this.$store.state.noticeBoardList.dtoList" v-bind:key="board.boardNo"
                      @click="goNoticeDetail(board.boardNo)">
                    <th>{{ board.title }}</th>
                  </tr>
                  </tbody>
                </table>
              </div>
          </div>
          <div class="content3">
              <h4>Q&A</h4><hr style="width: 20%; color: #663C2A;">
              <div class="notice-list">
                  <table class="table table-bordered table-condensed">
                    <tbody>
                    <tr class="tbody-th1" v-for="qnaBoard in this.$store.state.qnaBoardList.dtoList" v-bind:key="qnaBoard.qnaBoardNo"
                        @click="goQnaDetail(qnaBoard.qnaBoardNo)">
                      <th>{{ qnaBoard.title }}</th>
                    </tr>
                    </tbody>
                  </table>
                </div>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  name: 'Content',
  created() {
    this.$store.dispatch('fetchNoticeBoardListMain');
    this.$store.dispatch('fetchQnaBoardListMain');
  },
  methods:{
    goQnaDetail(qnaBoardNo) {
      this.$router.push({
        name: 'qnaDetail',
        params: {qnaBoardNo: qnaBoardNo}
      })
    },
    goNoticeDetail(boardNo) {
      this.$router.push({
        name: 'noticeDetail',
        params: { boardNo: boardNo }
      })
    },
  }
}
</script>

<style scoped>
.carouseldiv {
  background-image: url('../.././assets/background.jpg');
  background-repeat : no-repeat;
  background-size : cover;
  position: relative;
}

.carousel-item img {
  margin-left: -15px;

  width: 110%;
  height: 100%;
}

.container-fluid {
  
}

.content-wrap {
  /* background-color: rgba(0, 0, 0, 0.5); */
}

.content-list .bg2 {
  left: 0;
  width: 100%;
  height: 100%;
  position: fixed;
  z-index: -1;
}

.contents {
  background-color: #f7f7f7;
}

.content1, .content2, .content3 {
  width: 80%;
  height: 300px;
  /* border: 2px solid black; */
  background-color: white;
  margin: auto;
  padding: 30px;
  text-align: left;
}

.content1, .content2 {
  border-bottom: 1px solid #666;
}

.content1 h4, .content2 h4, .content3 h4 {
  font-weight: bold;
}

.content1 {
  /* background-color: blanchedalmond; */
}

.content2 .notice-list, .content3 .notice-list{
  /* width: 70%; */
  margin: 5px 10px;
  padding: 0px 50px;
}

.content2 .tbody-th1, .content3 .tbody-th1{
  border-bottom: 1px solid  #ddd;
  border-left: 1px solid white;
  border-right: 1px solid white;
}

.content2 .tbody-th1:hover, .content .tbody-th1:hover, .content3 .tbody-th1:hover {
  cursor: pointer;
  color: #A36043;
  transition: 0.3s;
}

.content2 th, .content3 th{
  border-left: 1px solid white;
  border-right: 1px solid white;
  vertical-align: middle;
  padding-left: 30px;
}


</style> 