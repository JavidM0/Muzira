package com.example.muzira

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.muzira.databinding.FragmentHomePageBinding

class HomePageFragment : Fragment(R.layout.fragment_home_page) {

    private val binding: FragmentHomePageBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnGetStarted.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_signUpFragment)
        }
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_signInFragment)
        }
    }
}