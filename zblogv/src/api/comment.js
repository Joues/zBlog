import request from '@/request'


export function getCommentsByArticle(id) {
    return request({
        url: `/blogComment/bid`,
        method: 'get',
        params: {
            bid: id,
        }
    })
}

export function publishComment(comment) {
    return request({
        url: '/blogComment/add',
        method: 'post',
        data: comment
    })
}