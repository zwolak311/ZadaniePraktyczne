package zwolinski.bartosz.zadaniepraktyczne.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class TTD(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo var name: String,
    @ColumnInfo var date: Long,
    @ColumnInfo var accessing_count: Int,
    @ColumnInfo var color: Int
)