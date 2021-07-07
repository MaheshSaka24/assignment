package com.assignment.xento.core.core.utility

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Base64
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.xento.core.BaseApplication
import com.assignment.xento.other.Constants
import com.google.android.material.textfield.TextInputLayout
import java.io.ByteArrayOutputStream
import java.lang.reflect.Method
import java.text.SimpleDateFormat
import java.util.*


inline fun <T> Context.getService(string: String): T {
    return getSystemService(string) as T
}

inline fun toastShort(string: String) =
    Toast.makeText(BaseApplication.context, string, Toast.LENGTH_SHORT).show()

inline fun toastLong(string: String) =
    Toast.makeText(BaseApplication.context, string, Toast.LENGTH_LONG).show()


@SuppressLint("MissingPermission")
inline fun Context.getIMEINumber(): String? {
    val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        telephonyManager?.imei
    } else {
        telephonyManager?.deviceId
    }
}

inline fun Context.getDeviceId(): String? {
    return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
}

inline fun Context.putStringPreference(key: String, value: String): Boolean? {
    val preferenceManager = applicationContext.getPreference()
    val sharedPreferences = preferenceManager?.edit()
    sharedPreferences?.putString(key, value)
    return sharedPreferences?.commit()
}


inline fun Context.getStringPreference(key: String): String? {
    val preferenceManager = applicationContext.getPreference()
    return preferenceManager?.getString(key, "")
}

fun Context.putIntPreference(key: String, value: Int): Boolean? {
    val preferenceManager = applicationContext.getPreference()
    val sharedPreferences = preferenceManager?.edit()
    sharedPreferences?.putInt(key, value)
    return sharedPreferences?.commit()
}


fun Context.getIntPreference(key: String): Int? {
    val preferenceManager = applicationContext.getPreference()
    return preferenceManager?.getInt(key, -1)
}

fun Context.getPreference(): SharedPreferences? {
    return getSharedPreferences(
        applicationContext.packageName + "_preferences",
        Context.MODE_PRIVATE
    )
}


inline fun Context.clearAllPreference() {
    val preferenceManager = applicationContext.getPreference()
    val sharedPreferences = preferenceManager?.edit()
    sharedPreferences?.clear()
    sharedPreferences?.commit()
}


fun Activity.base64(imagePath: String): String? {
    val bm = BitmapFactory.decodeFile(imagePath)
    val baos = ByteArrayOutputStream()
    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos) // bm is the bitmap object
    return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
}

