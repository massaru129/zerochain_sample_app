package sample.example.com.zerochain_sample_app

import android.os.Bundle

import io.flutter.app.FlutterActivity
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {

  private val CHANNEL = "sample.example.com.zerochain_sample_app"

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    GeneratedPluginRegistrant.registerWith(this)
    MethodChannel(flutterView, CHANNEL).setMethodCallHandler { call, result ->
      if (call.method == "hello") {
        // 適切にSystem.loadLibraryが実行され、Rust内で関数名を適切に設定できていればRustの関数を呼べる
        result.success(hello("World"))
      } else {
        result.notImplemented()
      }
    }
  }

  external fun hello(to: String): String

  companion object {
    init {
      // Rustのライブラリ名ではなく、".so"の拡張子がついているファイル名と同じ名前にする
      // rustじゃなくてもいい（まぎらわしい）
      System.loadLibrary("rust")
    }
  }
}
