package com.example.front

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.front.databinding.SignuActivityBinding
import com.google.firebase.auth.FirebaseAuth

class signu : AppCompatActivity() {

    // variable
    private lateinit var binding: SignuActivityBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // initialize activity
        binding = SignuActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize fairbase auth
        auth = FirebaseAuth.getInstance()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // to move login page
        binding.login.setOnClickListener{
            var Intent = Intent(this,login::class.java)
            startActivity(Intent)
            finish()
        }

        // to create account

        binding.signup.setOnClickListener{

            // get text from edit text field
            val email = binding.Email.text.toString()
            val username = binding.username.text.toString()
            val password = binding.Password.text.toString()
            val repass = binding.rePass.text.toString()

            // check the fill to all field

            if (email.isEmpty()||username.isEmpty()||password.isEmpty()||repass.isEmpty()){

                Toast.makeText(this,"Fill The All Details",Toast.LENGTH_LONG).show()
            } else if (password != repass){
                Toast.makeText(this,"Password is not Match",Toast.LENGTH_LONG).show()

            }else{

                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){ task->
                        if (task.isSuccessful){
                            Toast.makeText(this,"Registration is Successful", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this,MainActivity::class.java))
                            finish()

                        }else{
                            Toast.makeText(this,"Registration is Failed${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }

                    }

            }

        }
    }
}