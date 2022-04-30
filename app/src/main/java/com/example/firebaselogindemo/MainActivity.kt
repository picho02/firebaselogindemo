package com.example.firebaselogindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.firebaselogindemo.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.nio.file.attribute.UserPrincipalNotFoundException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var loginFragment: Login
    private lateinit var principalFragment: Principal
    private lateinit var registerFragment: Register
    //Variable para autenticar
    lateinit var autenticacion: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        autenticacion= FirebaseAuth.getInstance()
        loginFragment = Login()
        principalFragment = Principal()
        registerFragment = Register()

    }

    override fun onStart() {
        super.onStart()
        // el signo de ? sirve para indicar que puede ser nulable ya que el usuario puede ser nulo
        val user: FirebaseUser? = autenticacion.currentUser
        if (user == null){
            //manda a login
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView,loginFragment).commit()
        }else{
            //avanzar a la pantalla principal
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, principalFragment)
                .commit()
        }
    }
    fun login(){
        /*val correo:String = findViewById<EditText>(R.id.etUser).text.toString()
        val password:String = findViewById<EditText>(R.id.etPwd).text.toString()
        autenticacion.signInWithEmailAndPassword(correo,password).addOnCompleteListener{
            if(it.isSuccessful){
                //avanzar a la pantalla principal
                Toast.makeText(this,"El usuario es correcto",Toast.LENGTH_LONG).show()
            }else{
                //mostrar resultado de error
                Toast.makeText(this,"No existe el usuario",Toast.LENGTH_LONG).show()
            }
        }*/
    }

    fun verificar(view: View) {
        login()
    }
}