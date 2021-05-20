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
        url: '/login',
        method: 'post',
        data
    })
}

export function getRandomImage() {
    return request({
        url: '/randomImage',
        method: 'get'
    })
}


export function logout() {
    return request({
        url: '/logout',
        method: 'post'
    })
}

export function getUserInfo() {
    return request({
        url: '/user/userInfo',
        method: 'get'
    })
}

export function register(username, realname, password) {
    const data = {
        username,
        realname,
        password
    }
    return request({
        url: '/user/register',
        method: 'post',
        data
    })
}