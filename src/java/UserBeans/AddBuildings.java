/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package UserBeans;

import buildings.*;
import utilities.BuildingInfo;


/**
 *
 * @author root
 */
public class AddBuildings {

    /** Creates a new instance of AddBuildings */
    public AddBuildings() {
    }

    public void test() {
       /* TEST CODE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! */

        /*
        String playerName = utilities.BasicUtils.getUserName();

        System.out.println("TESTING!");

        BuildingFactory factory = new BuildingFactory();
        factory.initializeBuildings();


 
        Tresco tresco = factory.getTresco();

        BuildingInfo info = tresco.isAllowedToBuild(playerName, 3);
        System.out.println(info.getReason());

        tresco.build(playerName, 3);


        MacChicken macChicken = factory.getMacChicken();
        info = macChicken.isAllowedToBuild(playerName, 4);
        System.out.println(info.getReason());

        macChicken.build(playerName, 4);

        StudentUnion union = factory.getStudentUnion();
        union.build(playerName, 6);

        DocPub pub = factory.getPub();
        info = pub.isAllowedToBuild(playerName, 5);
        System.out.println(info.getReason());

        pub.build(playerName, 5);

        factory.getLabs().build(playerName, 7);
        factory.getBrain().build(playerName, 8);
        factory.getPdhOffice().build(playerName, 9);
        factory.getProfOffice().build(playerName, 10);
        factory.getLecturerRoom().build(playerName, 11);

        factory.getTresco().remove(playerName, 3);
        factory.getMacChicken().remove(playerName, 4);
        factory.getPub().remove(playerName, 5);
        factory.getStudentUnion().remove(playerName, 6);
        factory.getLabs().remove(playerName, 7);
        factory.getBrain().remove(playerName, 8);
        factory.getPdhOffice().remove(playerName, 9);
        factory.getProfOffice().remove(playerName, 10);
        factory.getLecturerRoom().remove(playerName, 11);

        /* TEST CODE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! */
    }

}
