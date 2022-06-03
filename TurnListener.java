import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class TurnListener extends MouseAdapter {
    
    GameFrame game;
    
    public TurnListener(GameFrame g) {
        game = g;
    }

    public void mouseClicked(MouseEvent e) {
        game.takeTurn();
    }
    
}
