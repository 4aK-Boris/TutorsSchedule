package dmitriy.losev.firebase.core

import android.app.Activity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import java.util.concurrent.Executor

class FirebaseTask<T : Any>(private val data: T) : Task<T>() {

    override fun addOnFailureListener(p0: OnFailureListener): Task<T> = this

    override fun addOnFailureListener(p0: Activity, p1: OnFailureListener): Task<T> = this

    override fun addOnFailureListener(p0: Executor, p1: OnFailureListener): Task<T> = this

    override fun getException(): Exception? = null

    override fun getResult(): T = data

    override fun <X : Throwable?> getResult(p0: Class<X>): T = result

    override fun isCanceled(): Boolean = false

    override fun isComplete(): Boolean = true

    override fun isSuccessful(): Boolean = true

    override fun addOnSuccessListener(p0: Executor, p1: OnSuccessListener<in T>): Task<T> = this

    override fun addOnSuccessListener(p0: Activity, p1: OnSuccessListener<in T>): Task<T> = this

    override fun addOnSuccessListener(p0: OnSuccessListener<in T>): Task<T> = this
}