package com.example.testkode.models

data class User(
    val avatarUrl: String,
    val birthday: String,
    val department: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val phone: String,
    var position: String,
    val userTag: String
)
