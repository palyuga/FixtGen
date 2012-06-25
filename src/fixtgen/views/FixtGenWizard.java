package fixtgen.views;

import org.eclipse.jface.wizard.Wizard;

import fixtgen.main.FixtureType;

public class FixtGenWizard extends Wizard {
     
    FixtGenPage fixtGenPage;
    FixtureType fixtureType;
    String inputTable;
    
    
    public void addPages() {
        fixtGenPage = new FixtGenPage("Fixture Generator");
        addPage(fixtGenPage);
    }
          
    public boolean performFinish() {
         
        if ("".equals(fixtGenPage.getInputTable().trim())) {
            return false;
        }
        this.fixtureType = fixtGenPage.getFixtureType();
        this.inputTable = fixtGenPage.getInputTable();
        
        return true;
    }           
    
    public FixtureType getFixtureType() {
        return this.fixtureType;
    }

    public String getInputTableValue() {
        return this.inputTable;
    }
}