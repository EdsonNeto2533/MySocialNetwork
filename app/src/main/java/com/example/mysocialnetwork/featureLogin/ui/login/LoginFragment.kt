package com.example.mysocialnetwork.featureLogin.ui.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.MainFragmentBinding
import com.example.mysocialnetwork.di.domain
import com.example.mysocialnetwork.di.viewModels
import com.example.mysocialnetwork.featureDashboard.ui.DashboardActivity
import com.example.mysocialnetwork.featureLogin.ui.MainActivity
import com.example.mysocialnetwork.featureLogin.ui.register.RegisterFragment
import com.example.mysocialnetwork.utilsGeneric.SharedPreferences
import com.example.mysocialnetwork.utilsGeneric.changeFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: MainFragmentBinding
    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadViewModels()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        loadComponents()

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
            sharedPreferences.setUserId(it.uid)
            startActivity(Intent(requireActivity(), DashboardActivity::class.java))
            requireActivity().finish()
        })
        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), getString(R.string.error_msg_login), Toast.LENGTH_SHORT).show()
        })
    }


    private fun tryLogin() {
        if (binding.etEmailLogin.text.isNullOrBlank() || binding.etPasswordLogin.text.isNullOrBlank()) {
            Toast.makeText(requireContext(), getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
        } else viewModel.loginWithEmailPassword(binding.etEmailLogin.text.toString(), binding.etPasswordLogin.text.toString())
    }


}