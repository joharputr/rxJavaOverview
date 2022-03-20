package com.example.rxjavaexercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjavaexercise.databinding.ActivityMainBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {
    lateinit var myObservable: Observable<String>
    lateinit var myObserve: Observer<String>
    private lateinit var binding: ActivityMainBinding
    val text = "gretting"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        myObservable = Observable.just(text)
        myObserve = object : Observer<String> {
            override fun onSubscribe(d: Disposable?) {

            }

            override fun onNext(t: String?) {
                binding.rxJavaTest.setText(t)
            }

            override fun onError(e: Throwable?) {
            }

            override fun onComplete() {
            }

        }

        myObservable.subscribe(myObserve)
    }
}