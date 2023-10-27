package com.dicoding.magazine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    companion object {
        const val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    private lateinit var rvSmartphone: RecyclerView
    private val list = ArrayList<Smartphone>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvSmartphone = findViewById(R.id.rv_smartphone)
        rvSmartphone.setHasFixedSize(true)

        list.addAll(getListSmartphone())
        showRecycleList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutPage::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getListSmartphone(): List<Smartphone> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val listSmartphone = ArrayList<Smartphone>()
        for (i in dataName.indices) {
            val cleanedPrice = dataPrice[i].replace("[^0-9.]".toRegex(), "")
            val price = if (cleanedPrice.isNotEmpty()) cleanedPrice.toDouble() else 0.0

            val smartphone = Smartphone(
                dataPhoto.getResourceId(i, -1),
                dataName[i],
                price,
                dataDescription[i]
            )
            listSmartphone.add(smartphone)
        }
        dataPhoto.recycle()
        return listSmartphone
    }

    private fun showRecycleList() {
        rvSmartphone.layoutManager = LinearLayoutManager(this)
        val listSmartphoneAdapter = listSmartphoneAdapter(this, list) {
            clickedSmartphone -> val intent = Intent(this, DetailSmartphoneActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, clickedSmartphone)
            startActivity(intent)
        }
        rvSmartphone.adapter = listSmartphoneAdapter
    }

}

