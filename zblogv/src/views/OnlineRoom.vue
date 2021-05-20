<template>

  <div class="me-message" v-title :data-title="title">
    <el-container class="">
      <base-header ></base-header>
      <div id="app">
        <div class="sidebar">
              <card></card>
              <list></list>
          </div>
          <div class="main">
              <message></message>
              <usertext></usertext>
          </div>
      </div>
    </el-container>
  </div>
</template>

<script>
 import BaseHeader from '@/views/BaseHeader'
import card from '../components/chat/card.vue'
    import list from '../components/chat/list.vue'
    import message from '../components/chat/message.vue'
    import usertext from '../components/chat/usertext.vue'
  export default {
    name: 'OnlineRoom',
    data() {
      return {
      }
    },
    computed: {
      title (){
        return '聊天室 - ityihang'
      }
    },
    mounted:function() {
        this.$store.dispatch('initData');
        // this.$store.dispatch('connect');
    },
    components:{
        card,
        list,
        message,
        usertext,
        'base-header': BaseHeader,
    },
  }
</script>

<style lang="scss" scoped>
    #app {
        margin: 100px auto;
        width: 800px;
        height: 600px;
        overflow: hidden;
        border-radius: 10px;
        .sidebar, .main {
            height: 100%;
        }
        .sidebar {
            float: left;
            color: #f4f4f4;
            background-color: #2e3238;
            width: 200px;
        }
        .main {
            position: relative;
            overflow: hidden;
            background-color: #eee;
        }
    }

    .el-header {
    position: fixed;
    z-index: 1024;
    min-width: 100%;
    box-shadow: 0 2px 3px hsla(0, 0%, 7%, .1), 0 0 0 1px hsla(0, 0%, 7%, .1);
  }
</style>