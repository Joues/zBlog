import Vuex from 'vuex'
import Vue from 'vue'
import { Notification } from 'element-ui';
import { getToken, setToken, removeToken, getUserinfos, setUserinfos, removeUserinfos } from '@/request/token'
import { login, getUserInfo, logout, register } from '@/api/login'
import { getAction } from '@/api/manage'
import { ACCESS_TOKEN, USER_NAME, USER_INFO } from "@/store/mutation-types"
import '../utils/stomp'
import '../utils/sockjs'

Vue.use(Vuex);

const now = new Date();

const store = new Vuex.Store({
    state: {
        id: '',
        username: '',
        nickname: '',
        avatar: '',
        token: getToken(),
        Userinfo: getUserinfos(),
        routes: [],
        sessions: {},
        hrs: [],
        currentSession: null,
        currentHr: JSON.parse(window.sessionStorage.getItem("user")),
        filterKey: '',
        stomp: null,
        isDot: {}
    },
    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token;
        },
        SET_ACCOUNT: (state, username) => {
            state.username = username
        },
        SET_NAME: (state, nickname) => {
            state.nickname = nickname
        },
        SET_AVATAR: (state, avatar) => {
            state.avatar = avatar
        },
        SET_ID: (state, id) => {
            state.id = id
        },
        SET_USERINFO: (state, Userinfo) => {
            state.Userinfo = Userinfo
        },
        INIT_CURRENTHR(state, hr) {
            state.currentHr = hr;
        },
        initRoutes(state, data) {
            state.routes = data;
        },
        changeCurrentSession(state, currentSession) {
            Vue.set(state.isDot, state.username + '#' + currentSession.username, false);
            state.currentSession = currentSession;
        },
        addMessage(state, msg) {
            let mss = state.sessions[state.Userinfo.username + '#' + msg.to];
            if (!mss) {
                // state.sessions[state.currentHr.username+'#'+msg.to] = [];
                Vue.set(state.sessions, state.Userinfo.username + '#' + msg.to, []);
            }
            state.sessions[state.Userinfo.username + '#' + msg.to].push({
                content: msg.content,
                date: new Date(),
                self: !msg.notSelf
            })
        },
        INIT_DATA(state) {
            //浏览器本地的历史聊天记录可以在这里完成
            let data = localStorage.getItem('vue-chat-session');
            if (data) {
                state.sessions = JSON.parse(data);
            }
        },
        INIT_HR(state, data) {
            state.hrs = data;
        }
    },
    actions: {
        connect(context) {
            context.state.stomp = Stomp.over(new SockJS('/ws/ep'));
            context.state.stomp.connect({ "X-Access-Token": context.state.token }, success => {
                context.state.stomp.subscribe('/user/queue/chat', msg => {
                    let receiveMsg = JSON.parse(msg.body);
                    if (!context.state.currentSession || receiveMsg.from != context.state.currentSession.username) {
                        Notification.info({
                            title: '【' + receiveMsg.fromNickname + '】发来一条消息',
                            message: receiveMsg.content.length > 10 ? receiveMsg.content.substr(0, 10) : receiveMsg.content,
                            position: 'bottom-right'
                        })
                        Vue.set(context.state.isDot, context.state.Userinfo.username + '#' + receiveMsg.from, true);
                    }
                    receiveMsg.notSelf = true;
                    receiveMsg.to = receiveMsg.from;
                    context.commit('addMessage', receiveMsg);
                });
                context.state.stomp.subscribe('/topic/chat', msg => {
                    let receiveMsg = JSON.parse(msg.body);
                    if (!context.state.currentSession || receiveMsg.from != context.state.currentSession.username) {
                        Notification.info({
                            title: '【' + receiveMsg.fromNickname + '】发来一条消息',
                            message: receiveMsg.content.length > 10 ? receiveMsg.content.substr(0, 10) : receiveMsg.content,
                            position: 'bottom-right'
                        })
                        Vue.set(context.state.isDot, context.state.Userinfo.username + '#' + receiveMsg.from, true);
                    }
                    receiveMsg.notSelf = true;
                    receiveMsg.to = receiveMsg.from;
                    context.commit('addMessage', receiveMsg);
                })
            }, error => {
                console.log("errormsg: ", error);
            })
        },
        initData(context) {
            context.commit('INIT_DATA')
            getAction("/user/usr").then(resp => {
                if (resp) {
                    context.commit('INIT_HR', resp.data.records);
                }
            })
        },
        login({ commit }, user) {
            return new Promise((resolve, reject) => {
                login(user.username, user.password, user.captcha, user.checkKey).then(response => {
                    const data = response
                    Vue.ls.set(ACCESS_TOKEN, data.data.token, 7 * 24 * 60 * 60 * 1000)
                    commit('SET_TOKEN', data.data.token)
                    setToken(data.data['token'])
                    resolve(response)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        // 获取用户信息
        getUserInfo({ commit, state }) {
            let that = this
            return new Promise((resolve, reject) => {
                getUserInfo().then(data => {
                    if (data.data) {
                        const userInfo = JSON.stringify(data.data)
                        commit('SET_ACCOUNT', data.data.username)
                        commit('SET_NAME', data.data.realname)
                        commit('SET_AVATAR', data.data.avatar)
                        commit('SET_ID', data.data.id)
                        commit('SET_USERINFO', data.data)
                            // this.$store.commit('INIT_CURRENTHR', data.data);
                        setUserinfos(userInfo)
                        Vue.ls.set(USER_NAME, userInfo.username, 7 * 24 * 60 * 60 * 1000)
                        Vue.ls.set(USER_INFO, userInfo, 7 * 24 * 60 * 60 * 1000)
                    } else {
                        commit('SET_ACCOUNT', '')
                        commit('SET_NAME', '')
                        commit('SET_AVATAR', '')
                        commit('SET_ID', '')
                        commit('SET_USERINFO', '')
                        removeUserinfos()
                        removeToken()
                    }
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        // 退出
        logout({ commit, state }) {
            return new Promise((resolve, reject) => {
                logout().then(data => {

                    commit('SET_TOKEN', '')
                    commit('SET_ACCOUNT', '')
                    commit('SET_NAME', '')
                    commit('SET_AVATAR', '')
                    commit('SET_ID', '')
                    commit('SET_USERINFO', '')
                    removeUserinfos()
                    Vue.ls.remove(ACCESS_TOKEN)
                    Vue.ls.remove(USER_NAME)
                    Vue.ls.remove(USER_INFO)
                    removeToken()
                    resolve(data)

                }).catch(error => {
                    reject(error)
                })
            })
        },
        // 前端 登出
        fedLogOut({ commit }) {
            return new Promise(resolve => {
                commit('SET_TOKEN', '')
                commit('SET_ACCOUNT', '')
                commit('SET_NAME', '')
                commit('SET_AVATAR', '')
                commit('SET_ID', '')
                commit('SET_USERINFO', '')
                removeUserinfos()
                Vue.ls.remove(ACCESS_TOKEN)
                Vue.ls.remove(USER_NAME)
                Vue.ls.remove(USER_INFO)
                removeToken()
                resolve()
            }).catch(error => {
                reject(error)
            })
        },
        register({ commit }, user) {
            return new Promise((resolve, reject) => {
                register(user.username, user.nickname, user.password).then((data) => {
                    commit('SET_TOKEN', data.data['data'])
                    setToken(data.data['token'])
                    Vue.ls.set(ACCESS_TOKEN, data.data['token'], 7 * 24 * 60 * 60 * 1000)
                    Vue.ls.set(USER_NAME, userInfo.username, 7 * 24 * 60 * 60 * 1000)
                    Vue.ls.set(USER_INFO, userInfo, 7 * 24 * 60 * 60 * 1000)
                    resolve()
                }).catch((error) => {
                    reject(error)
                })
            })
        },
    }
})

store.watch(function(state) {
    return state.sessions
}, function(val) {
    localStorage.setItem('vue-chat-session', JSON.stringify(val));
}, {
    deep: true /*这个貌似是开启watch监测的判断,官方说明也比较模糊*/
})


export default store;