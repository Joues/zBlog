<template>
  <el-header class="me-area">
    <el-row class="me-header">

      <el-col :span="3" class="me-header-left">
        <router-link to="/" class="me-title">
          <img src="../assets/img/banner.png"/>
        </router-link>
      </el-col>

      <el-col v-if="!simple" :span="14">
        <el-menu :router=true menu-trigger="click" active-text-color="#0abde3" :default-active="activeIndex"
                 mode="horizontal">
          <el-menu-item index="/">首页</el-menu-item>
          <el-menu-item index="/category/all">文章分类</el-menu-item>
          <el-menu-item index="/tag/all">标签</el-menu-item>
          <el-menu-item index="/archives">文章归档</el-menu-item>
          <el-menu-item index="/OnlineRoom">聊天室</el-menu-item>
          <el-menu-item index="/log">日志</el-menu-item>
          <el-submenu index="/">
            <template slot="title">我的工作台</template>
            <!-- <el-menu-item index="/"><a href="https://boot.ityihang.cn" target="_blank">开发平台</a></el-menu-item> -->
            <el-menu-item index="/"><a href="https://boot.ityihang.cn" target="_blank">开发平台</a></el-menu-item>
          </el-submenu>
        </el-menu>
      </el-col>

      <el-col v-if="!simple" :span="3" >
        <el-menu :router=true menu-trigger="click" active-text-color="#0abde3" :default-active="activeIndex"
                 mode="horizontal">
            <el-menu-item index="/write"><i class="el-icon-edit"></i>写文章</el-menu-item>
        </el-menu>
      </el-col>

      <template v-else>
        <slot></slot>
      </template>

      <el-col :span="4">
         <el-menu :router=true menu-trigger="click" mode="horizontal" active-text-color="#0abde3">

          <template v-if="!user.token">
            <el-menu-item index="/login">
              <el-button type="text">登录</el-button>
            </el-menu-item>
            <el-menu-item index="/register">
              <el-button type="text">注册</el-button>
            </el-menu-item>
          </template>

          <template v-else>
            <el-submenu index>
              <template slot="title">
                <!-- <img class="me-header-picture" :src="user.avatar"/> -->欢迎您，{{user.nickname}}
              </template>
              <el-menu-item index @click="logout"><i class="el-icon-back"></i>退出</el-menu-item>
            </el-submenu>
          </template>
        </el-menu>
      </el-col>

    </el-row>
  </el-header>
</template>

<script>
  export default {
    name: 'BaseHeader',
    props: {
      activeIndex: String,
      simple: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {}
    },
    computed: {
      user() {
        let login = this.$store.state.username != null
        let avatar = this.$store.state.avatar
        let token = this.$store.state.token
        let username = this.$store.state.username
        let nickname = this.$store.state.nickname
        return {
          // login, avatar
          login,token,username,nickname
        }
      }
    },
    methods: {
      logout() {
        let that = this
        this.$store.dispatch('logout').then(response => {
          if(response.code === 0) {
            that.$message({message: response.data, type: 'success', showClose: true});
            this.$router.push({path: '/'})
            // location.reload
          } else {
            that.$message({message: response.msg, type: 'error', showClose: true});
          }
        }).catch((error) => {
          if (error !== 'error') {
            that.$message({message: error, type: 'error', showClose: true});
          }
        })
      }
    }
  }
</script>

<style>

  .el-header {
    position: fixed;
    z-index: 1024;
    min-width: 100%;
    box-shadow: 0 2px 3px hsla(0, 0%, 7%, .1), 0 0 0 1px hsla(0, 0%, 7%, .1);
  }

  .me-title {
    margin-top: 10px;
    font-size: 24px;
  }

  .me-header-left {
    margin-top: 10px;
  }

  .me-title img {
    max-height: 2.4rem;
    max-width: 100%;
  }

  .me-header-picture {
    width: 36px;
    height: 36px;
    border: 1px solid #ddd;
    border-radius: 50%;
    vertical-align: middle;
    background-color: #74b9ff;
  }
</style>
