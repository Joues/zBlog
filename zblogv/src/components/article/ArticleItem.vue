<template>
  <el-card class="me-area" :body-style="{ padding: '16px' }">
    <div class="me-article-header">

      <a @click="view(id)" class="me-article-title">{{title}}</a>
      <el-button v-if="isTop" class="me-article-icon" type="text">置顶</el-button>
      <span v-if="isEssence" style="color: red;font-size:14px">【热门】</span>
      <span class="me-pull-right me-article-count">
	    	<i class="me-icon-comment"></i>&nbsp;{{commentCount}}
	    </span>
      <span class="me-pull-right me-article-count">
	    	<i class="el-icon-view"></i>&nbsp;{{readCount}}
	    </span>
    </div>

    <div class="me-artile-description">
      {{summary}}
    </div>
    <div class="me-article-footer">
	  	<span class="me-article-author">
	    	<!-- <i class="me-icon-author"></i>&nbsp;{{author.username}} -->
	    	<i class="me-icon-author"></i>&nbsp;{{realname}}
	    </span>

      <el-tag v-for="t in tags" :key="t.id" size="mini" type="success">{{t.name}}</el-tag>

      <span class="me-pull-right me-article-count">
	    	<i class="el-icon-time"></i>&nbsp;{{createTime | format}}
	    </span>

    </div>
  </el-card>
</template>

<script>
  import { formatTime } from "../../utils/time";

  export default {
    name: 'ArticleItem',
    props: {
      id: String,
      isTop: Boolean,
      isEssence: Boolean,
      title: String,
      commentCount: Number,
      readCount: Number,
      summary: String,
      createBy: String,
      realname: String,
      tags: Array,
      createTime: String
    },
    data() {
      return {}
    },
    methods: {
      view(id) {
        this.$router.push({path: `/view/${id}`})
      }
    }
  }
</script>

<style scoped>

  .me-article-header {
    /*padding: 10px 18px;*/
    padding-bottom: 10px;
  }

  .me-article-title {
    font-weight: 600;
  }

  .me-article-icon {
    padding: 3px 8px;
  }

  .me-article-count {
    color: #a6a6a6;
    padding-left: 14px;
    font-size: 13px;
  }

  .me-pull-right {
    float: right;
  }

  .me-artile-description {
    font-size: 13px;
    line-height: 24px;
    margin-bottom: 10px;
  }

  .me-article-author {
    color: #a6a6a6;
    padding-right: 18px;
    font-size: 13px;
  }

  .el-tag {
    margin-left: 6px;
  }

</style>
