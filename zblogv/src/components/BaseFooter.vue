<template>
  <el-footer class="me-area">
    <div class="me-footer">
      <p>Designed by
        <strong>
          <router-link to="/" class="me-login-design-color">ityihang</router-link>
        </strong>
      </p>
      <!-- <h5 style="height: 15px;">Copyright © 2020 ityihang.cn All Right Reserved 鄂ICP备20004327号</h5> -->
    </div>
  </el-footer>

</template>

<script>

  export default {
    name: 'BaseFooter',
    data() {
      return {}
    },
    methods: {},
    mounted() {
    }
  }
</script>

<style>

  .el-footer {
    position: absolute;
    bottom: 0;
    left: 0;
    z-index: 1024;
    min-width: 100%;
    box-shadow: 0 -2px 3px hsla(0, 0%, 7%, .1), 0 0 0 1px hsla(0, 0%, 7%, .1);
  }

  .me-footer {
    text-align: center;
    line-height: 60px;
    font-family: 'Open Sans', sans-serif;
    font-size: 16px;

  }

  .me-login-design-color {
    color: #0abde3 !important;
  }

</style>
