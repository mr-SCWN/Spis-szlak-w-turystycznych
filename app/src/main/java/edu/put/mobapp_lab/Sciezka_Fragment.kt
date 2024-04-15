package edu.put.mobapp_lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


class Sciezka_Fragment : Fragment() {
    private var walkID: Long = 0
    private var walkCategory: String? = null
    fun setWalkID(id: Int, category: String?) {
        walkID = id.toLong()
        walkCategory = category
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val timer = Oblicznaie_Czasu()
            val ft = getChildFragmentManager().beginTransaction()
            ft.add(R.id.timer_container, timer)
            ft.addToBackStack(null)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        } else {
            walkID = savedInstanceState.getLong("walkID")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sciezka_fragment_xml_, container, false)
    }

    override fun onStart() {
        super.onStart()
        val view = view
        if (view != null) {
            val sciezka = when (walkCategory) {
                "Łatwe ścieżki" -> Sciezka.info_easy[walkID.toInt()]
                "Cieżkie ścieżki" -> Sciezka.info_hard[walkID.toInt()]
                else -> Sciezka.info_easy[walkID.toInt()]
            }
            val title = view.findViewById<TextView>(R.id.sciezka_tytul)
            title.text = sciezka.name
            val count = view.findViewById<TextView>(R.id.poziom_sciezki)
            count.text = sciezka.level
            val info_l = view.findViewById<TextView>(R.id.krotki_opis)
            info_l.text = sciezka.info_cities
            val info_m = view.findViewById<TextView>(R.id.duzy_opis)
            info_m.text = sciezka.info_tour

        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putLong("walkID", walkID)
    }
}