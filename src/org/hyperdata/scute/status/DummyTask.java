/**
 * 
 */
package org.hyperdata.scute.status;

/**
 * @author danny
 *
 * has the listeners, but doesn't do anything
 */
public class DummyTask extends StatusTask {

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see org.hyperdata.scute.status.Stoppable#stop()
	 */
	@Override
	public void stop() {
		// do nothing
	}
}
