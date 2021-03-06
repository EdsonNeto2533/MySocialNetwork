package com.example.mysocialnetwork.featureAuth.featureLogin.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.MainFragmentBinding
import com.example.mysocialnetwork.generics.ui.HomeActivity
import com.example.mysocialnetwork.featureAuth.utils.ui.MainActivity
import com.example.mysocialnetwork.featureAuth.featureRegister.ui.RegisterFragment
import com.example.mysocialnetwork.generics.utils.SharedPreferences
import com.example.mysocialnetwork.generics.utils.changeFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: MainFragmentBinding
    private val sharedPreferences: SharedPreferences by inject()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        loadComponents()
        loadViewModels()

    }

    private fun loadComponents() {
        binding.btnLogin.setOnClickListener {
            tryLogin()
        }
        binding.tvSignUpLogin.setOnClickListener {
            (requireActivity() as MainActivity).changeFragment(RegisterFragment.newInstance())
        }
    }

    private fun loadViewModels() {
        viewModel.userLogged.observe(viewLifecycleOwner, {
            if (binding.cbRememberMeLogin.isChecked)
                sharedPreferences.setRememberMe(true)
            sharedPreferences.setUserId(it.uid)
            startActivity(Intent(requireActivity(), HomeActivity::class.java))
            requireActivity().finish()
        })
        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), getString(R.string.error_msg_login), Toast.LENGTH_SHORT).show()
        })
    }


    private fun tryLogin() {
        if (binding.etEmailLogin.text.toString().isNullOrBlank() || binding.etPasswordLogin.text.toString().isNullOrBlank()) {
            Toast.makeText(requireContext(), getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
        } else viewModel.loginWithEmailPassword(binding.etEmailLogin.text.toString(), binding.etPasswordLogin.text.toString())
    }


}