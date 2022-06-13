package com.example.banking

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.example.banking.databinding.ActivityMainBinding
import com.example.banking.registration.RegistrationActivity
import com.example.common.view.validatoredittext.Validable


class MainActivity : AppCompatActivity() {

    val TAG = "workmng"

    private var mLoading: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            buttonValidate.setOnClickListener {
                isValid(root)
//                Log.d("DOUBLE_CLICK", "click")
//                setOneTimeWorkRequest()
            }
            buttonRegister.setOnClickListener {
                val intent = Intent(applicationContext, RegistrationActivity::class.java)
                startActivity(intent)
            }
        }

    }


    fun isValid(root: View) {
        if (root is ViewGroup) {
            for (view in root.children) {
                isValid(view)
            }
        } else if (root is Validable) {
            root.validate()
        }
    }

    companion object {
        const val KEY_COUNT = "key_count"
        val loaderLiveData: MutableLiveData<Boolean> = MutableLiveData()
    }

    private fun setOneTimeWorkRequest() {
        val workManager = WorkManager.getInstance(applicationContext)
        val data = Data.Builder()
            .putInt(KEY_COUNT, 125)
            .build()
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()
        workManager.enqueue(oneTimeWorkRequest)
        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this) {
                if (it.state.isFinished) {
                    val data = it.outputData
                    val message = data.getString(MyWorker.KEY_WORKER)
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

                }
                Log.i(TAG, it.state.name)
            }
    }
}