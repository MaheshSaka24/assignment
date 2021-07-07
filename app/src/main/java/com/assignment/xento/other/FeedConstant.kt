package com.mygram.android.other

object FeedConstant {
    const val KEY_MAINFEED = "mainfeed"
    const val KEY_HELP = "help"
    const val KEY_SELL = "sell"
    const val KEY_KRUSHI = "farming"
    const val KEY_EMERGENCY = "emergency"
    const val KEY_MYGRAM = "mygram"
    const val KEY_HELTH = "health"


    val feedKeys = ArrayList<String>().apply {
        this.add(KEY_MAINFEED)
        this.add(KEY_HELP)
        this.add(KEY_SELL)
        this.add(KEY_KRUSHI)
        this.add(KEY_HELTH)
        this.add(KEY_EMERGENCY)
        this.add(KEY_MYGRAM)
    }

    val hashMapFeedKeys = HashMap<String, String>().apply {
        this[KEY_MAINFEED] = "मुख्यपृष्ठ"
        this[KEY_HELP] = "मदत"
        this[KEY_SELL] = "विक्री"
        this[KEY_KRUSHI] = "कृषी"
        this[KEY_HELTH] = "आरोग्य"
        this[KEY_EMERGENCY] = "इमर्जन्सी"
        this[KEY_MYGRAM] = "माझे ग्राम"
    }

    const val ARG_CATEGORY_NAME = "category_name"
    const val ARG_CATEGORY_DATA = "category_details"

}