package fixtgen.views;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import fixtgen.main.FixtureType;

public class FixtGenPage extends WizardPage {
    
    Text input;
    
    Button radioButtonDo;
    Button radioButtonRow;
    Button radioButtonColumn;

    
    protected FixtGenPage(final String pageName) {
        super(pageName);
        setTitle("Fixture Generator");
        setDescription("Please enter fitnesse-format table and select a fixture type");
    }
    
    @Override
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);     
       
        GridLayout gridLayout = new GridLayout();
        FillLayout fillLayout = new FillLayout();
        FormLayout formLayout = new FormLayout();
        
        fillLayout.type = SWT.VERTICAL;
        
        gridLayout.numColumns = 1;
        
        composite.setLayout(formLayout);
        setControl(composite);
        
        Label label = new Label(composite, SWT.LEFT);
        label.setText("Insert fitnesse table for fixture generation:");
        FormData labelData = new FormData();
        labelData.top = new FormAttachment(5, 5);
        labelData.left = new FormAttachment(0, 5);
        label.setLayoutData(labelData);
        
        FormData inputData = new FormData();
        inputData.top = new FormAttachment(label, 5);
        inputData.left = new FormAttachment(0, 5);
        inputData.bottom = new FormAttachment(60, 5);
        inputData.right = new FormAttachment(99, 5);
        
        input = new Text(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        input.setLayoutData(inputData);
        
        Label label2 = new Label(composite, SWT.LEFT);
        label2.setText("Choose a fixture type:");
        FormData label2Data = new FormData();
        label2Data.top = new FormAttachment(input, 15);
        label2Data.left = new FormAttachment(0, 5);
        label2.setLayoutData(label2Data);
        
        FormData button1Data = new FormData();
        button1Data.top = new FormAttachment(label2, 15);
        button1Data.left = new FormAttachment(0, 5);
        button1Data.right = new FormAttachment(10, 50);
        
        radioButtonDo = new Button(composite, SWT.RADIO);
        radioButtonDo.setText(FixtureType.DO.getName());
        radioButtonDo.setBounds(10, 30, 75, 20);
        radioButtonDo.setLayoutData(button1Data);
        
        FormData button2Data = new FormData(60, 60);
        button2Data.top = new FormAttachment(radioButtonDo, 0);
        button2Data.left = new FormAttachment(0, 5);
        button2Data.right = new FormAttachment(10, 50);
        
        radioButtonRow = new Button(composite, SWT.RADIO);
        radioButtonRow.setText(FixtureType.ROW.getName());
        radioButtonRow.setBounds(10, 45, 75, 20);
        radioButtonRow.setLayoutData(button2Data);
        
        FormData button3Data = new FormData();
        button3Data.top = new FormAttachment(radioButtonRow, 0);
        button3Data.left = new FormAttachment(0, 5);
        button3Data.right = new FormAttachment(10, 50);
        
        radioButtonColumn = new Button(composite, SWT.RADIO);
        radioButtonColumn.setText(FixtureType.COLUMN.getName());
        radioButtonColumn.setBounds(10, 60, 75, 20);
        radioButtonColumn.setLayoutData(button3Data);
    }
    
    public String getInputTable() {
        return this.input.getText();
    }
    
    public FixtureType getFixtureType() {
        
        FixtureType result = null;
        
        if (radioButtonDo.getSelection()) {
            result = FixtureType.DO;
        } else if (radioButtonRow.getSelection()) {
            result = FixtureType.ROW;
        } else if (radioButtonColumn.getSelection()) {
            result = FixtureType.COLUMN;
        }
        
        return result;
    }
    
}
