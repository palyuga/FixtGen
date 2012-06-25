package fixtgen.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import fixtgen.main.Activator;

public class FixtGenPreferencePage 
    extends FieldEditorPreferencePage 
    implements IWorkbenchPreferencePage {

    public FixtGenPreferencePage() {
        super(GRID);
    }

    public void createFieldEditors() {
        
        addField(new StringFieldEditor("DoFixtureParentClass", "DoFixture parent:",
                getFieldEditorParent()));
        addField(new StringFieldEditor("RowFixtureParentClass", "RowFixture parent:",
                getFieldEditorParent()));
        addField(new StringFieldEditor("ColumnFixtureParentClass", "ColumnFixture parent:",
                getFieldEditorParent()));
        
        addField(new StringFieldEditor("DoFixtureImportClasses", "DoFixture imports:",
                getFieldEditorParent()));
        addField(new StringFieldEditor("RowFixtureImportClasses", "RowFixture imports:",
                getFieldEditorParent()));
        addField(new StringFieldEditor("ColumnFixtureImportClasses", "ColumnFixture imports:",
                getFieldEditorParent()));
        
    }

    @Override
    public void init(IWorkbench workbench) {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("Fixture Generator settings");
    }
}