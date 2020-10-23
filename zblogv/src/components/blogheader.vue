<template>
  <div class="blogheader">
    <div class="all">
      <div class="logo">
        <img :src="defaultLogo" alt="">
      </div>
      <div class="menu">
        <div class="navbar">
          <ul>
            <li>博客</li>
            <li>留言板</li>
<!--            <img :src="randCodeImage" />-->
          </ul>
        </div>
        <div class="search">搜索</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "blogheader",
  data() {
    return {
      defaultLogo: require("../assets/images/default_logo.png"),
      randCodeImage:'data:image/jpg;base64,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',
    }
  }
};
</script>

<style>
.blogheader {
  width: 100%;
  height: 100%;
  /* margin: 10px 20px; */
  transition: opacity 0.3s ease-out 0s, transform 0.3s ease-out 0s;
  opacity: 1;
  transform: translateY(0px);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
}

.all {
  margin: 0 40px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.logo img {
  margin-right: 45px;
  height: 64px;
  width: 200px;
}

.menu {
  min-width: 600px;
  height: 64px;
  flex-grow: 1;
  flex-shrink: 0;
  overflow-x: auto;
  align-items: stretch;
  display: flex;
  padding: 0;
  flex-shrink: 0;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.navbar {
}

.navbar ul {
  list-style-type: none;
  float: left;
}

.navbar li {
  float: left;
  margin: 0 10px;
  line-height: 64px;
  color: #4a4a4a;

}

.search {
  width: 210px;
  padding: 12px 15px;
  line-height: 40px;
  color: #4a4a4a;
}
</style>
