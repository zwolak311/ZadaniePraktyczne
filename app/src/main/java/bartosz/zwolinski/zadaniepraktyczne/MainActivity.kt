package zwolinski.bartosz.zadaniepraktyczne

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import bartosz.zwolinski.zadaniepraktyczne.R
import bartosz.zwolinski.zadaniepraktyczne.database.TTDDatabaseController
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import zwolinski.bartosz.zadaniepraktyczne.adapter.OnTTDItemClick
import zwolinski.bartosz.zadaniepraktyczne.adapter.TTDAdapter
import zwolinski.bartosz.zadaniepraktyczne.dagger2.ContextModule
import zwolinski.bartosz.zadaniepraktyczne.dagger2.DaggerTTDControllerComponent
import zwolinski.bartosz.zadaniepraktyczne.database.entity.TTD
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnTTDItemClick, TTDDatabaseController.TTDDatabaseInterface {

    private lateinit var adapter: TTDAdapter

    private lateinit var controller: TTDDatabaseController
    private var tddList: ArrayList<TTD> = ArrayList()

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
        setContentView(R.layout.activity_main)
        initView()

        controller = DaggerTTDControllerComponent
            .builder()
            .contextModule(ContextModule(this))
            .build().getTTdController()


        Log.d("sda", "Main   " + controller.hashCode().toString())

        controller.rxGetTTDList(this)

        fab.setOnClickListener {
            controller.rxInsertAll(controller.generateTTD())
        }
    }

    override fun sendList(list: ArrayList<TTD>) {
        tddList = list
        setAdapter()
    }


    private fun initView() {
        recycle_view.layoutManager = LinearLayoutManager(this)
    }


    private fun setAdapter() {
        adapter = TTDAdapter(this)
        adapter.setList(tddList)
        recycle_view.adapter = adapter
    }


    override fun onItemClick(ttd: TTD) {
        ttd.accessing_count++
        controller.rxUpdateTTD(ttd)

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", ttd.id)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP

        startActivity(intent)
    }


    override fun onDeleteClicked(ttd: TTD) {
        controller.rxDeleteTTD(ttd)
    }
}