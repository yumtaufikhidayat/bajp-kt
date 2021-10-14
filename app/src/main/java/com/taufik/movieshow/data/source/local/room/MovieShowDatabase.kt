package com.taufik.movieshow.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.taufik.movieshow.data.source.local.entity.MovieEntity
import com.taufik.movieshow.data.source.local.entity.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieShowDatabase : RoomDatabase() {

    abstract fun movieShowDao(): MovieShowDao

    companion object {

        @Volatile
        private var INSTANCE: MovieShowDatabase? = null

        fun getInstance(context: Context): MovieShowDatabase =
            INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieShowDatabase::class.java,
                    "MovieShowDB.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}