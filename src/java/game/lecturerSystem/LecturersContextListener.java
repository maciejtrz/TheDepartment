
package game.lecturerSystem;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LecturersContextListener implements ServletContextListener {

    private CreatorDeamon creator = null;

    public void contextInitialized(ServletContextEvent sce) {
        if (creator == null || !creator.isAlive()) {
            creator = new CreatorDeamon();
            creator.start();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        /* Unsupported. */
    }


    private class CreatorDeamon extends Thread {

        @Override
        public void run() {
            while (true) {
                System.out.println("IM UPDATING AND YOU ARE FUCKED!");
                try {
                    sleep(1000 * 60);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LecturersContextListener
                        .class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }



    }

}
