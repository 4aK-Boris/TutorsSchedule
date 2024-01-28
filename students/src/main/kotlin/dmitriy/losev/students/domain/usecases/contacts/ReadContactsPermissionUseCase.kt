package dmitriy.losev.students.domain.usecases.contacts

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import dmitriy.losev.students.core.StudentsBaseUseCase

class ReadContactsPermissionUseCase : StudentsBaseUseCase() {

    @OptIn(ExperimentalPermissionsApi::class)
    fun requestPermission(permissionState: PermissionState){
        permissionState.launchPermissionRequest()
    }
}