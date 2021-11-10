package com.example.mysocialnetwork.featureUserPrefs.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.UserPrefsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserPrefsFragment : Fragment(R.layout.user_prefs_fragment) {

    companion object {
        fun newInstance() = UserPrefsFragment()
    }

    private val viewModel: UserPrefsViewModel by viewModel()
    private lateinit var binding: UserPrefsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = UserPrefsFragmentBinding.bind(view)
    }

}