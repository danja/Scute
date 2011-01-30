/**
 * 
 */
package org.hyperdata.scute.validate;

import java.util.Random;


/**
 * @author danny
 *
 */
public class DummyValidatable implements Validatable {

	private long duration = 2000;
	
	private Random random = new Random();
	
	/* (non-Javadoc)
	 * @see org.hyperdata.scute.validate.Validatable#validate()
	 */
	@Override
	public ValidationEvent validate() throws InterruptedException {
		
		// pretend to be working
		Thread.sleep(this.duration);
		
		ValidationEvent event = new ValidationEvent();
		
		event.setStatus(getRandomStatus());
		event.setDescription("Computer says \"No.\"");
		
		return event;
	}
	
	public void setDuration(long duration){
		this.duration = duration;
	}
	
	private int getRandomStatus() {
		return random.nextInt(Validator.N_STATES);
	}

}
