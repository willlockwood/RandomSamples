package com.willlockwood.lockwoodtakehome.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.willlockwood.lockwoodtakehome.R
import kotlinx.android.synthetic.main.fragment_view_page.*

class PageFragment : Fragment() {

    private lateinit var pageString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            pageString = it.getString("pageString")!!
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_view_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        writeString()
    }

    private fun writeString() {
        textView.text = pageString
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            PageFragment().apply {
                arguments = Bundle().apply {
                    putString("pageString", param1)
                }
            }
    }
}
