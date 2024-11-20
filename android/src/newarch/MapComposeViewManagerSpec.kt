package com.mapcompose

import android.view.View

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.viewmanagers.MapComposeViewManagerDelegate
import com.facebook.react.viewmanagers.MapComposeViewManagerInterface

abstract class MapComposeViewManagerSpec<T : View> : SimpleViewManager<T>(), MapComposeViewManagerInterface<T> {
  private val mDelegate: ViewManagerDelegate<T>

  init {
    mDelegate = MapComposeViewManagerDelegate(this)
  }

  override fun getDelegate(): ViewManagerDelegate<T>? {
    return mDelegate
  }
}
