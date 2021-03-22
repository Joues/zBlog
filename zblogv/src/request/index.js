import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/request/token'

// const service = axios.create({
//     baseURL: process.env.BASE_API,
//     timeout: 6000
// })
const service = axios.create({
    baseURL: "/api/zblog/",
    timeout: 6000
})


//request拦截器
service.interceptors.request.use(config => {

    if (store.state.token) {
        config.headers['X-Access-Token'] = getToken()
    }
    return config
}, error => {

    Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
    response => {

        //全局统一处理 Session超时
        if (response.headers['session_time_out'] == 'timeout') {
            store.dispatch('fedLogOut')
        }

        const res = response.data;


        //0 为成功状态
        if (res.code !== 0) {

            //90001 Session超时
            if (res.code === 90001) {
                return Promise.reject('error');
            }

            //20001 用户未登录
            if (res.code === 20001) {
                console.info("用户未登录")

                Message({
                    type: 'warning',
                    showClose: true,
                    message: '未登录或登录超时，请重新登录哦'
                })

                return Promise.reject('error');
            }

            //70001 权限认证错误
            if (res.code === 70001) {
                console.info("权限认证错误")
                Message({
                    type: 'warning',
                    showClose: true,
                    message: '你没有权限访问哦'
                })
                return Promise.reject('error');
            }


            return Promise.reject(res.message);
        } else {
            return response.data;
        }
    },
    error => {
        if (error.response.status == 504 || error.response.status == 404) {
            Message.error({message: '服务器迷路了呢⊙﹏⊙∥'});
          } else if (error.response.status == 403) {
            Message.error({message: '权限不足,请联系管理员!'});
          } else if (error.response.status == 401) {
            Message.error({message: error.response.data.message});
          } else {
            if (error.response.data.message) {
              Message.error({message: error.response.data.message});
            } else {
              Message.error({message: '未知错误!'});
            }
        }
        return Promise.reject('error')
    })

export default service
