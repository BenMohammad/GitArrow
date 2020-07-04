package com.benmohammad.gitarrow.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.benmohammad.gitarrow.data.RepoModel

@Database(entities = arrayOf(RepoModel::class), version = 1, exportSchema = true)
abstract class RepoDatabase: RoomDatabase() {

    abstract fun repoDao(): RepoDao

    companion object {
        private var INSTANCE: RepoDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): RepoDatabase {
            if(INSTANCE == null) {
                synchronized(RepoDatabase::javaClass) {
                    if(INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            RepoDatabase::class.java,
                            "Repo.db"
                        ).build()
                    }
                }
            }

            return INSTANCE!!
        }

    }
}