package com.example.rxjavaexercise.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjavaexercise.databinding.ActivityFlatMapBinding
import com.example.rxjavaexercise.logic.Helper
import com.example.rxjavaexercise.model.Student
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers

class FlatMapActivity : AppCompatActivity() {
    lateinit var myObservable: Observable<Student>
    lateinit var studentList: ArrayList<Student>
    val helper = Helper()
    private lateinit var binding: ActivityFlatMapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlatMapBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        studentList = arrayListOf()
        studentList.add(Student(text = "sekali lagi"))
        myObservable = Observable.fromIterable(studentList)

        myObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .concatMap (Function<Student, Observable<Student>> {
                val student2 = Student(text = "2")

                val student3 = Student(text = "3")

                it.text = it.text?.uppercase()
                return@Function Observable.just(it, student2, student3)
            }).subscribe(helper.observable2(binding))
    }
}

