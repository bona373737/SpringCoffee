<template>
    <div class="outterDiv">
        <h4 class="notice-head">공지사항</h4>
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
    
</template>

<script>
export default {
    name:'NoticeForm'
  
};
</script>

<style scoped>
.outterDiv{
    width: 60%;
    margin: auto;
}
.outterDiv .notice-head{
    margin-right: 80%;
}

</style>