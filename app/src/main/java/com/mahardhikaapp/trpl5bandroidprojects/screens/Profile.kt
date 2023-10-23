package com.mahardhikaapp.trpl5bandroidprojects.screens


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mahardhikaapp.trpl5bandroidprojects.ListModal
import com.mahardhikaapp.trpl5bandroidprojects.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun Profile() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center
    ) {
        displayListView()
    }
}

fun getJSONData(courseList: MutableList<String>, ctx: Context) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.npoint.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val retrofitAPI = retrofit.create(RetrofitAPI::class.java)
    val call: Call<ArrayList<ListModal>> = retrofitAPI.getLanguages()
    call!!.enqueue(object : Callback<ArrayList<ListModal>?> {
        override fun onResponse(
            call: Call<ArrayList<ListModal>?>,
            response: Response<ArrayList<ListModal>?>
        ) {
            if (response.isSuccessful) {
                var lst: ArrayList<ListModal> = ArrayList()
                lst = response.body()!!
                for (i in 0 until lst.size) {
                    courseList.add(lst.get(i).languageName)
                }
            }
        }

        override fun onFailure(call: Call<ArrayList<ListModal>?>, t: Throwable) {
            Toast.makeText(ctx, "Fail to get the data..", Toast.LENGTH_SHORT)
                .show()
            Log.d("ERROR", t.toString());
        }
    })
}

@Composable
fun displayListView() {
    val context = LocalContext.current
    val courseList = remember { mutableStateListOf<String>() }
    getJSONData(courseList, context)
    LazyColumn {
        items(courseList) { language ->
            Text(language, modifier = Modifier.padding(15.dp))
            Divider()
        }
    }
}