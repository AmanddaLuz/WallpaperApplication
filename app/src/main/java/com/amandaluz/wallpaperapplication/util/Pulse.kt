package com.amandaluz.wallpaperapplication.util

import android.view.View
import android.view.ViewPropertyAnimator
var viewPropertyAnimator: ViewPropertyAnimator? = null
fun View.pulseAnimation() {
    repeat(1000) {
        viewPropertyAnimator = this.animate()
            .scaleX(4f)
            .scaleY(4f)
            .alpha(0f)
            .setDuration(1000)
            .withEndAction {
                this.scaleX = 1f
                this.scaleY = 1f
                this.alpha = 1f
                pulseAnimation()
            }
    }
}
fun View.animationCancel() {
    visibility = View.GONE
    viewPropertyAnimator?.cancel()
}