package support;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public interface Observable {
    public void addController(Observer o);
    public void removeController();
    public void notifyActionPerformed(String action);   // Modalità pull
}
