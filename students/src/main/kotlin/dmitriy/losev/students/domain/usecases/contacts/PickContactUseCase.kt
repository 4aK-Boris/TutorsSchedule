package dmitriy.losev.students.domain.usecases.contacts

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import android.provider.ContactsContract.PhoneLookup
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import dmitriy.losev.students.core.StudentsBaseUseCase


class PickContactUseCase(
    private val context: Context,
    private val readContactsPermissionUseCase: ReadContactsPermissionUseCase
) : StudentsBaseUseCase() {

    fun pickContact(intent: Intent?): String? {
        var phoneNumber: String? = null
        intent?.data?.let { uri ->
            val resolver = context.contentResolver
            val queryFields = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
            resolver.query(uri, queryFields, null, null, null)?.let { cursor ->
                cursor.moveToNext()
                phoneNumber = cursor.getString(0)
                cursor.close()
            }
        }
        return phoneNumber
    }

    @OptIn(ExperimentalPermissionsApi::class)
    fun pickContactIntent(
        permissionState: PermissionState,
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
    ) {
        if (permissionState.status.isGranted) {
            val intent = Intent(Intent.ACTION_PICK, PhoneLookup.CONTENT_FILTER_URI).apply {
                type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            }
            launcher.launch(intent)
        } else {
            readContactsPermissionUseCase.requestPermission(permissionState)
        }
    }
}
