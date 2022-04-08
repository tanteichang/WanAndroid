package com.tantei.wanandroid.ui.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.tantei.wanandroid.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}