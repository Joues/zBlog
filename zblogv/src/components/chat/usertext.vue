<template>
    <div id="uesrtext">
        <span style="margin-left: 10px;font-size: 16px;color: #969696;">请输入信息……</span>
        <el-button 
            size="mini" 
            style="position: relative; left: 65%; width: 68px;margin: 3px 0px;" 
            :disabled="this.currentSession === null" 
            @click="addMessage(btn)">发送(S)</el-button>
        <textarea :disabled="this.currentSession === null" placeholder="按 Ctrl + Enter 发送" v-model="content" v-on:keyup="addMessage"></textarea>
    </div>
</template>

<script>
    import {mapState} from 'vuex'

    export default {
        name: 'uesrtext',
        data() {
            return {
                content: '',
                btn: "btn",
                username: null,
            }
        },
        computed: mapState([
            'sessions',
            'currentSession'    
        ]),
        methods: {
            addMessage(e) {

                if (("btn" === e && this.content.length) || (e.ctrlKey && e.keyCode === 13 && this.content.length)) {
                    let msgObj = new Object();
                    msgObj.from = this.$store.state.username;
                    msgObj.fromNickname = this.$store.state.nickname;
                    msgObj.to = this.currentSession.username;
                    msgObj.content = this.content;
                    this.$store.state.stomp.send('/ws/queue', {}, JSON.stringify(msgObj));
                    this.$store.commit('addMessage', msgObj);
                    this.content = '';
                }
            }
        }
    }
</script>

<style lang="scss" scoped>
    #uesrtext {
        position: absolute;
        bottom: 0;
        right: 0;
        width: 100%;
        height: 30%;
        border-top: solid 1px #DDD;
        background: white;

        > textarea {
            padding: 10px;
            width: 100%;
            height: 75%;
            border: none;
            outline: none;
            box-shadow: inset 0 1px 3px hsla(0, 0%, 7%, .1), 0 0 0 1px hsla(0, 0%, 7%, .1);
            // border-top: solid 1px #DDD;
        }
    }
</style>
