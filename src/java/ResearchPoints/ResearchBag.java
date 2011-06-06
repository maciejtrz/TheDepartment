package ResearchPoints;

import Connections.ConnectionSingleton;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


public class ResearchBag {

    List<Research> researchBag = new ArrayList<Research>();
   
    public ResearchBag() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        researchBag = (List<Research>) session.getAttribute(ConnectionSingleton.researchBag);
    }
    
    public List<Research> getResearches() {
        return researchBag;
    }

    public void setResearches(List<Research> researchBag) {
        this.researchBag = researchBag;
    }






}

/*
                    <p:outputPanel id="researches">
                        <p:dataTable value="#{researchBag.researches}" var="research">
            <p:column>
                <f:facet name="header">
                    <h:outputText value="Title" />
                </f:facet>
                <h:outputText value="#{research.name}" />
            </p:column>

            <p:column>
                <f:facet name="header">
                    <h:outputText value="Time left" />
                </f:facet>
                <h:outputText value="#{research.researchTime}" />
            </p:column>


        </p:dataTable>
    </p:outputPanel> */