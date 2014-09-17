package org.akrogen.dynaresume.raphelloworld.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class JobActionWithDialog implements IWorkbenchWindowActionDelegate {
	
	private static final int TASK_AMOUNT = 100;
	
	@Override
	public void dispose() {}
	
	@Override
	public void init(IWorkbenchWindow window) {}
	
	@Override
	public void run(IAction action) {
		Job job = new Job("Long Running Action: ") {
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Number counting", TASK_AMOUNT);
				
				for(int i = 0; i < TASK_AMOUNT; i++) {
			          if(monitor.isCanceled()) {
			            monitor.done();
			            return Status.CANCEL_STATUS;
			          }
			          int done = i % TASK_AMOUNT;
			          monitor.subTask("work done: (" + done+ "%)");
			          monitor.worked(1);
			          try {
			            Thread.sleep(200);
			          } catch(InterruptedException e) {
			            e.printStackTrace();
			          }
			        }
			        monitor.done();
			        return Status.OK_STATUS;
			}
		};
		
		job.setName(job.getName() + " " + job.hashCode());
	    job.setUser(true);
	    job.schedule();
	}
	
	@Override
	public void selectionChanged(IAction action, ISelection selection) {}
}
