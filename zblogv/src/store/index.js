import Vuex from 'vuex'
import Vue from 'vue'
import { getToken, setToken, removeToken, getUserinfos, setUserinfos, removeUserinfos } from '@/request/token'
import { login, getUserInfo, logout, register } from '@/api/login'
import { ACCESS_TOKEN,USER_NAME,USER_INFO } from "@/store/mutation-types"

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        id: '',
        username: '',
        nickname: '',
        avatar: '',
        token: getToken(),
        Userinfo: getUserinfos(),
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
        }
    },
    actions: {
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
                        commit('SET_ACCOUNT', data.data.username)
                        commit('SET_NAME', data.data.nickname)
                        commit('SET_AVATAR', data.data.avatar)
                        commit('SET_ID', data.data.id)
                        commit('SET_USERINFO', data.data)
                        console.log("222222", JSON.stringify(data.data))
                        const userInfo = JSON.stringify(data.data)
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
        }
    }
})