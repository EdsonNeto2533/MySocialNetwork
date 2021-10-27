package com.example.mysocialnetwork.featureLogin.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.MainFragmentBinding

class LoginFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadViewModels()
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        loadComponents()

    }

    private fun loadComponents(){
        binding.btnLogin.setOnClickListener {
            tryLogin()
        }
    }

    private fun loadViewModels(){
        viewModel.userLogged.observe(viewLifecycleOwner, {

        })
        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), getString(R.string.error_msg_login), Toast.LENGTH_SHORT).show()
        })
    }


    private fun tryLogin(){
        if (binding.etEmailLogin.text.isNullOrBlank() || binding.etPasswordLogin.text.isNullOrBlank()){
            Toast.makeText(requireContext(), getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
        } else viewModel.loginWithEmailPassword(binding.etEmailLogin.text.toString(), binding.etPasswordLogin.text.toString())
    }





}