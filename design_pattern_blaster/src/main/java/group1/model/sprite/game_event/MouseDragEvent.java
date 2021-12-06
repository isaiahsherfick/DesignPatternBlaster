package group1.model.sprite.game_event;

public class MouseDragEvent extends GameEvent {


	protected MouseDragEvent(int eventType) {
		super(eventType);
		// TODO Auto-generated constructor stub
	}

	public boolean equals(Object o)
	{
//		if (o instanceof MouseDragEvent)
//		{
//			MouseDragEvent m = (MouseDragEvent)o;
//			return m.equals(m.getKeyCode());
//		}
		return false;
	}

}
