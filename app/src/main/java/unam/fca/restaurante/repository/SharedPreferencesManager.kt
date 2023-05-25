package unam.fca.restaurante.repository

import android.content.Context

class SharedPreferencesManager(context: Context) {
    /*
     * tags to save data
     */
    private val SHARED_PREFERENCES_NAME: String = "local_shared_preferences"
    private val JSON_ORDERS: String = "json_sellers"

    /*
     * Objects
     */
    private val mContext: Context = context

    /**
     * This method removes all sharedPreferences session data
     */
    fun removeSessionData() {
        mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit().clear()
            .apply()
    }

    fun storeOrder(order: String) {
        val editor =
            mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(JSON_ORDERS, order)
        editor.apply()
    }

    fun getOrders(): String? {
        val prefs = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return prefs.getString(JSON_ORDERS, null)
    }
}
