package com.example.presentation.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.domain.entity.User
import com.example.presentation.R
import com.example.presentation.databinding.FragmentSignUpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val binding: FragmentSignUpBinding by viewBinding()
    private val viewModel: SignUpViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupOnSignUpClickListener()
        setOnSignInClickListener()
        observeViewModel()
    }

    private fun setupOnSignUpClickListener() = with(binding) {
        btnSignUp.setOnClickListener {
            viewModel.validateInputs(
                etEmail.text.toString(),
                etPassword.text.toString(),
                etConfirmPassword.text.toString(),
            )
        }
    }

    private fun observeViewModel() = with(viewModel) {
        invalidEmailEvent.observe(viewLifecycleOwner) {
            resetErrors()
            binding.emailTextInputLayout.error =
                getString(R.string.fragment_sign_up_error_invalid_email)
        }

        invalidPasswordEvent.observe(viewLifecycleOwner) {
            resetErrors()
            binding.passwordTextInputLayout.error =
                getString(R.string.fragment_sign_up_error_invalid_password)
        }

        invalidConfirmPasswordEvent.observe(viewLifecycleOwner) {
            resetErrors()
            binding.confirmPasswordTextInputLayout.error =
                getString(R.string.fragment_sign_up_error_empty_confirm_password)
        }

        successValidationEvent.observe(viewLifecycleOwner) {
            resetErrors()
            val bundle = Bundle()
            val textOfEmailEt = binding.etEmail.text.toString()
            val textOfPasswordEt = binding.etPassword.text.toString()

            bundle.putSerializable(USER_BUNDLE_KEY, it)

            viewModel.registerUser(User(0, textOfEmailEt, textOfPasswordEt))

            findNavController().navigate(R.id.action_signUpFragment_to_musicPlayerFragment)
        }
    }

    private fun setOnSignInClickListener() = binding.tvLogin.setOnClickListener {
        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
    }

    private fun resetErrors() = with(binding) {
        emailTextInputLayout.error = null
        passwordTextInputLayout.error = null
        confirmPasswordTextInputLayout.error = null
    }

    companion object {
        const val USER_BUNDLE_KEY = "USER"
    }
}