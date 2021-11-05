package com.example.mysocialnetwork.featureLogin.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.RegisterFragmentBinding
import com.example.mysocialnetwork.featureLogin.ui.MainActivity
import com.example.mysocialnetwork.featureLogin.ui.login.LoginFragment
import com.example.mysocialnetwork.generics.utils.changeFragment
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
        loadComponents()
        loadSpinner()
    }

    private fun loadSpinner() {
        binding.spGenderRegister.setAdapter(ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, listOf("Masculino", "Feminino")))
    }

    private fun loadComponents() {
        binding.btnRegister.setOnClickListener {
            if (binding.etAgeRegister.text.toString().isNullOrBlank() ||
                binding.etEmailRegister.text.toString().isNullOrBlank() ||
                binding.etNameRegister.text.toString().isNullOrBlank() ||
                binding.spGenderRegister.text.toString().isNullOrBlank() ||
                binding.etPasswordRegister.text.toString().isNullOrBlank()
            )
                Toast.makeText(requireContext(), getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
            else   registerUser()
        }
    }

    private fun registerUser() {
        viewModel.createUser(
            email = binding.etEmailRegister.text.toString(),
            password = binding.etPasswordRegister.text.toString(),
            name = binding.etNameRegister.text.toString(),
            gender = binding.spGenderRegister.text.toString(),
            age = binding.etAgeRegister.text.toString().toInt())
    }

    private fun loadViewModels() {
        viewModel.userCreated.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), getString(R.string.userCreated_msg), Toast.LENGTH_SHORT).show()
            (requireActivity() as MainActivity).changeFragment(LoginFragment.newInstance())
        })
        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), getString(R.string.error_msg_generic), Toast.LENGTH_SHORT).show()
        })
    }


}