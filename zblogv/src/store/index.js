import Vuex from 'vuex'
import Vue from 'vue'
import { getToken, setToken, removeToken } from '@/request/token'
import { login, getUserInfo, logout, register } from '@/api/login'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        // id: '',
        username: '',
        // name: '',
        // avatar: '',
        token: getToken(),
    },
    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token;
        },
        SET_ACCOUNT: (state, username) => {
            state.username = username
        },
        SET_NAME: (state, name) => {
            state.name = name
        },
        SET_AVATAR: (state, avatar) => {
            state.avatar = avatar
        },
        SET_ID: (state, id) => {
            state.id = id
        }
    },
    actions: {
        login({ commit }, user) {
            return new Promise((resolve, reject) => {
                login(user.username, user.password, user.captcha, user.checkKey).then(data => {
                    commit('SET_TOKEN', data.data.token)
                    setToken(data.data['X-Access-Token'])
                    resolve()
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
                            // commit('SET_NAME', data.data.nickname)
                            // commit('SET_AVATAR', data.data.avatar)
                            // commit('SET_ID', data.data.id)
                    } else {
                        commit('SET_ACCOUNT', '')
                            // commit('SET_NAME', '')
                            // commit('SET_AVATAR', '')
                            // commit('SET_ID', '')
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
                        // commit('SET_NAME', '')
                        // commit('SET_AVATAR', '')
                        // commit('SET_ID', '')
                    removeToken()
                    resolve()

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
                    // commit('SET_NAME', '')
                    // commit('SET_AVATAR', '')
                    // commit('SET_ID', '')
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
                    setToken(data.data['data'])
                    resolve()
                }).catch((error) => {
                    reject(error)
                })
            })
        }
    }
})