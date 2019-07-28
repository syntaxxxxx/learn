package com.syntax.learn.screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.syntax.learn.R
import com.syntax.learn.common.Load
import com.syntax.learn.common.extensions.errorMessage
import com.syntax.learn.common.extensions.fragmentViewModel
import com.syntax.learn.common.extensions.navigate
import com.syntax.learn.common.extensions.setupNavigator
import com.syntax.learn.screen.main.MainNavigator
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by fragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigator(this, viewModel, useActivityHandler = true)

       btnLogin.setOnClickListener {
            viewModel.doLogin(editUsername.text.toString(), editPassword.text.toString())
        }

        textForgotPass.setOnClickListener {
            navigate(MainNavigator.Profile(), useActivityHandler = true)
        }
        txtRegister.setOnClickListener {
            navigate(MainNavigator.Register(), useActivityHandler = true)
        }

        viewModel.loginLoad.observe(this, Observer {
            progressBar.visibility = if (it is Load.Loading) View.VISIBLE else View.GONE
            if (it is Load.Fail) {
                Toast.makeText(
                    requireContext(),
                    it.error.errorMessage(),
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}
