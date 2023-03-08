package com.example.presentation.signup

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.R
import com.example.presentation.databinding.FragmentSignUpBinding
import com.example.presentation.model.User
import com.example.ui_kit.`ui-kit`.viewmodel.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val binding: FragmentSignUpBinding by viewBinding()
    private val viewModel: SignUpViewModelApi by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnSignUpParentClickListener()
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
        invalidEmailEvent.bind(viewLifecycleOwner) {
            resetErrors()
            binding.emailTextInputLayout.error =
                getString(R.string.sign_up_error_invalid_email)
        }

        invalidPasswordEvent.bind(viewLifecycleOwner) {
            resetErrors()
            binding.passwordTextInputLayout.error =
                getString(R.string.sign_up_error_invalid_password)
        }

        invalidConfirmPasswordEvent.bind(viewLifecycleOwner) {
            resetErrors()
            binding.confirmPasswordTextInputLayout.error =
                getString(R.string.sign_up_error_empty_confirm_password)
        }

        successValidationEvent.bind(viewLifecycleOwner) {
            resetErrors()
            val bundle = Bundle()
            val textOfEmailEt = binding.etEmail.text.toString()
            val textOfPasswordEt = binding.etPassword.text.toString()

            bundle.putSerializable(USER_BUNDLE_KEY, it)
            viewModel.registerUser(User(0, textOfEmailEt, textOfPasswordEt))
            findNavController().navigate(R.id.action_signUpFragment_to_musicPlayerFragment)
        }
    }

    private fun setOnSignUpParentClickListener() {
        binding.signUpParentLayout.setOnClickListener {
            val inputMethodManager =
                requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                requireActivity().currentFocus!!.windowToken,
                0
            )
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
