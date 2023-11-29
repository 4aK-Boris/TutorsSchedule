package dmitriy.losev.vk.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vk.auth.api.models.AuthResult
import com.vk.auth.main.VkClientAuthCallback
import com.vk.auth.main.VkClientAuthLib
import com.vk.auth.ui.fastloginbutton.VkFastLoginButton
import dmitriy.losev.vk.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VkActivity : AppCompatActivity() {

    private lateinit var vkButtonLogin: VkFastLoginButton

    private val scope = CoroutineScope(Dispatchers.Default)

    private var isAuth = false

    private val authCallback = object : VkClientAuthCallback {
        override fun onAuth(authResult: AuthResult) {
            super.onAuth(authResult)

            Intent().apply {
                putExtra(TOKEN, authResult.accessToken)
                putExtra(EMAIL, authResult.personalData?.email)
                putExtra(UID, authResult.uid.value)
                setResult(RESULT_OK, this)
            }
            finish()
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vk)

        vkButtonLogin = findViewById(R.id.vk_button_login)

        VkClientAuthLib.addAuthCallback(authCallback)
    }

    override fun onResume() {
        super.onResume()
        if (!isAuth) {
            scope.launch {
                isAuth = true
                delay(50)
                withContext(Dispatchers.Main) {
                    vkButtonLogin.performClick()
                }
            }
        } else {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        VkClientAuthLib.removeAuthCallback(authCallback)
    }

    companion object {
        const val TOKEN = "token"
        const val EMAIL = "email"
        const val UID = "uid"
    }
}