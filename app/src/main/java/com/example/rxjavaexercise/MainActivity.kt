package com.example.rxjavaexercise

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjavaexercise.databinding.ActivityMainBinding
import com.example.rxjavaexercise.logic.Helper
import com.example.rxjavaexercise.ui.FlatMapActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    val helper = Helper()
    lateinit var disposable: Disposable
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.gotoFlatMapId.setOnClickListener {
            App.studentList.clear()
            App.studentList.forEach {
                App.myObservable = Observable.just(it)
            }
            startActivity(Intent(this, FlatMapActivity::class.java))
        }

//
//        studentList.forEach {
//            myObservable = Observable.just(it)
//        }
        //  myObservable = Observable.fromIterable(studentList)

        App.myObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .map { t ->
                t?.text = t?.text + " gogo mama"
                t
            }
            .subscribe(helper.observable(binding, this))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}

