package com.willlockwood.lockwoodtakehome.preferences


import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.willlockwood.lockwoodtakehome.R
import com.willlockwood.lockwoodtakehome.activity.MainActivity

class PreferencesFragment : PreferenceFragmentCompat() {

    override fun onStart() {
        super.onStart()
        (requireActivity() as MainActivity).toggleNavBarVisibility()
    }

    override fun onPause() {
        super.onPause()
        (requireActivity() as MainActivity).toggleNavBarVisibility()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }

    override fun onDisplayPreferenceDialog(preference: Preference?) {

        val preferences = preferenceManager.sharedPreferences

        var dialogFragment: DialogFragment? = null
        if (dialogFragment != null) {
            dialogFragment.setTargetFragment(this, 0)
            dialogFragment.show(fragmentManager!!, "androidx.preference.Preference" + ".PreferenceFragment.DIALOG")
        } else {
            super.onDisplayPreferenceDialog(preference)
        }
    }

}
