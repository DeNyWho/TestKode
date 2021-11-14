package com.example.testkode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_detail.*
import java.time.LocalDate
import java.time.Period
import android.graphics.BitmapFactory

import android.graphics.Bitmap
import android.widget.ImageView
import java.net.URL


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
        val date = LocalDate.parse(birthdayText)
        val temp = date.year
        count.text = "${2021 - temp}"
        birthday.text = birthdayText
        phone.text = phoneText
        userTag.text = userTagText
        fullName.text = "$firstNameText $lastNameText"
        department.text = departmentText
        Picasso.get().load(avatarUrl).into(image)
    }
}