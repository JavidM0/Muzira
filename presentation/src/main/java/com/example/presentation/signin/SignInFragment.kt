package com.example.presentation.signin

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.R
import com.example.presentation.databinding.FragmentSignInBinding
import com.example.ui_kit.`ui-kit`.viewmodel.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding: FragmentSignInBinding by viewBinding()
    private val viewModel: SignInViewModelApi by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnSignInParentClickListener()
        setOnSignInClickListener()
        observeViewModel()
    }

    private fun observeViewModel() = with(viewModel) {
        errorLoginEvent.bind(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                getString(R.string.sign_in_error_no_user_found),
                Toast.LENGTH_SHORT
            ).show()
        }

        successLoginEvent.bind(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_signInFragment_to_musicPlayerFragment)
        }
    }

    private fun setOnSignInParentClickListener() {
        binding.signInParentLayout.setOnClickListener {
            val inputMethodManager =
                requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                requireActivity().currentFocus!!.windowToken,
                0
            )
        }
    }

    private fun setOnSignInClickListener() = binding.btnLogin.setOnClickListener {
        val textOfEmailEt = binding.etEmail.text.toString()
        val textOfPasswordEt = binding.etPassword.text.toString()
        viewModel.checkUser(textOfEmailEt, textOfPasswordEt)
    }
}
