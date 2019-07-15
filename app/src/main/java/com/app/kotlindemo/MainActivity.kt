package com.app.kotlindemo

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.async

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //creteThird()
        testAsync()
    }

    private fun creteThird() {
        val third1 = MyThird()
        val third2 = MyThird()
        val third3 = MyThird()
        third1.start()
        third2.start()
        third3.start()
    }

    private inner class MyThird : Thread() {
        override fun run() {
            //做业务
            while (true) {

            }
        }

    }


    fun testAsync(){
        //开启两个异步任务；这里只能用async，因为只有async有await()获取结果，并且异步
        async {
            Log.d("Task1", "当前线程：${Thread.currentThread().name}")
        }
        async {
            Log.d("Task2", "当前线程：${Thread.currentThread().name}")
        }
        async {
            repeat(100) {
                Log.d("Task5", "当前线程：${Thread.currentThread().name}")
            }
        }
        //更新UI或async
        GlobalScope.launch(Unconfined) {
            //当前UI线程的协程阻塞，但是不会使UI阻塞
//           text1.text = task1.await()
//            text2.text = task2.await()
            Log.d("UI2","当前线程：${Thread.currentThread().name}")
        }
    }
}
