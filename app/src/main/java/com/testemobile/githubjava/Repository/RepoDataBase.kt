package com.testemobile.githubjava.Repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.testemobile.githubjava.DAO.ItemDAO
import com.testemobile.githubjava.Model.ItemsModel

@Database(entities = arrayOf((ItemsModel::class)), version = 1)

abstract class RepoDataBase:RoomDatabase () {



    abstract fun ItemDAO(): ItemDAO

    companion object{

        private lateinit var DBINSTANCE: RepoDataBase
        fun getDatabase(context: Context): RepoDataBase {
            if (!::DBINSTANCE.isInitialized) {
                synchronized(RepoDataBase::class) {
                    DBINSTANCE =
                        Room.databaseBuilder(context, RepoDataBase::class.java, "DbGitHub")
                            .addMigrations(MIGRATION_3_4)
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return DBINSTANCE
        }

        private val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }

        }

    }

}