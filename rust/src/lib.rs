#![cfg(target_os="android")]
#![allow(non_snake_case)]

use std::ffi::{CString, CStr};
use jni::JNIEnv;
use jni::objects::{JObject, JString};
use jni::sys::{jstring};

// 命名規則が気持ち悪いが、Java_アンドロイドのパス？_ファイル名_Rustの関数名
// アプリ名にアンダーバーを入れるとアンダーバーの直後に1を入れる必要がある
// ex) zerochain_sample_app -> zerochain_1sample_1app
#[no_mangle]
pub unsafe extern "C" fn Java_sample_example_com_zerochain_1sample_1app_MainActivity_hello(env: JNIEnv, _: JObject, j_recipient: JString) -> jstring {
    let recipient = CString::from(
        CStr::from_ptr(
            env.get_string(j_recipient).unwrap().as_ptr()
        )
    );

    let output = env.new_string("Hello ".to_owned() + recipient.to_str().unwrap()).unwrap();
    output.into_inner()
}