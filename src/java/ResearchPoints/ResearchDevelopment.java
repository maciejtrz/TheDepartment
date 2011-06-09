/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ResearchPoints;

import specializationsGenerator.SpecializationsGenerator;

/**
 *
 * @author kp1209
 */
public class ResearchDevelopment {

    public static void initializeDevelopmentTree() {
        ResearchInstance ins1 = new ResearchInstance(getSubject(0),"War of life","the aim of this research is to test the AI strategies efficiency against human player");
        ResearchInstance ins2 = new ResearchInstance(getSubject(1),"Malice","new compiler that produces extraordinarily efficient machine code");
        ResearchInstance ins3 = new ResearchInstance(getSubject(2),"Pintos","new operating system that is to be run on DoC lab machines in order to safe some money");
        ResearchInstance ins4 = new ResearchInstance(getSubject(3),"Hai protocol","new connection protocol that minimises collisions");
        ResearchInstance ins5 = new ResearchInstance(getSubject(4),"Halting Problem","the aim of this project is to prove Turing wrong and prove that the halting problem might be solved");
        ResearchInstance ins6 = new ResearchInstance(getSubject(5),"Student Correlation","the aim of this project is to check whether there exists some kind of connection between the volume of beer students drink and the grades they score");
        ResearchInstance ins7 = new ResearchInstance(getSubject(3),"Not exclusive xor","to check usefulness of such gate");
        ResearchInstance ins8 = new ResearchInstance(getSubject(2),"Students logic","to check whether there exist any there exists any type of primitive logic system the students use during their studies.");



    }

    public static String getSubject(int i) {
        return SpecializationsGenerator.subjectList[i];
    }

}
