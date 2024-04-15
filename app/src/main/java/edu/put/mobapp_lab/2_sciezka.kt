package edu.put.mobapp_lab

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class `2_sciezka` : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val walkRecycler =
            inflater.inflate(R.layout.s2_sciezka_xml, container, false) as RecyclerView
        val walkNames = Sciezka.info_hard.map { it.name }.toTypedArray()
        val walkImages = Sciezka.info_hard.map { it.imageId }.toIntArray()
        val adapter = Adapt_Zdjecia(walkNames, walkImages)
        walkRecycler.adapter = adapter
        val layoutManager = GridLayoutManager(activity, 2)
        walkRecycler.layoutManager = layoutManager

        adapter.setListener(object : Adapt_Zdjecia.Listener {
            override fun onClick(position: Int) {
                val intent = Intent(activity, Info_Sciezka::class.java).apply {
                    putExtra(Info_Sciezka.EXTRA_WALK_ID, position)
                    putExtra(Info_Sciezka.EXTRA_WALK_CATEGORY, "Cieżkie ścieżki")
                }
                startActivity(intent)
            }
        })

        return walkRecycler
    }
}