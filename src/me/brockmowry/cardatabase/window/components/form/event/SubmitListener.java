package me.brockmowry.cardatabase.window.components.form.event;

import java.util.EventListener;

public interface SubmitListener extends EventListener {
	public void submitTriggered(final SubmitEvent event);
}
