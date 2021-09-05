package com.pascaciorc.checkpoint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pascaciorc.checkpoint.databinding.FragmentInputBinding
import com.pascaciorc.checkpoint.utils.hideKeyboard
import com.pascaciorc.checkpoint.utils.showKeyboard

class InputFragment : Fragment() {

    val args: InputFragmentArgs by navArgs()
    private lateinit var binding: FragmentInputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputBinding.inflate(inflater, container, false)

        requireContext().showKeyboard(binding.inputEditText)

        binding.cancelButton.setOnClickListener {
            activity?.hideKeyboard()
            findNavController().navigateUp()
        }

        return binding.root
    }
}