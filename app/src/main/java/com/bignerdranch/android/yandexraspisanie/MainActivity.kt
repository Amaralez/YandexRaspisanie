package com.bignerdranch.android.yandexraspisanie

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.yandexraspisanie.databinding.ActivityMainBinding
import com.bignerdranch.android.yandexraspisanie.responsedata.YandexResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val NOTIFICATION_ID = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        val channelId ="canal"
     val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "canal",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "My channel description"
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(false)
            notificationManager.createNotificationChannel(channel)
        }


            val station = "s9600731"
            //val date = "2021-12-22"
            val apikey = "7662ee52-0ac0-4ddc-8d02-e9ed94eb9315"

        //&station=s9600731&lang=ru_RU&page=1&date=2021-12-22&apikey=f54df5ce-f50a-4c02-a23f-25b7cb34a527

        val queryInterface = QueryInterface
        val reto = queryInterface.create()

        val resp = reto.getResp(station,apikey)

        resp.enqueue(object : Callback<YandexResponse> {
            override fun onResponse(
                call: Call<YandexResponse>,
                response: Response<YandexResponse>
            ) {
                notyMe("Запрос обработан успешно")
                val resp = response.body()?.schedule
                val t = 3



                val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter =
                    resp?.let { CustomRecyclerAdapter(it) }
                recyclerView.addItemDecoration(
                    DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
                )







            }

            override fun onFailure(call: Call<YandexResponse>, t: Throwable) {
                notyMe("Запрос обработан успешно")
            }


        }
        )
        }

    private fun notyMe(text: String) {
        val builder = NotificationCompat.Builder(this, "canal")
            .setSmallIcon(R.drawable.ic_noty_name)
            .setContentTitle("Уведомление")
            .setContentText(text)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build()) // посылаем уведомление
        }
    }


}
