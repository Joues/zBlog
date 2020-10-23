<template>
  <div class="home">
    <div class="header">
      <blogheader></blogheader>
    </div>
    <div class="container">
      <div class="body">
        <blogbody></blogbody>
      </div>
      <div class="footer">
        <blogfooter></blogfooter>
      </div>
    </div>
  </div>
</template>

<script>
import blogheader from '@/components/blogheader'
import blogbody from '@/components/blogbody'
import blogfooter from '@/components/blogfooter'

export default {
  name: "home",
  components: {
    blogheader,
    blogbody,
    blogfooter
  },
  data() {
    return {};
  }
};
</script>

<style>
.home {
  width: 100%;
  height: 100%;
  /* 设置overflow: hidden;可以防止div溢出 */
  overflow: hidden;
  /* 设置position: fixed;可以铺满屏幕 */
  position: fixed;
  display: flex;
  flex-direction: column;
}

.header {
  background-color: #ffffff;
  color: #333;
  height: 64px;
  /* line-height: 80px; */
  /* display: flex; */
  /* align-items: center; */
}

.container {
  /*此处记得修改！！！！*/
  /* margin-bottom: 60px; */
}

.body {
  width: 100%;
  overflow: hidden;
}

.footer {
  clear: both;
  color: #8c9398;
  background: darkgray;
  /* padding: 50px 0; */
  text-align: center;
  /* text-transform: uppercase; */
  height: 180px;
}

/*定义滚动条高宽及背景 高宽分别对应横竖滚动条的尺寸*/
::-webkit-scrollbar {
  width: 5px; /*滚动条宽度*/
  height: 5px; /*滚动条高度*/
  /* background-color: white; */
  background-color: dimgrey;
}

/*定义滑块 内阴影+圆角*/
::-webkit-scrollbar-thumb {
  border-radius: 50%;
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  background-color: rgba(221, 222, 224); /*滚动条的背景颜色*/
}
</style>