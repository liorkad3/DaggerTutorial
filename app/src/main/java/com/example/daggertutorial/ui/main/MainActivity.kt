package com.example.daggertutorial.ui.main

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.daggertutorial.R
import com.example.daggertutorial.databinding.ActivityMainBinding
import com.example.daggertutorial.utils.StorageHelper
import com.example.daggertutorial.utils.elapsedTime
import com.example.daggertutorial.utils.getTime
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    companion object{
        const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigation()

//        testSavingToStorage()
    }


    private fun testSavingToStorage(){
        val storageHelper = StorageHelper(this)
        Log.d(TAG, "testSavingToStorage: sd-card = ${storageHelper.isSdAvailable}")

        val bitmap = Bitmap.createBitmap(2000, 2000, Bitmap.Config.ARGB_8888)

        val availableSd = storageHelper.sdFiles?.let { storageHelper.getAvailableBytes(it)} ?:0
        val availableInt = storageHelper.getAvailableBytes(storageHelper.internalFiles)

        Log.d(TAG, "testSavingToStorage: int: ${availableInt/1_000_000}MB")
        Log.d(TAG, "testSavingToStorage: sd: ${availableSd/1_000_000}MB")

        saveBitmapToStorage(bitmap, storageHelper.internalFiles)
        storageHelper.sdFiles?.let { saveBitmapToStorage(bitmap, it) }

    }


    private fun saveBitmapToStorage(bitmap: Bitmap, dir: File) {
        val file = File(dir, "test.png")
        val format = Bitmap.CompressFormat.PNG
        try {
            val fos = FileOutputStream(file)
            val time = getTime()
            bitmap.compress(format, 100, fos)
            Log.d(TAG, "saveBitmapToStorage: to - ${file.absolutePath} = ${elapsedTime(time)}ms")
            fos.close()
        }catch (e: FileNotFoundException){
            Log.e(TAG, "saveBitmapToStorage: failed to save bitmap to ${file.absolutePath}"
                ,e)
        }catch (e: IOException){
            Log.e(TAG, "saveBitmapToStorage: failed to close resource to ${file.absolutePath}"
                ,e)
        }

    }

    private fun setUpNavigation() {
        // Finding the Navigation Controller
//        val navController = findNavController(R.id.nav_host_fragment_container)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
                as NavHostFragment
        val navController = navHostFragment.navController
        // Setting Navigation Controller with the BottomNavigationView
        binding.bottomNav.setupWithNavController(navController)
    }

}