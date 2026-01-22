package com.heartratemonitor.pregnancyvitalstrackerwithreminders.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VitalsEntity::class], version = 1)
abstract class VitalsDatabase : RoomDatabase() {

    abstract fun vitalsDao(): VitalsDao

    companion object {
        @Volatile
        private var INSTANCE: VitalsDatabase? = null

        fun getDatabase(context: Context): VitalsDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    VitalsDatabase::class.java,
                    "vitals_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
