package zwolinski.bartosz.zadaniepraktyczne.adapter

import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bartosz.zwolinski.zadaniepraktyczne.R
import kotlinx.android.synthetic.main.ttd_item.view.*
import zwolinski.bartosz.zadaniepraktyczne.database.entity.TTD

class TTDAdapter(private var onTTDItemClick: OnTTDItemClick) : RecyclerView.Adapter<TTDAdapter.TTDHolder>() {

    private lateinit var ttdList: ArrayList<TTD>

    class TTDHolder(itemView: View, private var onTTDItemClick: OnTTDItemClick) : RecyclerView.ViewHolder(itemView) {

        fun setTTDItem(ttd: TTD) {
            itemView.name.text = ttd.name
            itemView.accesing_count.text = "Wejść: ${ttd.accessing_count}"

            when (ttd.color) {
                0 -> itemView.cardView.setBackgroundColor(
                    ResourcesCompat.getColor(
                        itemView.resources,
                        R.color.colorPrimary,
                        null
                    )
                )
                1 -> itemView.cardView.setBackgroundColor(
                    ResourcesCompat.getColor(
                        itemView.resources,
                        R.color.colorPrimary,
                        null
                    )
                )
                2 -> itemView.cardView.setBackgroundColor(
                    ResourcesCompat.getColor(
                        itemView.resources,
                        R.color.colorPrimary,
                        null
                    )
                )
                3 -> itemView.cardView.setBackgroundColor(
                    ResourcesCompat.getColor(
                        itemView.resources,
                        R.color.colorPrimary,
                        null
                    )
                )
                4 -> itemView.cardView.setBackgroundColor(
                    ResourcesCompat.getColor(
                        itemView.resources,
                        R.color.colorPrimary,
                        null
                    )
                )
            }

            itemView.button.setOnClickListener { onTTDItemClick.onDeleteClicked(ttd) }
            itemView.clickLayout.setOnClickListener { onTTDItemClick.onItemClick(ttd) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TTDHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ttd_item, parent, false)
        return TTDHolder(view, onTTDItemClick)
    }

    override fun onBindViewHolder(holder: TTDHolder, position: Int) {
        holder.setTTDItem(ttdList[position])
    }


    fun setList(ttdList: List<TTD>) {
        this.ttdList = ttdList as ArrayList<TTD>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = ttdList.size

}