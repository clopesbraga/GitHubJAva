package com.testemobile.githubjava.Repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.testemobile.githubjava.Repository.DAO.IItemDAO
import com.testemobile.githubjava.Model.RepositorioLocalModel

@Database(entities = arrayOf((RepositorioLocalModel::class)), version = 1)

abstract class AppDataBase:RoomDatabase () {

    abstract fun ItemDAO(): IItemDAO

    companion object{

        private lateinit var DBINSTANCE: AppDataBase
        fun getDatabase(context: Context): AppDataBase {
            if (!::DBINSTANCE.isInitialized) {
                synchronized(AppDataBase::class) {
                    DBINSTANCE =
                        Room.databaseBuilder(context, AppDataBase::class.java, "AppDataBase")
                            .addMigrations(MIGRATION)
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return DBINSTANCE
        }

        private val MIGRATION: Migration = object : Migration(0, 1) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("DELETE FROM Repositorio")
                db.execSQL("DELETE FROM Pullrequest")
            }

        }

    }

}