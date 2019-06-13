package bartosz.zwolinski.zadaniepraktyczne.database

import android.annotation.SuppressLint
import android.arch.persistence.room.Room
import android.content.Context
import io.reactivex.Completable
import io.reactivex.MaybeObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import zwolinski.bartosz.zadaniepraktyczne.database.accual_database.TTDDatabase
import zwolinski.bartosz.zadaniepraktyczne.database.entity.TTD
import java.util.*


class TTDDatabaseController(applicationContext: Context) {
    private var db: TTDDatabase =
        Room.databaseBuilder(applicationContext, TTDDatabase::class.java, "things_to_do").build()


    //
    //TTD generations
    //
    fun generateTTD(): TTD {
        return TTD(
            null,
            "Rzecz do zrobienia - ${randomString(8)}",
            Calendar.getInstance().timeInMillis,
            0,
            Random().nextInt(5)
        )
    }

    private fun randomString(len: Int): String {
        val data = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"

        val sb = StringBuilder(len)
        for (i in 0 until len) {
            sb.append(data[Random().nextInt(data.length)])
        }
        return sb.toString()
    }

    //
    //DB accessing function
    //
    @SuppressLint("CheckResult")
    fun rxGetTTDList(ttdDatabaseInterface: TTDDatabaseInterface) {
        db.ttdDao().getList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                ttdDatabaseInterface.sendList(it as ArrayList<TTD>)
            }
    }


    fun rxInsertAll(ttd: TTD) {
        Completable.fromAction {
            db.ttdDao().insertAll(ttd)
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun rxUpdateTTD(ttd: TTD) {
        Completable.fromAction {
            db.ttdDao().update(ttd)
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()

    }

    fun rxDeleteTTD(ttd: TTD) {
        Completable.fromAction {
            db.ttdDao().delete(ttd)
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun rxGetTTDById(id: Long, singleTDDResultInterface: SingleTDDResultInterface) {
        db.ttdDao().getTTDById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : MaybeObserver<TTD> {
                override fun onSuccess(t: TTD) {
                    singleTDDResultInterface.onFindByIdResult(t)
                }

                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }
            })


//        (object : MaybeObserver<TTD> {
//            override fun onSuccess(t: TTD?) {
//                singleTDDResultInterface.onFindByIdResult(t!!)
//            }
//
//            override fun onComplete() {
//            }
//
//            override fun onSubscribe(d: Disposable?) {
//            }
//
//            override fun onError(e: Throwable?) {
//            }
//        })
    }

    //
    //Public interfaces
    //
    interface SingleTDDResultInterface {
        fun onFindByIdResult(ttd: TTD)
    }

    interface TTDDatabaseInterface {
        fun sendList(list: ArrayList<TTD>)
    }

}
