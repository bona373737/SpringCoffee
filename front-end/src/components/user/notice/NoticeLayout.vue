<template>
    <div class="outer">
        <div class="tab-bar">
            <div class="tab-shop py-3">
                <span class="tab-title">공지사항</span>            
            </div>
        </div>

        <router-view></router-view>

    </div>
</template>

<script>
export default {
  name:'NoticeForm',
};
</script>


<style scoped>
.outer {
  background-color: #f7f7f7;
}
.tab-bar {
  background-image: url('../../../assets/background.jpg');
  background-repeat : no-repeat;
  background-size : cover;
  position: relative;
}

.tab-shop {
  background-color: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 150px;
  display: table;
  }

.tab-title {
    font-weight: bold;
    font-size: 25pt;
    color: white;
    display: table-cell;
    vertical-align: middle;
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