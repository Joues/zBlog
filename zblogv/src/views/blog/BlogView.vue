<template>
  <div class="me-view-body" v-title :data-title="title">
    <el-container class="me-view-container">
      <!-- <el-aside class="me-area">
        <ul class="me-operation-list">
          <li class="me-operation-item">
            <el-button type="primary" size="mini" icon="el-icon-edit"></el-button>
          </li>
        </ul>
      </el-aside> -->
      <el-main>

        <div class="me-view-card">
          <h1 class="me-view-title">{{article.title}}</h1>
          <div class="me-view-author">
            <!-- <a class=""> -->
              <!-- <img class="me-view-picture" :src="article.author.avatar"></img> -->
              <!-- <span>周逸航</span> -->
              <div class="me-view-photo">
                {{ article.author.avatar === null ? article.author.nickname[0] : article.author.avatar }}
              </div>
            <!-- </a> -->
            <div class="me-view-info">
              <span>{{article.author.username}}</span>
              <div class="me-view-meta">
                <span>{{article.createDate | format}}</span>
                <span>阅读   {{article.viewCounts}}</span>
                <span>评论   {{article.commentCounts}}</span>
              </div>

            </div>
            <el-button
              v-if="this.article.author.id == this.$store.state.id"
              @click="editArticle()"
              style="position: relative; left: 85%;"
              size="mini"
              round
              icon="el-icon-edit">编辑</el-button>
          </div>
          <div class="me-view-content">
            <markdown-editor :editor=article.editor></markdown-editor>
          </div>

          <div class="me-view-end">
            <el-alert
              title="文章End..."
              type="success"
              center
              :closable="false">
            </el-alert>
          </div>

          <div class="me-view-tag">
            标签：
            <!--<el-tag v-for="t in article.tags" :key="t.id" class="me-view-tag-item" size="mini" type="success">{{t.tagname}}</el-tag>-->
            <el-button @click="tagOrCategory('tag', t.id)" size="mini" type="primary" v-for="t in article.tags" :key="t.id" round plain>{{t.tagname}}</el-button>
          </div>

          <div class="me-view-tag">
            文章分类：
            <!--<span style="font-weight: 600">{{article.category.categoryname}}</span>-->
            <el-button @click="tagOrCategory('category', article.category.id)" size="mini" type="primary" round plain style="position: absolute;vertical-align：middle;margin: 0 auto;">{{article.category.categoryname}}</el-button>
          </div>

          <div class="me-view-comment">
            <div class="me-view-comment-write">
              <el-row :gutter="20">
                <el-col :span="2">
                  <!-- <a class="">
                    <img class="me-view-picture" :src="avatar"></img>
                  </a> -->
                  <!-- 评论人为当前登录用户，若当前登录用户为空则显示一个固定的头像 -->
                  <div class="me-view-photo">
                    <!-- {{ this.$store.state.avatar === null ? this.$store.state.nickname[0] : this.$store.state.avatar }} -->
                    <img class="me-view-picture" :src="this.$store.state.avatar === null ? this.$store.state.nickname[0] : this.$store.state.avatar"></img>
                  </div>
                </el-col>
                <el-col :span="22">
                  <el-input
                    type="textarea"
                    :autosize="{ minRows: 2}"
                    placeholder="你的评论..."
                    class="me-view-comment-text"
                    v-model="comment.content"
                    resize="none">
                  </el-input>
                </el-col>
              </el-row>

              <el-row :gutter="20" >
                <el-col :span="2" :offset="22">
                  <el-button type="primary" size="mini" 
                    @click="publishComment()" 
                    style="margin: 15px 35px 0 0;" 
                    v-if="this.article.author.id == this.$store.state.id"
                  >评论</el-button>
                </el-col>
              </el-row>
            </div>

            <div class="me-view-comment-title">
              <span>{{article.commentCounts}} 条评论</span>
            </div>

            <commment-item
              v-for="(c,index) in comments"
              :comment="c"
              :articleId="article.id"
              :index="index"
              :rootCommentCounts="comments.length"
              @commentCountsIncrement="commentCountsIncrement"
              :key="c.id">
            </commment-item>

          </div>

        </div>
      </el-main>

    </el-container>
  </div>
</template>

