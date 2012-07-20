package fixtgen.testservice;

import java.util.HashMap;
import java.util.Map;

import fixtgen.generators.AbstractGenerator;
import fixtgen.main.FixtureType;
import fixtgen.preferences.IPreferenceManager;

public class MockPreferenceManager implements IPreferenceManager {

	public static final String DO_IMPORT_CLASSES = "test";

	public static final String COLUMN_IMPORT_CLASSES = "test";

	public static final String ROW_IMPORT_CLASSES = "test";

	public static final String DO_PARENT_CLASS = "DoFixture";

	public static final String COLUMN_PARENT_CLASS = "ColumnFixture";

	public static final String ROW_PARENT_CLASS = "RowFixture";
	
	
	
	
	@SuppressWarnings("serial")
	private static final Map<String, String> preferences = new HashMap<String, String>() {{
		
		/* Import classes */
		put(
			FixtureType.DO.getName() + AbstractGenerator.IMPORT_CLASSES_PREF_POSTFIX,
			DO_IMPORT_CLASSES
		);
		put(
			FixtureType.COLUMN.getName() + AbstractGenerator.IMPORT_CLASSES_PREF_POSTFIX,
			COLUMN_IMPORT_CLASSES
	    );
		put(
			FixtureType.ROW.getName() + AbstractGenerator.IMPORT_CLASSES_PREF_POSTFIX,
			ROW_IMPORT_CLASSES
	    );
		
		/* Parent classes*/
		put(
			FixtureType.DO.getName() + AbstractGenerator.PARENT_CLASS_PREF_POSTFIX,
			DO_PARENT_CLASS
		);
		put(
			FixtureType.COLUMN.getName() + AbstractGenerator.PARENT_CLASS_PREF_POSTFIX,
			COLUMN_PARENT_CLASS
	    );
		put(
			FixtureType.ROW.getName() + AbstractGenerator.PARENT_CLASS_PREF_POSTFIX,
			ROW_PARENT_CLASS
	    );
	}};
	
	@Override
	public String getPreference(String key) {
		
		return preferences.get(key);
	}

}
