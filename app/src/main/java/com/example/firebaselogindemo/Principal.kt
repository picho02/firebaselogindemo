package com.example.firebaselogindemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firebaselogindemo.databinding.FragmentLoginBinding
import com.example.firebaselogindemo.databinding.FragmentPrincipalBinding
import com.google.firebase.auth.FirebaseAuth

class Principal : Fragment() {
    private lateinit var binding: FragmentPrincipalBinding
    lateinit var autenticacion: FirebaseAuth
    lateinit var loginFragment: Login


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPrincipalBinding.inflate(inflater, container, false)
        autenticacion = FirebaseAuth.getInstance()
        loginFragment = Login()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogOut.setOnClickListener {
            autenticacion.signOut()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView,loginFragment)
                ?.commit()
        }
    }

}