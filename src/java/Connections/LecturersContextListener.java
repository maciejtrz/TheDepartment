
package Connections;

import ConnectionDataBase.DepartmentinfoHelper;
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

    private static CreatorDeamon creator = null;

    public void contextInitialized(ServletContextEvent sce) {
        if (creator == null) {

            System.out.println("IM NOTIFIED ABOUT THE CONTEX!"
                    + " My name is: " + this.toString());
            creator = new CreatorDeamon();
            System.out.println(" Starting new thread. !!!!");
            creator.setDaemon(true);
            creator.start();

        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        
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

            DepartmentinfoHelper deptInfoHelper
                    = new DepartmentinfoHelper();

            while (true) {
                try {

                    System.out.println("THREAD " +
                            this.getName() + " is READY TO OPERATE");
                    sleep(1000 * 60 * 1);

                    
                    System.out.println(this.getName() + " is repopulating available lecturers");

                    // Updating available lecturers for all players

                    List<Players> allPlayers
                            = playersHelper.getPlayers();
                    Iterator <Players> it = allPlayers.iterator();

                    while (it.hasNext()) {
                        Players player = it.next();
                        String idname = player.getIdname();

                        if (idname != null) {
                            // Checking whether a given player has a department
                            boolean hasDept
                                    = deptInfoHelper.hasDepartment(idname);
                            if (!hasDept) {
                                // If not continue to another player.
                                continue;
                            }

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
                    System.out.println(this.getName() + " is going to sleep.");
                    sleep(1000 * 60 * 5);
                } catch (InterruptedException ex) {
                    System.out.println(this.getName() + " I BROKE :((((((((((");
                    Logger.getLogger(LecturersContextListener
                        .class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }



    }

}
