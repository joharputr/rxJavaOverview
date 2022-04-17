package com.example.rxjavaexercise

import android.app.Application
import com.example.rxjavaexercise.model.Student
import io.reactivex.rxjava3.core.Observable

class App : Application() {

    companion object {
        lateinit var myObservable: Observable<Student>
        val studentList = arrayListOf<Student>()
    }

    override fun onCreate() {
        super.onCreate()

        studentList.add(Student("yolo2"))
        myObservable = Observable.create { emitter ->
            studentList.forEach {
                emitter?.onNext(it)
            }
            emitter?.onComplete()
        }
    }

}