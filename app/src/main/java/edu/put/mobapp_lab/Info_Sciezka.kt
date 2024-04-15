package edu.put.mobapp_lab

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ShareActionProvider
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat
import android.Manifest
import android.graphics.Bitmap


class Info_Sciezka : AppCompatActivity() {
    private var shareActionProvider: ShareActionProvider? = null
    private var shareContent: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sciezka_info_xml)
        val fragment: Sciezka_Fragment? =
            supportFragmentManager.findFragmentById(R.id.detail_fragment) as Sciezka_Fragment?
        val walkID = intent.extras!![EXTRA_WALK_ID] as Int
        val walkCategory = intent.extras!![EXTRA_WALK_CATEGORY] as String?
        if (fragment != null) {
            fragment.setWalkID(walkID, walkCategory)
        }

        val sciezka =
            if (walkCategory == "Łatwe ścieżki") Sciezka.info_easy[walkID] else if (walkCategory == "Cieżkie ścieżki") Sciezka.info_hard[walkID] else Sciezka.info_easy[walkID]
        val imageView = findViewById<View>(R.id.sciezka_obrazek) as ImageView
        imageView.setImageDrawable(ContextCompat.getDrawable(this, sciezka.imageId))

        shareContent = sciezka.toString()
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.trail_main, menu)
        val menuItem = menu.findItem(R.id.action_share)
        shareActionProvider = MenuItemCompat.getActionProvider(menuItem) as ShareActionProvider?
        setShareActionIntent(shareContent)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setShareActionIntent(text: String?) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, text)
        shareActionProvider!!.setShareIntent(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.author_action) {
            val intent = Intent(this, Info_Autor::class.java)
            startActivity(intent)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    fun onClickPhoto(view: View) {
        // Check for camera permission
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is granted, open the camera
            openCamera()
        } else {
            // Permission is not granted, request for permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
        }
    }

    // Handle the result of the permission request
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CAMERA_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted, open the camera
                    openCamera()
                } else {
                    // Permission was denied, show a message to the user
                    Toast.makeText(this, "Camera permission is required to take photos.", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }


    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // Display error message
            Toast.makeText(this, "Unable to open camera", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            // Use the image captured from the camera
        }
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val REQUEST_CAMERA_PERMISSION = 2

            const val EXTRA_WALK_ID = "0"
            const val EXTRA_WALK_CATEGORY = "Łatwe ścieżki"

    }



}