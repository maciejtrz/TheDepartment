
package Connections;

import ResearchPoints.ResearchDevelopment;
import ResearchPoints.ResearchTreeShowcase;
import deamons.DeamonManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import messageSystem.OffersSuperviser;
import specializationsGenerator.SpecializationsGenerator;

public class ApplicationContextInitializer implements ServletContextListener {


    public void contextInitialized(ServletContextEvent sce) {

            System.out.println("IM NOTIFIED ABOUT THE CONTEX!"
                    + " My name is: " + this.toString());

            /* Initizalization of research development tree */
            SpecializationsGenerator.initializeSpecializationsGenerator();
            ResearchDevelopment.initializeDevelopmentTree();
            ResearchTreeShowcase.initializeResearchTreeShowcase();
            OffersSuperviser.initializeOffersSupervise();

            DeamonManager mgr = DeamonManager.getManager();


            System.out.println(" Starting new threads in CONTEXT. !!!!");
            //mgr.createLecturersPopulator();
            //mgr.createEventDeamon();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        
    }




}
