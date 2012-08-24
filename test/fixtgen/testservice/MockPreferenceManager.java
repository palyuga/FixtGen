package fixtgen.testservice;

import java.util.HashMap;
import java.util.Map;

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
		    FixtureType.DO.getImportClassesPrefKey(),
			DO_IMPORT_CLASSES
		);
		put(
		    FixtureType.COLUMN.getImportClassesPrefKey(),
			COLUMN_IMPORT_CLASSES
	    );
		put(
		    FixtureType.ROW.getImportClassesPrefKey(),
			ROW_IMPORT_CLASSES
	    );
		
		/* Parent classes*/
		put(
			FixtureType.DO.getParentClassPrefKey(),
			DO_PARENT_CLASS
		);
		put(
		    FixtureType.COLUMN.getParentClassPrefKey(),
			COLUMN_PARENT_CLASS
	    );
		put(
		    FixtureType.ROW.getParentClassPrefKey(),
			ROW_PARENT_CLASS
	    );
	}};
	
	@Override
	public String getPreference(String key) {		
		return preferences.get(key);
	}

}
