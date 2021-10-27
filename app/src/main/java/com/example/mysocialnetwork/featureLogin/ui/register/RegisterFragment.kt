package com.example.mysocialnetwork.featureLogin.ui.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.RegisterFragmentBinding
import com.example.mysocialnetwork.featureLogin.ui.MainActivity
import com.example.mysocialnetwork.featureLogin.ui.login.LoginFragment
import com.example.mysocialnetwork.utilsGeneric.changeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment(R.layout.register_fragment) {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private val viewModel: RegisterViewModel by viewModel()
    private lateinit var binding: RegisterFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RegisterFragmentBinding.bind(view)
        loadViewModels()

    }

    private fun loadViewModels(){
        viewModel.userCreated.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), getString(R.string.userCreated_msg), Toast.LENGTH_SHORT).show()
            (requireActivity() as MainActivity).changeFragment(LoginFragment.newInstance())
        })
        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), getString(R.string.error_msg_generic), Toast.LENGTH_SHORT).show()
        })
    }



}