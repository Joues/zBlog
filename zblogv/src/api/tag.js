import request from '@/request'

export function getAllTags() {
    return request({
        url: '/tag/list',
        method: 'get',
    })
}

export function getAllTagsDetail() {
    return request({
        url: '/tag/list',
        method: 'get',
    })
}

export function getHotTags() {
    return request({
        url: '/tag/hot',
        method: 'get',
    })
}

export function getTag(id) {
    return request({
        url: `/tag/${id}`,
        method: 'get',
    })
}

export function getTagDetail(id) {
    return request({
        url: `/tag/id`,
        method: 'get',
        params: {
            id: id,
        }
    })
}