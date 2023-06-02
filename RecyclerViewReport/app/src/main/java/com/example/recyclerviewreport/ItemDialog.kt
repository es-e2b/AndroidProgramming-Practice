package com.example.recyclerviewreport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.example.recyclerviewreport.MyViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.jar.Attributes.Name

class ItemDialog(private val itemPos: Int = -1): BottomSheetDialogFragment() {
    private val viewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.item_dialog_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val editTextName = view.findViewById<EditText>(R.id.editTextName)
        val editTextAddress = view.findViewById<EditText>(R.id.editTextAddress)
        val buttonOk = view.findViewById<Button>(R.id.buttonOK)

        if (itemPos >= 0) {
            with (viewModel.getItem(itemPos)) {
                editTextName.setText(Name)
                editTextAddress.setText(Address)
            }
        }

        buttonOk.setOnClickListener {
            val item = Item(
                editTextName.text.toString(),
                editTextAddress.text.toString()
            )
            if (itemPos < 0)
                viewModel.addItem(item)
            else
                viewModel.updateItem(itemPos, item)
            dismiss()
        }


    }
}