package com.example.githubfinder.model

data class ItemList(
    var items : List<User>
)

data class User(
    var url: String
)

data class UserInfo(
    var avatar_url : String,
    var login : String,
    var email : String,
    var location : String,
    var created_at : String,
    var followers : Int,
    var following : Int,
    var bio : String,
    var public_repos : Int,
    var repos_url : String
)

data class RepoInfo(
    var name : String,
    var private : Boolean,
    var forks : Int,
    var stargazers_count : Int
)







