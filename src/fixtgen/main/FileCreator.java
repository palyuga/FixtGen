package fixtgen.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import fixtgen.actions.*;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Class for a new file creation
 * @author DPalyuga
 * @version 1.0
 */
public class FileCreator extends AbstractHandler {
   
    ExecutionEvent executionEvent;
    WindowAction action;
 
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        this.executionEvent = event;

        IStructuredSelection selection = 
                (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
        
        Object firstElement = selection.getFirstElement();
        
        /* 
         * If selected item is package 
         * then let's create a new fixture in it
         */      
        /* TODO: Show only in package context menu */
        if (firstElement instanceof IPackageFragment) {
         
            final String generatedFixture = getFixtureCodeUsingWizard();
            
            if (generatedFixture != null) {
            
                final String className = getClassName();
                
                IPackageFragment cu = (IPackageFragment) firstElement;
                IResource res = cu.getResource();
                
                write(
                   res.getLocation().toString(),
                   className,
                   SourceCodeFormatter.format(generatedFixture)
                );                
                
                try {
                    
                    final String modelFixture = action.getModelFixture();

                    if (action.isModelFixtureGenerated()) {
                        write(
                            res.getLocation().toString(), 
                            action.getModelClassName(), 
                            SourceCodeFormatter.format(modelFixture)
                        );
                        openFile(res.getLocation().toString(), getModelClassName());
                    }
                    
                    openFile(res.getLocation().toString(), className);
                } catch (PartInitException e) {
                    MessageDialog.openError(
                        HandlerUtil.getActiveShell(event),
                        "Error", "Error occured while trying to open new file"
                    );
                } catch (FileNotFoundException e) {
                    MessageDialog.openError(
                        HandlerUtil.getActiveShell(event),
                        "Error", "File not found"
                    );
                }
            }            
        } else {
            /* If it wasn't a package then blame user */ 
            MessageDialog.openInformation(
                HandlerUtil.getActiveShell(event),
                "Information", "Please select a package for fixture creation"
            );
        }
        disposeWindows();
        return null;
    }
    

    /**
     * This method opens the java class file from the given 
     * directory in eclipse editor 
     * 
     * @param directory directory path
     * @param className name of the java class
     * @throws PartInitException in case of error
     * @throws FileNotFoundException if specified file is not found
     */
    protected void openFile(final String directory, final String className) 
        throws PartInitException, FileNotFoundException {
        
        final String fileName = className + ".java";
        File fileToOpen = new File(directory, fileName);
        
        if (fileToOpen.exists() && fileToOpen.isFile()) {
            IFileStore fileStore = EFS.getLocalFileSystem().getStore(fileToOpen.toURI());
            IWorkbenchPage page = 
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            IDE.openEditorOnFileStore(page, fileStore);
        } else {
            throw new FileNotFoundException();
        }
    }

    protected String getPersistentProperty(IResource res, QualifiedName qn) {
        try {
            return res.getPersistentProperty(qn);
        } catch (CoreException e) {
            return "";
        }
    }

    protected void setPersistentProperty(
        final IResource res, 
        final QualifiedName qn,
        final String value
    ) {
        try {
            res.setPersistentProperty(qn, value);
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write to file
     * 
     * @param dir directory
     * @param name filename
     * @param content file content
     */
    private void write(
        final String dir, 
        final String name, 
        final String content
    ) {
        try {
            String outputFile = dir + File.separator + name + ".java";

            FileWriter output = new FileWriter(outputFile);
            BufferedWriter writer = new BufferedWriter(output);
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns name of the fixture class
     * @return name of the fixture class
     */
    private String getClassName() {
        return action.getClassName();
    }
    
    /**
     * Returns model class name
     * @return model class name
     */
    private String getModelClassName() {
        return action.getModelClassName();
    }
    
    /**
     * Shows wizard to user and returns generated fixture source code
     * @return generated fixture source code
     */
    private String getFixtureCodeUsingWizard() {
        action = new WindowAction(
                HandlerUtil.getActiveWorkbenchWindow(executionEvent)
            );
        action.run();
        return action.getGeneratedFixture();
    }
    
    /**
     * Disposes windows
     */
    private void disposeWindows() {
        if (action != null) {
            action.disposeWindows();
        }
    }
}