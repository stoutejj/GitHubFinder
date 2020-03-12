package com.example.githubfinder.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserInfo::class], version = 1)
abstract class UserDB : RoomDatabase(){
    abstract fun getDao(): UserCollectionDao

    companion object{
        @Volatile
        private var INSTANCE: UserDB? = null
        fun getDatabase(context: Context): UserDB{

            return INSTANCE?: synchronized(this){
                val instance =
                    Room.databaseBuilder(context.applicationContext,
                    UserDB::class.java, "UserInfoDatabase")
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }
}