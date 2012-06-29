package fixtgen.actions;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.wizard.WizardDialog;

import org.eclipse.ui.IWorkbenchWindow;

import fixtgen.generators.AbstractGenerator;
import fixtgen.generators.GeneratorFactory;
import fixtgen.generators.IModelGenerator;
import fixtgen.generators.RowFixtureGenerator;
import fixtgen.main.FixtureType;
import fixtgen.main.IDataProvider;
import fixtgen.main.SimpleStringDataProvider;
import fixtgen.views.FixtGenWizard;

public class WindowAction {

    private FixtGenWizard wizard;
    private IWorkbenchWindow window;
    private AbstractGenerator generator;
    private IDataProvider dataProvider;
    private String generatedFixture;
    private String modelFixture;
    
    /**
     * The constructor.
     * @param window Eclipse workbench window
     */
    public WindowAction(IWorkbenchWindow window) {
        this.window = window;
        this.wizard = new FixtGenWizard();
    }

    public void run() {
        
        WizardDialog dialog = new WizardDialog(
            window.getShell(), 
            wizard
        );
        dialog.create();

        if (dialog.open() == IStatus.OK) {

            final FixtureType fixtureType = wizard.getFixtureType();
            final String inputTable = wizard.getInputTableValue();
            
            this.generatedFixture = generateFixture(
                fixtureType,
                inputTable
            );
            
            if (fixtureType == FixtureType.ROW) {
                this.modelFixture = generateModelForRowFixture(inputTable);
            }
        }
    }
    
    public void disposeWindows() {
        if (wizard != null) {
            wizard.dispose();
        }
    }
    

    public String getGeneratedFixture() {
        return generatedFixture;
    }
    
    public String getModelFixture() {
        return modelFixture;
    }
    
    private String generateFixture(
        final FixtureType fixtureType,
        final String inputTable
    ) {
        String result = null;
        dataProvider = new SimpleStringDataProvider(inputTable);
        createGenerator(fixtureType);
        if (getGenerator() != null) {
            result = getGenerator().generate(dataProvider);
        }
        return result;
    }

    
    private String generateModelForRowFixture(final String inputTable) {
        String result = null;
        this.dataProvider = new SimpleStringDataProvider(inputTable);
        createGenerator(FixtureType.ROW);
        if (getGenerator() != null) {
            result = ((IModelGenerator)getGenerator()).generateModel(dataProvider);
        }
        return result;
    }
    
    public String getClassName() {
        return generator.getClassName(dataProvider);
    }
    
    private void createGenerator(FixtureType fixtureType) {
        generator = GeneratorFactory.getGenerator(fixtureType);
    }
    
    public AbstractGenerator getGenerator() {
        return generator;
    }

    public boolean isModelFixtureGenerated() {
        return !("".equals(this.modelFixture));
    }

    public String getModelClassName() {
        return RowFixtureGenerator.MODEL_CLASSNAME;
    }
}