fun Activity.hideKeyboard() {
    val imm: InputMethodManager =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view: View? = this.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun AppCompatEditText.isNotEmpty(): Boolean {
    return !(this.text.toString().isNullOrEmpty())
}

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


fun Context.getLayoutInflater(): LayoutInflater? {
    return LayoutInflater.from(this)
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.GONE
}

fun removeError(vararg list: TextInputLayout) {
    for (i in list) {
        i.error = null
    }
}


fun String.endsWithFiles(okFileExtensions: Array<String>): Boolean {
    for (i in okFileExtensions) {
        if (this.endsWith(i)) {
            return true
        }
    }
    return false
}

fun Context.getLinearVerticalLayoutManger(): LinearLayoutManager {
    return LinearLayoutManager(this, RecyclerView.VERTICAL, false)
}

fun String.formatDate(): String {
    return try {
        val inputFormat = SimpleDateFormat(Constants.DATE_FORMAT)
        val parsedDate: Date = inputFormat.parse(this)
        val outputFormat = SimpleDateFormat(Constants.OUTPUT_DATE_FORMAT)
        outputFormat.format(parsedDate)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        ""
    }
}


/*inline fun Context.getUserUniqueId(): String? {
    var userid = getStringPreference(
        KEY_PREFRENCE_USER_ID
    )
    if (TextUtils.isEmpty(userid)) {
        userid = UUID.randomUUID().toString()
        putStringPreference(KEY_PREFRENCE_USER_ID, userid)
    }
    return userid
}*/

/*
fun Context.saveFile(list: List<AddUnityVehicle>): String? {
    return return try {
        */
/*if (list.isNotEmpty()) {
            for (i in list) {
                try {
                    val string = AppUtil.getBase64fromImagePath(i.workImage)
                    i.workImage = string
                } catch (throwable: Throwable) {
                }
                i.id = 0
            }
        }*//*

        val string = Gson().toJson(list).toString()
        val fileName =
            applicationContext.getUserUniqueId() + "__" + TimeUtil.getTimeStamp() + ".json"
        val fos: FileOutputStream = applicationContext.openFileOutput(
            fileName,
            Context.MODE_PRIVATE
        )
        val out: Writer = OutputStreamWriter(fos)
        out.write(string)
        out.close()
        applicationContext.getFileStreamPath(fileName).absolutePath
    } catch (e: IOException) {
        e.printStackTrace()
        ""
    }
}

*/
/** Returns the consumer friendly device name  *//*

fun getDeviceName(): String? {
    val manufacturer = Build.MANUFACTURER
    val model = Build.MODEL
    return if (model.startsWith(manufacturer)) {
        capitalize(model)
    } else capitalize(manufacturer) + " " + model
}

fun capitalize(str: String): String {
    if (TextUtils.isEmpty(str)) {
        return str
    }
    val arr = str.toCharArray()
    var capitalizeNext = true
    val phrase = StringBuilder()
    for (c in arr) {
        if (capitalizeNext && Character.isLetter(c)) {
            phrase.append(Character.toUpperCase(c))
            capitalizeNext = false
            continue
        } else if (Character.isWhitespace(c)) {
            capitalizeNext = true
        }
        phrase.append(c)
    }
    return phrase.toString()
}
*/


/**
 *
 * @param path
 * the path to the Video
 * @return a thumbnail of the video or null if retrieving the thumbnail failed.
 */
fun getVideoThumbnail(path: String?): Bitmap? {
    var bitmap: Bitmap? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
        bitmap =
            ThumbnailUtils.createVideoThumbnail(path!!, MediaStore.Images.Thumbnails.MICRO_KIND)
        if (bitmap != null) {
            return bitmap
        }
    }
    // MediaMetadataRetriever is available on API Level 8 but is hidden until API Level 10
    var clazz: Class<*>? = null
    var instance: Any? = null
    try {
        clazz = Class.forName("android.media.MediaMetadataRetriever")
        instance = clazz.newInstance()
        val method: Method = clazz.getMethod("setDataSource", String::class.java)
        method.invoke(instance, path)
        // The method name changes between API Level 9 and 10.
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD) {
            bitmap = clazz.getMethod("captureFrame").invoke(instance) as Bitmap
        } else {
            val data = clazz.getMethod("getEmbeddedPicture").invoke(instance) as ByteArray
            if (data != null) {
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
            }
            if (bitmap == null) {
                bitmap = clazz.getMethod("getFrameAtTime").invoke(instance) as Bitmap
            }
        }
    } catch (e: Exception) {
        bitmap = null
    } finally {
        try {
            if (instance != null) {
                clazz!!.getMethod("release").invoke(instance)
            }
        } catch (ignored: Exception) {
        }
    }
    return bitmap
}

/*
fun SimpleDateFormat.parseDate(timeInMillsecond: Long?): String {
    try {
        val simpleDateFormat = SimpleDateFormat(Constants.DATE_TIME_FORMATE)
        val date = Date()
        date.time = timeInMillsecond!!
        return simpleDateFormat.format(date)
    } catch (t: Throwable) {
        t.printStackTrace()
        return ""
    }
}*/

fun AppCompatEditText.disable() {
    this.isEnabled = false
    this.isFocusable = false
}


fun disable(vararg t: AppCompatEditText) {
    for (i in t) {
        i.disable()
    }
}

fun ViewGroup.disableEditText() {
    for (i in 0 until childCount) {
        if (getChildAt(i) is AppCompatEditText) {
            val editText = getChildAt(i) as AppCompatEditText
            editText.disable()
        }
    }
}


