
package Connections;

import ResearchPoints.ResearchDevelopment;
import ResearchPoints.ResearchTreeShowcase;
import deamons.DeamonManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import messageSystem.OffersSuperviser;
import specializationsGenerator.SpecializationsGenerator;

public class ApplicationContextInitializer implements ServletContextListener {

    private static int i = 0;

    public void contextInitialized(ServletContextEvent sce) {

            /* Initizalization of research development tree */
            SpecializationsGenerator.initializeSpecializationsGenerator();
            ResearchDevelopment.initializeDevelopmentTree();
            ResearchTreeShowcase.initializeResearchTreeShowcase();
            OffersSuperviser.initializeOffersSupervise();

            DeamonManager mgr = DeamonManager.getManager();

            //mgr.createLecturersPopulator();
            //mgr.createEventDeamon();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        UserManager.saveToDatabase();
    }




}
