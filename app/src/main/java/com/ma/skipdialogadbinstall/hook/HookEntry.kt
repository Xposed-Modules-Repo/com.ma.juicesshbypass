package com.ma.skipdialogadbinstall.hook

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.current
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.log.loggerI
import com.highcapable.yukihookapi.hook.type.android.ToastClass
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit

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
//                    afterHook {
//                        //AlertDialog.Builder(instance()).setMessage("成功").setPositiveButton("ok",null).show()
//                    }
                }
            }
        }
    }

}