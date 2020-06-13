package cardatabase.window.panels.form.event;

import java.util.EventListener;

public interface SubmitListener extends EventListener {
	public void submitTriggered(final SubmitEvent event);
}
