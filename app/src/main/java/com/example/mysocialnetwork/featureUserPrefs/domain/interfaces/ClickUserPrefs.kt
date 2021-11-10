package com.example.mysocialnetwork.featureUserPrefs.domain.interfaces

import com.example.mysocialnetwork.featureUserPrefs.domain.entity.PostModelUserPrefs

interface ClickUserPrefs {
    fun clickDelete(mPostModelUserPrefs: PostModelUserPrefs)
}