package edu.put.mobapp_lab

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment

class Animacja_Fragment : Fragment() {
    private lateinit var mSceneView: View
    private lateinit var mIconView: View
    private lateinit var mSkyView: View
    private var mStartColor: Int = 0
    private var mEndColor: Int = 0

    companion object {
        fun newInstance(): Animacja_Fragment {
            return Animacja_Fragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.animacja_fragment_xml, container, false)
        mSceneView = view
        mIconView = view.findViewById(R.id.icon)
        mSkyView = view.findViewById(R.id.sky)
        val resources = resources
        mStartColor = resources.getColor(R.color.black, null)
        mEndColor = resources.getColor(R.color.white, null)
        startAnimation()
        return view
    }

    private fun startAnimation() {
        val iconYStart = mSceneView.top - 1500f
        val iconYEnd = mSkyView.pivotY

        val heightAnimator = ObjectAnimator.ofFloat(mIconView, "y", iconYStart, iconYEnd)
            .apply {
                duration = 3000
                interpolator = DecelerateInterpolator()
            }

        val sunsetSkyAnimator = ObjectAnimator.ofInt(mSkyView, "backgroundColor", mStartColor, mEndColor).apply {
            duration = 3000
            setEvaluator(ArgbEvaluator())
        }

        heightAnimator.start()
        sunsetSkyAnimator.start()
    }
}
