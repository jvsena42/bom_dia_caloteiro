package com.bulletapps.bomdiacaloteiro.util

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore

const val NEGATIVE = -1
const val EMPTY_STRING = ""
const val BR_CODE = "+55"

fun intentShare(text: String, uri: Uri) = Intent().apply {
    action = Intent.ACTION_SEND
    putExtra(Intent.EXTRA_TEXT, text)
    putExtra(Intent.EXTRA_STREAM, uri)
    type = "image/*"
}

fun Context.getBitmapFromResource(imageRef: Int) = BitmapFactory.decodeResource(resources, imageRef)

fun Context.getUriResource(resourceId: Int) = (Uri.Builder())
    .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
    .authority(resources.getResourcePackageName(resourceId))
    .appendPath(resources.getResourceTypeName(resourceId))
    .appendPath(resources.getResourceEntryName(resourceId))
    .build()

fun Context.getImageUri(resourceId: Int) = Uri.parse(
    MediaStore.Images.Media.insertImage(this.contentResolver,
    BitmapFactory.decodeResource(resources, resourceId), null, null))