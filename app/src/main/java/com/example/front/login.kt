package com.example.front

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.front.databinding.LoginActivityBinding
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    // variable
    private lateinit var binding: LoginActivityBinding
    private  lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //initialize activity
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize Firebase

        auth = FirebaseAuth.getInstance()
        // then user Firebase

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // to move signup page
        binding.signup.setOnClickListener{
            var Intent = Intent(this,signu::class.java)
            startActivity(Intent)
            finish()
        }

        //to login in application
      binding.login.setOnClickListener{

          //import edittext
         val email = binding.email.text.toString()
          val password = binding.Password.text.toString()

          // to check and sigin in fairbase
          if (email.isEmpty()|| password.isEmpty()){
              Toast.makeText(this,"Please Fill Details", Toast.LENGTH_LONG).show()
          }else{

              auth.signInWithEmailAndPassword(email,password)
                  .addOnCompleteListener(this){ task->
                      if (task.isSuccessful){
                          Toast.makeText(this,"SignIn is Successful", Toast.LENGTH_LONG).show()
                          startActivity(Intent(this,MainActivity::class.java))
                          finish()

                      }else{
                          Toast.makeText(this,"SignIn is Failed${task.exception?.message}", Toast.LENGTH_LONG).show()
                      }

                  }

      }}

    }
}