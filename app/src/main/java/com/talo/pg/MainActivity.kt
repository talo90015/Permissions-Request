package com.talo.pg


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.talo.pg.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //Manifast宣告權限(非危險與危險權限)
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            requestPermissions()
        }


    }
    //檢查權限函數是否符合返回內容
    private fun hasWriteExternalStoragePermissions() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    //檢查權限是否接受許可
    private fun requestPermissions(){
        val permissionsToRequest = mutableListOf<String>()
        if (!hasWriteExternalStoragePermissions()){
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (permissionsToRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), 0)
        }
    }

    //檢查是否有取得的權限代碼
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 0 && grantResults.isNotEmpty()){
            for (i in grantResults.indices ){
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.d("PermissionsRequest", "${permissions[i]} granted")
                }
            }
        }
    }
}