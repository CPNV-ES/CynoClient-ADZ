package ch.leytto.cynoclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val test = intent.getStringExtra("client_id")
        Toast.makeText(this,test,Toast.LENGTH_SHORT).show();
    }


}