package be.harm.mentallist.list

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import be.harm.mentallist.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_add_item.view.txt_add_item_name

class NewItemDialog : DialogFragment() {

    private lateinit var listener: NewItemDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = targetFragment as NewItemDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context  must implement NoticeDialogListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        val dialogView = inflater.inflate(R.layout.dialog_add_item, null)
        val builder = MaterialAlertDialogBuilder(context)
            .setTitle("Add item")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                listener.onAddItemClicked(dialogView.txt_add_item_name.text.toString())
            }
        return builder.create()
    }

    interface NewItemDialogListener {
        fun onAddItemClicked(newItemName: String)
    }
}
