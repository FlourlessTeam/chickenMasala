package com.example.chickenmasala.entities

import com.example.chickenmasala.entities.enums.AboutItemType

data class AboutItem<T>(
    val item: T,
    val type: AboutItemType,
)