package com.example.presentation.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.data.module.UserInfoModule
import com.example.presentation.signup.SignUpFragment.Companion.USER_BUNDLE_KEY
import com.example.presentation.R
import com.example.presentation.databinding.FragmentSignInBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding: FragmentSignInBinding by viewBinding()
    private val vm: SignInViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnSignInClickListener()
        setOnSignUpClickListener()
        observeViewModel()
    }

    private fun observeViewModel() = with(vm) {

        errorLoginEvent.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                getString(R.string.fragment_sign_in_error_no_user_found),
                Toast.LENGTH_SHORT
            ).show()
        }

        successLoginEvent.observe(viewLifecycleOwner) {
            Bundle().putSerializable(USER_BUNDLE_KEY, UserInfoModule(it.email))

            findNavController().navigate(R.id.action_signInFragment_to_musicPlayerFragment)
        }
    }

    private fun setOnSignInClickListener() = binding.btnLogin.setOnClickListener {

        val textOfEmailEt = binding.etEmail.text.toString()
        val textOfPasswordEt = binding.etPassword.text.toString()

        vm.checkUser(textOfEmailEt, textOfPasswordEt)
    }

    private fun setOnSignUpClickListener() = binding.tvRegister.setOnClickListener {
        findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
    }
}