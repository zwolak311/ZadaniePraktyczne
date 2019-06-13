package zwolinski.bartosz.zadaniepraktyczne.database.accual_database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import zwolinski.bartosz.zadaniepraktyczne.database.daos.TTDDao
import zwolinski.bartosz.zadaniepraktyczne.database.entity.TTD


@Database(entities = [TTD::class], version = 1)
abstract class TTDDatabase : RoomDatabase() {
    public abstract fun ttdDao(): TTDDao
}