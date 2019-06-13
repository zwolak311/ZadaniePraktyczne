package zwolinski.bartosz.zadaniepraktyczne

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import bartosz.zwolinski.zadaniepraktyczne.R
import bartosz.zwolinski.zadaniepraktyczne.database.TTDDatabaseController
import kotlinx.android.synthetic.main.activity_detail.*
import zwolinski.bartosz.zadaniepraktyczne.dagger2.ContextModule
import zwolinski.bartosz.zadaniepraktyczne.dagger2.DaggerTTDControllerComponent
import zwolinski.bartosz.zadaniepraktyczne.database.entity.TTD
import java.util.*

class DetailActivity : AppCompatActivity(), TTDDatabaseController.SingleTDDResultInterface {


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Log.d("groupActivity", "onOptionItemSelected")
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val controller = DaggerTTDControllerComponent
            .builder()
            .contextModule(ContextModule(this))
            .build().getTTdController()

        Log.d("sda", "Detail " + controller.hashCode().toString())

        controller.rxGetTTDById(intent.getLongExtra("id", -1), this)
    }

    override fun onFindByIdResult(ttd: TTD) {
        setTime((Calendar.getInstance().timeInMillis - ttd.date))
        setBackgroundAndActonBarColor(ttd.color)
        setTTDViewCard(ttd.name, ttd.id.toString())
    }

    private fun setTTDViewCard(name: String, id: String) {
        detailId.text = id
        detailName.text = name
    }

    private fun setBackgroundAndActonBarColor(color: Int) {
        when (color) {
            0 -> {
                detailCardView.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.colorPrimary, null))
                supportActionBar?.setBackgroundDrawable(
                    ColorDrawable(
                        ResourcesCompat.getColor(resources, R.color.colorPrimary, null)
                    )
                )
            }
            1 -> {
                detailCardView.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.colorPrimary, null))
                supportActionBar?.setBackgroundDrawable(
                    ColorDrawable(
                        ResourcesCompat.getColor(resources, R.color.colorPrimary, null)
                    )
                )
            }
            2 -> {
                detailCardView.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.colorPrimary, null))
                supportActionBar?.setBackgroundDrawable(
                    ColorDrawable(
                        ResourcesCompat.getColor(resources, R.color.colorPrimary, null)
                    )
                )
            }
            3 -> {
                detailCardView.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.colorPrimary, null))
                supportActionBar?.setBackgroundDrawable(
                    ColorDrawable(
                        ResourcesCompat.getColor(resources, R.color.colorPrimary, null)
                    )
                )
            }
            4 -> {
                detailCardView.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.colorPrimary, null))
                supportActionBar?.setBackgroundDrawable(
                    ColorDrawable(
                        ResourcesCompat.getColor(resources, R.color.colorPrimary, null)
                    )
                )
            }
        }
    }

    private fun delayTime(time: Long) {
        Handler().postDelayed({
            setTime(time + 1000)
        }, 1000)
    }

    @SuppressLint("SetTextI18n")
    private fun setTime(time: Long) {
        val seconds = (time / 1000) % 60
        val minutes = (time / (1000 * 60) % 60)
        val hours = (time / (1000 * 60 * 60) % 24)

        val secondsString = if (seconds > 10) "$seconds" else "0$seconds"
        val minutesString = if (minutes > 10) "$minutes" else "0$minutes"
        val hoursString = if (hours > 10) "$hours" else "0$hours"

        detailTime.text = "$hoursString:$minutesString:$secondsString"
        delayTime(time)
    }
}