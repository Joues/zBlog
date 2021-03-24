import request from '@/request'


export function getCommentsByArticle(id) {
  return request({
    url: `/blogDetail/id/${id}`,
    method: 'get'
  })
}

export function publishComment(comment) {
  return request({
    url: '/blogComment/add',
    method: 'post',
    data: comment
  })
}

