
package Connections;

import ResearchPoints.ResearchDevelopment;
import ResearchPoints.ResearchTreeShowcase;
import buildings.BuildingFactory;
import deamons.DeamonManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import specializationsGenerator.SpecializationsGenerator;

public class ApplicationContextInitializer implements ServletContextListener {


    public void contextInitialized(ServletContextEvent sce) {

            System.out.println("IM NOTIFIED ABOUT THE CONTEX!"
                    + " My name is: " + this.toString());

            /* Initizalization of research development tree */
            SpecializationsGenerator.initializeSpecializationsGenerator();
            ResearchDevelopment.initializeDevelopmentTree();
            ResearchTreeShowcase.initializeResearchTreeShowcase();
            BuildingFactory.initializeBuildings();
            DeamonManager mgr = DeamonManager.getManager();


            System.out.println(" Starting new POPULATOR thread. !!!!");
            mgr.createLecturersPopulator();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        
    }


}
