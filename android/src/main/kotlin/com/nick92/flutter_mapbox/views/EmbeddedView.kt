package com.nick92.flutter_mapbox.views

import android.app.Activity
import android.view.View
import android.content.Context
import android.util.Log
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapboxMapOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.loader.MapboxMapsInitializer
import com.nick92.flutter_mapbox.EmbeddedNavigationView
import com.nick92.flutter_mapbox.databinding.MapActivityBinding
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView
import timber.log.Timber


class EmbeddedView(context: Context, activity: Activity, binding: MapActivityBinding, binaryMessenger: BinaryMessenger, vId: Int, args: Any?, accessToken: String)
    : PlatformView, EmbeddedNavigationView(context, activity, binding, accessToken) {
    private val viewId: Int = vId
    private val messenger: BinaryMessenger = binaryMessenger
    init{
        val arguments = args as Map<*, *>
        initFlutterChannelHandlers()
        initNavigation(arguments)
    }

    override fun initFlutterChannelHandlers() {
        methodChannel = MethodChannel(messenger, "flutter_mapbox/${viewId}")
        eventChannel = EventChannel(messenger, "flutter_mapbox/${viewId}/events")
        super.initFlutterChannelHandlers()
    }

    override fun getView(): View {
        return binding.root;
    }

    override fun dispose() {
        unregisterObservers();
        onDestroy();
    }

}