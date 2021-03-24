<template>
  <el-card>
    <h1 class="me-author-name">YiHang</h1>
    <div class="me-author-description">
      <span><i class="el-icon-location-outline"></i> &nbsp;湖北·武汉</span>
      <span><i class="me-icon-job"></i> &nbsp;Java开发工程师</span>
    </div>
    <div class="me-author-tool">
      <i @click="showTool(qq)" :title="qq.title" class="me-icon-QQ"></i>
      <i @click="showTool(github)" :title="github.title" class="me-icon-github"></i>
    </div>
    <div class="me-author-message">
      <span>都说酒是陈的香</span>
    </div>
  </el-card>

</template>

<script>
  export default {
    name: 'CardMe',
    data() {
      return {
        qq: {title: 'QQ', message: '1987247505'},
        github: {
          title: 'github',
          message: '<a target="_blank" href="https://github.com/joues">https://github.com/joues</a>'
        }
      }
    },
    methods: {
      showTool(tool) {
        this.$message({
          duration: 0,
          showClose: true,
          dangerouslyUseHTMLString: true,
          message: '<strong>' + tool.message + '</strong>'
        });
      }
    }
  }
</script>

<style scoped>
  .me-author-name {
    text-align: center;
    font-size: 30px;
    border-bottom: 1px solid #0abde3;
    font-family:Verdana, Geneva, Tahoma, sans-serif
  }

  .me-author-description {
    padding: 8px 0;
  }

  .me-icon-job {
    padding-left: 16px;
  }

  .me-author-tool {
    text-align: center;
    padding-top: 10px;
  }

  .me-author-tool i {
    cursor: pointer;
    padding: 4px 10px;
    font-size: 30px;
  }

  .me-author-message {
    text-align: center;
  }

  .me-author-message span {
    text-align: center;
    font-size: 14px;
    color: #969696;
  }
</style>
