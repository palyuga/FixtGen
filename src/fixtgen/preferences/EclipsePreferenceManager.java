package fixtgen.preferences;

import fixtgen.main.Activator;

public class EclipsePreferenceManager implements IPreferenceManager {

	private static final IPreferenceManager instance = new EclipsePreferenceManager();
	
	private EclipsePreferenceManager() {}
	
	public static IPreferenceManager getInstance() {
		return instance;
	}
	
	@Override
	public String getPreference(final String key) {
		return Activator.getDefault().getPreferenceStore().getString(key);
	}

}
