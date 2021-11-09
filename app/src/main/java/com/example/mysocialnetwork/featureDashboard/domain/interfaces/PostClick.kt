package com.example.mysocialnetwork.featureDashboard.domain.interfaces

import com.example.mysocialnetwork.featureDashboard.domain.entity.PostModel

interface PostClick {
    fun profileClick(mPostModel: PostModel)
}