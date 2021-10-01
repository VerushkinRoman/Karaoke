package com.posse.android.karaoke.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class FilesystemWorkerImpl : FilesystemWorker {

    override fun saveFile(directory: File, name: String, drawable: Drawable?) {
        drawable?.let {
            val bitmap = drawableToBitmap(it)
            val imageFile = File(directory, name)
            if(!imageFile.exists()){
                imageFile.parentFile?.mkdirs()
                imageFile.createNewFile()
            }

            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(imageFile)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                fos.close()
            } catch (e: IOException) {
                Log.e("app", e.message.toString())
                if (fos != null) {
                    try {
                        fos.close()
                    } catch (e1: IOException) {
                        e1.printStackTrace()
                    }
                }
            }
        }
    }

    override fun getDrawable(directory: File, name: String, context: Context): Drawable? {
        val imageFile = File(directory, name)
        return if (imageFile.exists()) {
            val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
            BitmapDrawable(context.resources, bitmap)
        } else null
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            if (drawable.bitmap != null) {
                return drawable.bitmap
            }
        }
        val bitmap: Bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
        } else {
            Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        }
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}