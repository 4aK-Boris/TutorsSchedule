package dmitriy.losev.tutorsschedule.core

import android.app.Application
import androidx.appcompat.content.res.AppCompatResources
import com.vk.api.sdk.VK
import com.vk.auth.main.VkClientUiInfo
import com.vk.superapp.SuperappKit
import com.vk.superapp.SuperappKitConfig
import com.vk.superapp.core.SuperappConfig
import dmitriy.losev.tutorsschedule.R


object VkDevUtils {

    fun initSuperAppKit(context: Application) {

        val appName = context.getString(R.string.app_name)
        val clientSecret = context.getString(R.string.vk_client_secret)

        val appInfo = SuperappConfig.AppInfo(
            appName,
            VK.getAppId(context).toString(),
            context.packageManager.getPackageInfo(context.packageName, 0).versionName
        )

        val icon = AppCompatResources.getDrawable(context, R.mipmap.ic_launcher)!!

        val config = SuperappKitConfig.Builder(context)
            .setAuthModelData(clientSecret = clientSecret)
            .setAuthUiManagerData(clientUiInfo = VkClientUiInfo(icon, appName))
            .setLegalInfoLinks(
                serviceUserAgreement = "https://id.vk.com/terms",
                servicePrivacyPolicy = "https://id.vk.com/privacy"
            )
            .setApplicationInfo(version = appInfo)
            .setUseCodeFlow(isUseCodeFlow = true)
            .sslPinningEnabled(enable = false)
            .build()

        SuperappKit.init(config)
    }
}