package zwolinski.bartosz.zadaniepraktyczne.database.daos

import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Maybe
import zwolinski.bartosz.zadaniepraktyczne.database.entity.TTD

@Dao
interface TTDDao {

    @Query("SELECT * from TTD")
    fun getTTD(): List<TTD>

    @Insert
    fun insertAll(ttd: TTD): Long

    @Delete
    fun delete(ttd: TTD)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(ttd: TTD)

    @Query("SELECT * from TTD")
    fun getList(): Flowable<List<TTD>>

    @Query("SELECT * from TTD where id = :id")
    fun getTTDById(id: Long): Maybe<TTD>
}