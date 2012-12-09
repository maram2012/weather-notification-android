/*
 *  Android Weather Notification.
 *  Copyright (C) 2010  Denis Nelubin aka Gelin
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 *  http://gelin.ru
 *  mailto:den@gelin.ru
 */

package ru.gelin.android.weather.notification.app;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.SwitchPreference;
import ru.gelin.android.weather.notification.skin.SkinInfo;

import java.util.List;

import static ru.gelin.android.weather.notification.PreferenceKeys.ENABLE_NOTIFICATION;
import static ru.gelin.android.weather.notification.app.PreferenceKeys.SKINS_CATEGORY;

/**
 *  Main activity for ICS and later Androids.
 */
public class MainActivity4 extends BaseMainActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: replace with master On/Off switch
        Preference notificationPreference = findPreference(ENABLE_NOTIFICATION);
        notificationPreference.setOnPreferenceChangeListener(this);
    }

    protected void fillSkinsPreferences(List<SkinInfo> skins) {
        PreferenceCategory skinsCategory = (PreferenceCategory)findPreference(SKINS_CATEGORY);
        for (SkinInfo skin : skins) {
            SwitchPreference switchPref = skin.getSwitchPreference(this);
            switchPref.setOnPreferenceChangeListener(this);
            skinsCategory.addPreference(switchPref);
        }
    }

    //@Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String key = preference.getKey();
        if (ENABLE_NOTIFICATION.equals(key)) {
            //force reschedule service start
            startUpdate(false);
            return true;
        }
        return super.onPreferenceChange(preference, newValue);
    }
    
}