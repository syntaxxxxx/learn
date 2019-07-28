package com.syntax.learn.screen.register


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.syntax.learn.R
import com.syntax.learn.common.Load
import com.syntax.learn.common.extensions.errorMessage
import com.syntax.learn.common.extensions.fragmentViewModel
import com.syntax.learn.common.extensions.setupNavigator
import kotlinx.android.synthetic.main.fragment_register.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class RegisterFragment : Fragment() {
    private val viewModel : RegisterViewModel by fragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigator(this, viewModel, useActivityHandler = true)
        btnRegister.setOnClickListener {
            viewModel.doRegister(username.text.toString(), email.text.toString(), password.text.toString(), password2.text.toString())
        }

        viewModel.registerLoad.observe(this, Observer {
            pbRegister.visibility = if (it is Load.Loading) View.VISIBLE else View.GONE
            if (it is Load.Fail) {
                Toast.makeText(
                    requireContext(),
                    it.error.errorMessage(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }


}
