package com.example.testkode

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_detail.*
import java.time.LocalDate
import java.time.Period
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.example.testkode.enums.AlertStyle
import java.net.URL
import java.time.Month
import java.util.*


class DetailActivity : AppCompatActivity() {

    companion object{
        const val avatarUrl = "avatar"
        const val birthdayl = "birthday"
        const val departmentl = "department"
        const val firstName = "firstName"
        const val lastName = "lastName"
        const val phoneNum = "phone"
        const val position = "position"
        const val userTagl = "userTag"
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val avatarUrl = intent.getStringExtra(avatarUrl)
        val departmentText = intent.getStringExtra(departmentl)
        val firstNameText = intent.getStringExtra(firstName)
        val lastNameText = intent.getStringExtra(lastName)
        val phoneText = intent.getStringExtra(phoneNum)
        val userTagText = intent.getStringExtra(userTagl)
        val birthdayText = intent.getStringExtra(birthdayl)
        val image = findViewById<ImageView>(R.id.image)
        val date = Calendar.getInstance()

        if (birthdayText != null) {

            val temp = (date.get(Calendar.YEAR)).minus((birthdayText.take(4).toInt())) % 10

            if( (date.get(Calendar.YEAR)).minus((birthdayText.take(4).toInt())) < 10) {

                    if(temp == 1) count.text = "${(date.get(Calendar.YEAR)).minus((birthdayText.take(4).toInt()))} год"
                    else if(temp == 2 || temp == 3 || temp == 4 ) count.text = "${(date.get(Calendar.YEAR)).minus((birthdayText.take(4).toInt()))} года"
                    else count.text = "${(date.get(Calendar.YEAR)).minus((birthdayText.take(4).toInt()))} лет" }
            else if((date.get(Calendar.YEAR)).minus((birthdayText.take(4).toInt())) in 10..20)
                count.text = "${(date.get(Calendar.YEAR)).minus((birthdayText.take(4).toInt()))} лет"
            else if (temp == 1 ) count.text = "${(date.get(Calendar.YEAR)).minus((birthdayText.take(4).toInt()))} год"
            else if (temp == 2 && temp == 3 && temp == 4)  count.text = "${(date.get(Calendar.YEAR)).minus((birthdayText.take(4).toInt()))} года"
            else  count.text = "${(date.get(Calendar.YEAR)).minus((birthdayText.take(4).toInt()))} лет"
        }
        val _date = LocalDate.parse(birthdayText)
        birthday.text = when(_date.month){
            Month.DECEMBER -> "${_date.dayOfMonth} декабря ${_date.year}"
            Month.APRIL -> "${_date.dayOfMonth} апреля ${_date.year}"
            Month.AUGUST -> "${_date.dayOfMonth} августа ${_date.year}"
            Month.FEBRUARY -> "${_date.dayOfMonth} февраля ${_date.year}"
            Month.JANUARY -> "${_date.dayOfMonth} января ${_date.year}"
            Month.JULY -> "${_date.dayOfMonth} июля ${_date.year}"
            Month.JUNE -> "${_date.dayOfMonth} июня ${_date.year}"
            Month.MARCH -> "${_date.dayOfMonth} марта ${_date.year}"
            Month.MAY -> "${_date.dayOfMonth} мая ${_date.year}"
            Month.NOVEMBER -> "${_date.dayOfMonth} ноября ${_date.year}"
            Month.OCTOBER -> "${_date.dayOfMonth} октября ${_date.year}"
            Month.SEPTEMBER -> "${_date.dayOfMonth} сентября ${_date.year}"

            else -> "error"
        }
        val _phoneText = "$phoneText"

        val array = CharArray(_phoneText.length)
        for ( i in _phoneText.indices) array[i] = _phoneText[i]
        phone.text = "+${array[0]} (${array[1]}${array[2]}${array[4]}) ${array[5]}${array[6]}${array[8]} ${array[9]}${array[10]} ${array[11]}"
        phone.setOnClickListener {

            val alert = AlertView("+${array[0]} (${array[1]}${array[2]}${array[4]}) ${array[5]}${array[6]}${array[8]} ${array[9]}${array[10]} ${array[11]}", AlertStyle.PHONE)

            alert.setCancelButtonText("Отмена")
            alert.show(this)
        }
        userTag.text = userTagText
        fullName.text = "$firstNameText $lastNameText"
        department.text = departmentText
        Picasso.get().load(avatarUrl).into(image)
    }
}