package com.studio.secretmessage

import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController

class MessageFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = view.findNavController()
        val messageView = view.findViewById<EditText>(R.id.message)

        view.findViewById<Button>(R.id.next).setOnClickListener {
            val message = messageView.text.toString()
            val action = MessageFragmentDirections.actionMessageFragmentToEncryptFragment(message)
            navController.navigate(action)
        }
    }
}