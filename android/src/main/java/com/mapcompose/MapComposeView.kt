package com.mapcompose

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import androidx.compose.ui.platform.ComposeView
import com.google.maps.android.compose.googleMapFactory
import kotlin.concurrent.thread

class MapComposeView : LinearLayout {
  constructor(context: Context) : super(context){
    configureComponent(context)
  }
  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
    context,
    attrs,
    defStyleAttr
  ){
    Log.d("MapComposeView","configureComponent")
    configureComponent(context)
  }
  private var composeView: ComposeView? = null  // Store reference to ComposeView

  private fun configureComponent(context: Context) {
    layoutParams = LayoutParams(
      LayoutParams.WRAP_CONTENT,
      LayoutParams.WRAP_CONTENT
    )

    composeView = ComposeView(context).also {  // Store the reference
      it.layoutParams = LayoutParams(
        LayoutParams.WRAP_CONTENT,
        LayoutParams.WRAP_CONTENT
      )

      it.setContent {
        JetComposeGoogleMapView()
      }
      addView(it)
    }
  }

  @Synchronized
  fun cleanup() {

    composeView?.let { view ->
      // Dispose composition
      view.disposeComposition()
      // Remove view from parent
      removeView(view)
      composeView!!.removeAllViews()
      // Clear reference
      composeView = null
    }
    thread {
      Runtime.getRuntime().gc()
    }
  }

  // It's good practice to also override onDetachedFromWindow
  override fun onDetachedFromWindow() {
    cleanup()
    super.onDetachedFromWindow()
  }

}
