package com.testemobile.githubjava.Repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.testemobile.githubjava.DAO.IItemDAO
import com.testemobile.githubjava.Model.ItemsModelRepo

@Database(entities = arrayOf((ItemsModelRepo::class)), version = 1)

abstract class AppDataBase:RoomDatabase () {

    abstract fun ItemDAO(): IItemDAO

    companion object{

        private lateinit var DBINSTANCE: AppDataBase
        fun getDatabase(context: Context): AppDataBase {
            if (!::DBINSTANCE.isInitialized) {
                synchronized(AppDataBase::class) {
                    DBINSTANCE =
                        Room.databaseBuilder(context, AppDataBase::class.java, "AppDataBase")
                            .addMigrations(MIGRATION_1_2)
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return DBINSTANCE
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {

            }

        }

    }

}