package com.bignerdranch.android.androidtask1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.bignerdranch.android.androidtask1.databinding.FragmentBlankBinding


class BlankFragment : Fragment() {
    lateinit var editText: EditText
    lateinit var v: View
    lateinit var binding: FragmentBlankBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlankBinding.inflate(layoutInflater)
        v = inflater.inflate(R.layout.fragment_blank, container, false)
        editText = v.findViewById(R.id.input_field)

        binding.inputField.onTextChange {
            showToast(it)
        }
        return binding.root
    }


    inline fun EditText.onTextChange(crossinline listener: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //NO OP
            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                listener(charSequence.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
                //NO OP
            }
        })
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}