<script>
  import MarkdownEditor from '@/components/markdown/MarkdownEditor'
  import CommmentItem from '@/components/comment/CommentItem'
  import {viewArticle} from '@/api/article'
  import {getCommentsByArticle, publishComment} from '@/api/comment'

  import default_avatar from '@/assets/img/default_avatar.png'

  export default {
    name: 'BlogView',
    created() {
      this.article.author.id
      console.log("states.id: "+ this.$store.state.id);
      console.log("states.nickname: "+ this.$store.state.nickname);
      this.getArticle()
    },
    watch: {
      '$route': 'getArticle'
    },
    data() {
      return {
        article: {
          id: 0,
          title: '',
          commentCounts: 0,
          viewCounts: 0,
          summary: '',
          email: '',
          author: {},
          tags: [],
          category:{},
          createDate: '',
          editor: {
            value: '',
            toolbarsFlag: false,
            subfield: false,
            defaultOpen: 'preview'
          }
        },
        comments: [],
        comment: {
          // article: {},
          blogId: 0,
          email: '',
          content: ''
        }
      }
    },
    computed: {
      avatar() {
        let avatar = this.$store.state.avatar
        console.log("this.$storm.state.avatar + " + avatar);
        if (null !== avatar) {
          return avatar
        }
        return default_avatar
      },
      title() {
        return `${this.article.title} - 文章 - ityihang`
      }
    },
    methods: {
      tagOrCategory(type, id) {
        this.$router.push({path: `/${type}/${id}`})
      },
      editArticle() {
        this.$router.push({path: `/write/${this.article.id}`})
      },
      getArticle() {
        let that = this
        viewArticle(that.$route.params.id).then(res => {
          Object.assign(that.article, res.data)
          that.article.id = that.$route.params.id
          that.article.title = res.data.blog.title
          that.article.author.avatar = res.data.author.avatar
          that.article.author.username = res.data.author.nickname
          that.article.author.nickname = res.data.author.nickname
          that.article.editor.value = res.data.body.content
          that.article.viewCounts = res.data.blog.readCount
          that.article.commentCounts = res.data.blog.commentCount
          that.getCommentsByArticle(res.data.body.id)
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '文章加载失败', showClose: true})
          }
        })
      },
      publishComment() {
        let that = this
        if (!that.comment.content) {
          return;
        }
        that.comment.blogId = that.article.id
        that.comment.email = that.article.email

        publishComment(that.comment).then(data => {
          that.$message({type: 'success', message: '评论成功', showClose: true})
          that.comments.unshift(data.data)
          that.commentCountsIncrement()
          that.comment.content = ''
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '评论失败', showClose: true})
          }
        })
      },
      getCommentsByArticle(id) {
        let that = this
        getCommentsByArticle(id).then(data => {
          that.comments = data.data
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '评论加载失败', showClose: true})
          }
        })
      },
      commentCountsIncrement() {
        this.article.commentCounts += 1
      }
    },
    components: {
      'markdown-editor': MarkdownEditor,
      CommmentItem
    },
    //组件内的守卫 调整body的背景色
    beforeRouteEnter(to, from, next) {
      window.document.body.style.backgroundColor = '#fff';
      next();
    },
    beforeRouteLeave(to, from, next) {
      window.document.body.style.backgroundColor = '#f5f5f5';
      next();
    }
  }
</script>

<style>
  .me-view-body {
    margin: 100px auto 140px;
  }

  .me-view-container {
    width: 1150px;
  }

  .el-main {
    overflow: hidden;
  }

  .me-view-title {
    font-size: 34px;
    font-weight: 700;
    line-height: 1.3;
  }

  .me-view-author {
    margin: 20px 0;
    margin-top: 30px;
    vertical-align: middle;
  }

  .me-view-picture {
    width: 35px;
    height: 35px;
    border: 0 none;
    /* border: 1px solid #ddd; */
    border-radius: 50%;
    vertical-align: middle;
    /* background-color: #0abde3; */
  }

  .me-view-photo {
    /* top: 50%; */
    display: inline-block;
    left: -40px;
    transform: translateY(-40%);
    width: 35px;
    height: 35px;
    border-radius: 50%;
    background: #0abde3;
    font-size: 18px;
    font-family: PingFangTC-Medium, PingFangTC;
    font-weight: 500;
    color: #FFFFFF;
    line-height: 34px;
    text-align: center;
    cursor: pointer;
    margin-top: 20px;
  }

  .me-view-info {
    position: absolute;
    display: inline-block;
    vertical-align: middle;
    margin-left: 8px;
  }

  .me-view-info span {
    font-size: 14 px;
  }

  .me-view-meta {
    font-size: 12px;
    color: #969696;
  }

  .me-view-content {
    width: 100%;
    height: 100%; 
    padding: 8px 25px 15px 25px;
    /* margin-left: 15px; */
    overflow-y: auto;
    box-sizing: border-box;
    overflow-x: hidden; 
  }  

  .me-view-content p {
    padding: 8px 0px 15px 25px
  }

  .me-view-end {
    margin-top: 20px;
  }

  .me-view-tag {
    margin-top: 20px;
    padding-left: 6px;
    border-left: 4px solid #c5cac3;
  }

  .me-view-tag-item {
    margin: 0 4px;
  }

  .me-view-comment {
    margin-top: 60px;
  }

  .me-view-comment-title {
    font-weight: 600;
    border-bottom: 1px solid #f0f0f0;
    padding-bottom: 20px;
  }

  .me-view-comment-write {
    margin-top: 20px;
    margin-bottom: 25px;
  }

  .me-view-comment-text {
    font-size: 16px;
  }

  .v-show-content {
    padding: 8px 25px 15px 0px !important;
  }

  .v-note-wrapper .v-note-panel {
    box-shadow: none !important;
  }

  .v-note-wrapper .v-note-panel .v-note-show .v-show-content, .v-note-wrapper .v-note-panel .v-note-show .v-show-content-html {
    background: #fff !important;
  }


</style>
