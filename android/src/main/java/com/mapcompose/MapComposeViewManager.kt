package com.mapcompose

import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext

@ReactModule(name = MapComposeViewManager.NAME)
class MapComposeViewManager :
  SimpleViewManager<MapComposeView>() {
  override fun getName(): String {
    return NAME
  }

  public override fun createViewInstance(context: ThemedReactContext): MapComposeView {
    return MapComposeView(context)
  }

  override fun onDropViewInstance(view: MapComposeView) {
    super.onDropViewInstance(view)
    view.cleanup()
  }



  companion object {
    const val NAME = "MapComposeView"
  }
}
