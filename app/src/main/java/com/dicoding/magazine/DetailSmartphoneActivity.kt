package com.dicoding.magazine

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

@Suppress("DEPRECATION")
class DetailSmartphoneActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_smartphone)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val smartphone = intent.getParcelableExtra<Smartphone>(MainActivity.INTENT_PARCELABLE)

            val tvDetailPhoto = findViewById<ImageView>(R.id.iv_img_detail_photo)
            val tvDetailName = findViewById<TextView>(R.id.tv_detail_name)
            val tvDetailDesc = findViewById<TextView>(R.id.tv_detail_description)
            val tvDetailPrice = findViewById<TextView>(R.id.tv_detail_price)

            smartphone?.photo?.let { tvDetailPhoto.setImageResource(it) }
            tvDetailName.text = smartphone?.name
            tvDetailDesc.text = smartphone?.description
        if (smartphone != null) {
            val priceText = "Price: Rp.${smartphone.price}"
            tvDetailPrice.text = priceText
        }else {
            tvDetailPrice.text = "Price: N/A"
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}


