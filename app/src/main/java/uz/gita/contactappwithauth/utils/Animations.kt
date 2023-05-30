package uz.gita.contactappwithauth.utils

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.CycleInterpolator
import androidx.appcompat.app.AppCompatActivity

class Animations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        val imgBtn: ImageView = findViewById(R.id.imgBtn)
//        imgBtn.setOnClickListener { valueAnimator(it) }
    }

    //60fps 1000/60   1 -> 16mls
//    private fun customRotateAnimation(view: View) = lifecycleScope.launchWhenResumed {
//        val duration = 2000.0
//        val refreshRate = 16L
//        val numberOfFrames = ceil(duration / refreshRate).toInt()
//        val actualAngle = (360F / numberOfFrames)
//        for (x in 1..numberOfFrames) {
//            delay(refreshRate)
//            val angle = x * actualAngle
//            view.rotation = angle
//        }
//    }

    private fun valueAnimator(view: View) {
        ValueAnimator.ofInt(0, 1000, 500).apply {
            //ofArgb(Color.BLUE, Color.YELLOW, Color.GRAY, Color.MAGENTA).apply {
            addUpdateListener {
                val color = it.animatedValue as Int
                view.x = color.toFloat()
            }

            interpolator = CycleInterpolator(2f)

            duration = 2000
            start()
        }
//        val floatValueAnimator = ValueAnimator.ofFloat(0f, 360f)
//        floatValueAnimator.addUpdateListener {
//            val angle = it.animatedValue as Float
//            view.rotation = angle
//            Log.d("FFF","angle $angle")
//        }
//        floatValueAnimator.duration = 2000
//        floatValueAnimator.start()
    }
}