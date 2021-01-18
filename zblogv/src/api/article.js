import request from '@/request'


export function getArticles(query, page) {
    return request({
        url: '/list',
        method: 'get',
        params: {
            pageNumber: page.pageNumber,
            pageSize: page.pageSize,
            name: page.name,
            sort: page.sort,
            year: query.year,
            month: query.month,
            tagId: query.tagId,
            categoryId: query.categoryId
        }
    })
}

export function getHotArtices() {
    return request({
        url: '/hot',
        method: 'get'
    })
}

export function getNewArtices() {
    return request({
        url: '/new',
        method: 'get'
    })
}

export function viewArticle(id) {
    return request({
        url: `/blogInfo/${id}`,
        method: 'get'
    })
}

export function getArticlesByCategory(id) {
    return request({
        url: `/category/${id}`,
        method: 'get'
    })
}

export function getArticlesByTag(id) {
    return request({
        url: `/tag/${id}`,
        method: 'get'
    })
}


export function publishArticle(article) {
    return request({
        url: '/articles/publish',
        method: 'post',
        data: article
    })
}

export function listArchives() {
    return request({
        url: '/list',
        method: 'get'
    })
}

export function getArticleById(id) {
    return request({
        url: `/articles/${id}`,
        method: 'get'
    })
}