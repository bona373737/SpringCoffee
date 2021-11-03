<template>
    <div>
        <div class="tab-bar">
            <div class="tab-shop py-3" style="width:100%;">
                <span class="tab-title">Q & A</span>            
            </div>
        </div>
        <div class="py-2"><br></div>
        <span class="info"><i class="bi bi-question-circle-fill"></i></span><br>
        <span style="font-size: 11pt;">궁금한 것이 있으면 언제든 문의해주세요!</span>

        <router-view></router-view>

    </div>
</template>

<script>
export default {
  name:'NoticeForm',
};
</script>


<style scoped>
.tab-bar {
  background-image: url('../../../assets/background.jpg');
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

.info {
    font-size: 26pt;
}

.info:hover {
    color: green;
    font-size: 26pt;
    transition: 0.3s;
}

</style>