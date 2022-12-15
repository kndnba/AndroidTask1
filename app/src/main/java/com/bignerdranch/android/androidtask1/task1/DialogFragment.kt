package com.bignerdranch.android.androidtask1.task1

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.bignerdranch.android.androidtask1.R


class DialogFragment : DialogFragment() {
    var index: Int = 0
    var message = ""

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { it ->
            val builder = AlertDialog.Builder(it)

                builder.setTitle((index+1).toString())
                builder.setMessage(message)
                .setPositiveButton(
                    R.string.ok,
                    DialogInterface.OnClickListener { dialog, id -> dialog.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}