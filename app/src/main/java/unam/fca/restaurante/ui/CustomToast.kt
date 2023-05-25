package unam.fca.restaurante.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import unam.fca.restaurante.R


class CustomToast(context: Context): Toast(context) {
    companion object {
        fun makeText(context: Context, text: CharSequence?, duration: Int): Toast? {
            val t = Toast.makeText(context, text, duration)
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val layout: View = inflater.inflate(R.layout.custom_toast, null)
            val textView = layout.findViewById(R.id.text) as TextView
            textView.text = text
            t.setView(layout)
            return t
        }
    }
}