package com.ma.skipdialogadbinstall.hook

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.widget.Toast
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.type.android.DialogInterface_OnClickListenerClass
import com.highcapable.yukihookapi.hook.type.java.BooleanType
import com.highcapable.yukihookapi.hook.type.java.ObjectsClass
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import java.util.*

@InjectYukiHookWithXposed
class HookEntry : IYukiHookXposedInit {

    override fun onInit() = configs {
        // Your code here.
    }

    override fun onHook() = encase {
        // 装载需要 Hook 的 APP
        loadApp(name = "com.sonelli.juicessh") {
            // 得到需要 Hook 的 Class
            findClass(name = "com.sonelli.oi0").hook {
                // 注入要 Hook 的方法
                injectMember {
                    method {
                        name = "d"
                        replaceToTrue()
                    }.onNoSuchMethod {
                        Log.e("error",it.message,it)
                    }
                }
            }
        }
    }

}