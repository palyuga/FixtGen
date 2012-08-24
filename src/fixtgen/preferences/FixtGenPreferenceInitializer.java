package fixtgen.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import fixtgen.main.Activator;
import fixtgen.main.FixtureType;

public class FixtGenPreferenceInitializer extends AbstractPreferenceInitializer {

    public FixtGenPreferenceInitializer() {
        super();
    }

    @Override
    public void initializeDefaultPreferences() {

        IPreferenceStore store = Activator.getDefault().getPreferenceStore();

        store.setDefault(FixtureType.DO.getParentClassPrefKey(), "DoSymbolFixture");
        store.setDefault(FixtureType.ROW.getParentClassPrefKey(), "RowSymbolFixture");
        store.setDefault(FixtureType.COLUMN.getParentClassPrefKey(), "ColumnSymbolFixture");
        store.setDefault(FixtureType.SEQUENCE.getParentClassPrefKey(), "SequenceSymbolFixture");

        store.setDefault(
            FixtureType.DO.getImportClassesPrefKey(),
            "com.boeing.eid.odt.fitnesse.common.DoSymbolFixture"
        );
        store.setDefault(
            FixtureType.ROW.getImportClassesPrefKey(),
            "com.boeing.eid.odt.fitnesse.common.RowSymbolFixture"
        );
        store.setDefault(
            FixtureType.COLUMN.getImportClassesPrefKey(),
            "com.boeing.eid.odt.fitnesse.common.ColumnSymbolFixture"
        );
        store.setDefault(
            FixtureType.SEQUENCE.getImportClassesPrefKey(),
            "com.boeing.eid.odt.fitnesse.common.SequenceSymbolFixture"
        );
    }
}
