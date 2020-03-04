package com.example.githubfinder.model

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface UserCollectionDao {
    @Insert
    suspend fun createCacheData(user: UserInfo)
}