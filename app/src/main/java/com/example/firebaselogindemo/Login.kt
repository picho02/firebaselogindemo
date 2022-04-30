package com.example.firebaselogindemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.firebaselogindemo.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class Login : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    lateinit var autenticacion: FirebaseAuth
    lateinit var principalFragment: Principal
    lateinit var registerFragment: Register
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        autenticacion = FirebaseAuth.getInstance()
        principalFragment = Principal()
        registerFragment = Register()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            val correo: String = binding.etUser.text.toString()
            val password: String = binding.etPwd.text.toString()
            autenticacion.signInWithEmailAndPassword(correo, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    //avanzar a la pantalla principal
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragmentContainerView,principalFragment)
                        ?.commit()

                } else {
                    //mostrar resultado de error
                    Toast.makeText(requireContext(),"Algo salio mal",Toast.LENGTH_LONG).show()
                }
            }
        }
        //le pones oncliclistener al boton registrarse
        binding.btnRegistrar.setOnClickListener {
            //sirve para remplazar el fragment en el contenedor
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView,registerFragment)
                ?.commit()
        }
    }
}
