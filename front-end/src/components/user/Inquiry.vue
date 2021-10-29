<template>
    <div>
        <div class="tab-bar">
            <div class="tab-shop py-3" style="width:100%;">
                <span class="tab-title">Q & A</span>            
            </div>
        </div>
        <div class="py-2"><br></div>
        <span class="qna"><i class="bi bi-question-circle-fill"></i></span><br>
        <span style="font-size: 11pt;">궁금한 것이 있다면 자유롭게 문의 해보세요!</span>
        
        <div class="outterDiv py-5">
            <div>
                <table class="table table-bordered table-condensed">
                    <colgroup>
                        <col width="10%" />
                        <col width="65%" />
                        <col width="15%" />
                        <col width="10%" />
                    </colgroup>
                    <tr>
                        <th>No.</th>
                        <th>제목</th>
                        <th>작성일</th>
                        <th>조회수</th>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name:'NoticeForm'
  
};
</script>

<style scoped>
.tab-bar {
  background-image: url('../.././assets/background.jpg');
  background-repeat : no-repeat;
  background-size : cover;
  position: relative;
}

.tab-shop {
  background-color: rgba(0, 0, 0, 0.5);
}

.tab-title {
    font-size: 22pt;
    color: white;
}

.outterDiv{
    width: 60%;
    margin: auto;
}
.outterDiv .notice-head{
    margin-right: 80%;
}

.qna {
    font-size: 26pt;
}

.qna:hover {
    color: green;
    font-size: 26pt;
    transition: 0.3s;
}

</style>