package mqwrapper;

public class MessagingEvent {

	private java.util.Vector data = new java.util.Vector();

	public synchronized void addListener(MessageListener lis) {
		data.addElement(lis);
	}

	public synchronized void removeListener(MessageListener lis) {
		data.removeElement(lis);
	}

	public interface MessageListener extends java.util.EventListener {
		void listen(GetMessageEvent event);
	}

	public class GetMessageEvent extends java.util.EventObject {
		private static final long serialVersionUID = 1L;
		public String message;

		GetMessageEvent(Object obj, String message) {
			super(obj);
			this.message = message;

		}
	}

	public void notfifyMatlab(String message) {
		java.util.Vector dataCopy;
		synchronized (this) {
			dataCopy = (java.util.Vector) data.clone();
		}
		for (int i = 0; i < dataCopy.size(); i++) {
			GetMessageEvent event = new GetMessageEvent(this, message);
			((MessageListener) dataCopy.elementAt(i)).listen(event);
		}
	}
}