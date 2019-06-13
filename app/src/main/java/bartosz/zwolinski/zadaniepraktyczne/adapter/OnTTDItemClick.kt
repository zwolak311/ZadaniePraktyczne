package zwolinski.bartosz.zadaniepraktyczne.adapter

import zwolinski.bartosz.zadaniepraktyczne.database.entity.TTD

interface OnTTDItemClick {
    fun onItemClick(ttd: TTD)
    fun onDeleteClicked(ttd: TTD)
}