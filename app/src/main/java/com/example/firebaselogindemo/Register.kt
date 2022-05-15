package com.example.firebaselogindemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.firebaselogindemo.databinding.FragmentLoginBinding
import com.example.firebaselogindemo.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class Register : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    lateinit var autenticacion: FirebaseAuth
    lateinit var principalFragment: Principal
    lateinit var loginFragment: Login
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        autenticacion = FirebaseAuth.getInstance()
        principalFragment = Principal()
        loginFragment = Login()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAceptarRegister.setOnClickListener {
            val nombre: String = binding.etName.text.toString()
            val apellido: String = binding.etApellido.text.toString()
            val correo: String = binding.etEmailRegister.text.toString()
            val password: String = binding.etPswRegister.text.toString()
            autenticacion.createUserWithEmailAndPassword(correo, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    db.collection("user").document(autenticacion.currentUser.toString()).set(
                       hashMapOf( "nombre" to nombre, "apellido" to apellido, "email" to correo, "password" to password)
                    )
                    //avanzar a la pantalla principal
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragmentContainerView,principalFragment)
                        ?.commit()

                } else {
                    //mostrar resultado de error
                    Toast.makeText(requireContext(),"Algo salio mal", Toast.LENGTH_LONG).show()
                }
            }
        }
        binding.btnCancelarRegister.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView,loginFragment)
                ?.commit()
        }

    }

}

