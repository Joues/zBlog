import request from '@/request'

export function login(username, password, captcha, checkKey) {
    const data = {
        id: 0,
        username,
        password,
        captcha,
        checkKey,
    }
    return request({
        url: '/sys/login',
        method: 'post',
        data
    })
}

export function getRandomImage() {
    return request({
        url: '/sys/randomImage',
        method: 'get'
    })
}


export function logout() {
    return request({
        url: '/sys/logout',
        method: 'get'
    })
}

export function getUserInfo() {
    return request({
        url: '/user/userInfo',
        method: 'get'
    })
}

export function register(username, nickname, password) {
    const data = {
        username,
        nickname,
        password
    }
    return request({
        url: '/user/register',
        method: 'post',
        data
    })
}