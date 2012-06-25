package fixtgen.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import fixtgen.main.Activator;

public class FixtGenPreferenceInitializer extends AbstractPreferenceInitializer {

    public FixtGenPreferenceInitializer() {
        super();
    }

    @Override
    public void initializeDefaultPreferences() {

        IPreferenceStore store = Activator.getDefault().getPreferenceStore();

        store.setDefault("DoFixtureParentClass", "DoSymbolFixture");
        store.setDefault("RowFixtureParentClass", "RowSymbolFixture");
        store.setDefault("ColumnFixtureParentClass", "ColumnSymbolFixture");

        store.setDefault("DoFixtureImportClasses", "com.boeing.eid.odt.fitnesse.common.DoSymbolFixture");
        store.setDefault("RowFixtureImportClasses", "com.boeing.eid.odt.fitnesse.common.RowSymbolFixture");
        store.setDefault("ColumnFixtureImportClasses", "com.boeing.eid.odt.fitnesse.common.ColumnSymbolFixture");
    }
}
