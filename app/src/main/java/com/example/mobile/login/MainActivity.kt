package com.example.mobile.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mobile.R
import com.example.mobile.map.MapActivity
import com.example.mobile.network.Apifactory
import com.example.mobile.network.SignUp
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logup.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val result = Apifactory.api.signUp(
                        SignUp(
                            0,
                            passwordInput.text.toString(),
                            loginInput.text.toString(),
                            loginInput.text.toString(),
                            0,
                            loginInput.text.toString()
                        )

                    ).await()

                    if (result.result == "Successfully inserted"){
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@MainActivity, "Зарегистрировался успешно",Toast.LENGTH_LONG ).show()
                        }
                    }
                } catch (e: Exception) {

                }

            }
        }

        login.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val result = Apifactory.api.login(loginInput.text.toString(), passwordInput.text.toString()).await()
                    if (result.result != "False"){
                        withContext(Dispatchers.Main) {
                            startActivity()
                        }
                    }
                } catch (e: Exception) {
                    
                }

            }
        }
    }

    private fun startActivity() {
        startActivity(Intent(this, MapActivity::class.java))
    }
}