package com.example.rxjavaexercise.logic

import  android.util.Log
import com.example.rxjavaexercise.MainActivity
import com.example.rxjavaexercise.databinding.ActivityFlatMapBinding
import com.example.rxjavaexercise.databinding.ActivityMainBinding
import com.example.rxjavaexercise.model.Student
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class Helper {
    fun observable(binding: ActivityMainBinding, mainActivity: MainActivity): Observer<Student> {
        return object : Observer<Student> {
            override fun onSubscribe(d: Disposable?) {
                if (d != null) {
                    mainActivity.disposable = d
                }
            }

            override fun onNext(t: Student?) {
                binding.rxJavaTest.setText(t?.text)

            }

            override fun onError(e: Throwable?) {
            }

            override fun onComplete() {
            }

        }
    }

    fun observable2(
        binding: ActivityFlatMapBinding,
    ): Observer<Student> {
        return object : Observer<Student> {
            override fun onSubscribe(d: Disposable?) {

            }

            override fun onNext(t: Student?) {
                Log.d("cekData", t?.text.toString())
                binding.flatMapId.setText(t?.text)

            }

            override fun onError(e: Throwable?) {
            }

            override fun onComplete() {
            }

        }
    }
}