package wu.jay.citadelsshuffle;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class Settings extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// load preferences
		addPreferencesFromResource(R.xml.prefs);
	}
}
