
package Connections;

import ConnectionDataBase.LecturersAvailableHelper;
import ConnectionDataBase.LecturersHelper;
import ConnectionDataBase.LecturersSpecializationsHelper;
import ConnectionDataBase.PlayerHelper;
import ConnectionDataBase.Players;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import utilities.Lecturer;
import utilities.LecturersManager;

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
            /* Inicialization code. */

            // Setting up all required helpers
            PlayerHelper playersHelper = new PlayerHelper();
            LecturersAvailableHelper avHelper
                    = new LecturersAvailableHelper();
            LecturersHelper lecHelper
                    = new LecturersHelper();
            LecturersSpecializationsHelper specHelper
                    = new LecturersSpecializationsHelper();

            while (true) {
                try {
                    System.out.println("Repopulating available lecturers");

                    // Updating available lecturers for all players

                    List<Players> allPlayers
                            = playersHelper.getPlayers();
                    Iterator <Players> it = allPlayers.iterator();
                    while (it.hasNext()) {
                        Players player = it.next();
                        String idname = player.getIdname();
                        if (idname != null) {
                            // Removing all prevous records.
                            LecturersManager manager
                                    = new LecturersManager(idname);
                            ArrayList<Lecturer> av_list
                                    = manager.getAvailabeLecturers();
                            Iterator <Lecturer> lec_it
                                    = av_list.iterator();
                            while (lec_it.hasNext()) {
                                Lecturer cur = lec_it.next();
                                String lecName = cur.getName();
                                lecHelper.removeLecturer(lecName);
                                specHelper.removeSpecializationsRecords(lecName);
                            }

                            avHelper.removeAvailableLecturers(idname);
                            // Repopulating available lecturers.
                            manager.repopulateAvailableLec();
                        }
                    }

                    /* Sleep for five minutes. */
                    System.out.println("Going to sleep.");
                    sleep(1000 * 60 * 5);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LecturersContextListener
                        .class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }



    }

}
