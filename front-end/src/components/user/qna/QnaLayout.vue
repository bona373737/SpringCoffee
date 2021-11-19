<template>
    <div class="outer">
        <div class="tab-bar">
            <div class="tab-shop py-3">
                <span class="tab-title">Q & A</span>            
            </div>
        </div>
        <router-view></router-view>

    </div>
</template>

<script>
export default {
  name:'qnaLayout',
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


.filter{
  margin-right:10%;
  text-align: right;
}

</style>