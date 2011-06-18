/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package buildings;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.BuildingsPositionHelper;


/**
 *
 * Upgrade functions are never to be called here if no upgrades are possible.
 * The button invoking them should be invisible.
 */
public class UpgradeBean {

    private String playerName;

    /** Creates a new instance of UpgradeBean */
    public UpgradeBean() {
        playerName = utilities.BasicUtils.getUserName();
    }

    public void upgradeLabs() {
        BuildingFactory factory = new BuildingFactory();
        Laboratories labs = factory.getLabs();
        
        BuildingsPositionHelper helper = new BuildingsPositionHelper();
        int position = helper.getPosition(playerName, Building.CODE_LABS);
        labs.upgrade(playerName, position);
    }

    public void upgradeLectureRoom() {
        System.out.println("UPGRADING !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        BuildingFactory factory = new BuildingFactory();
        LectureRoom lec_room = factory.getLecturerRoom();

        BuildingsPositionHelper helper = new BuildingsPositionHelper();
        int position = helper.getPosition(playerName, Building.CODE_LECTURER_ROOM_1);
        if (position == 0) {
            position = helper.getPosition(playerName, Building.CODE_LECTURER_ROOM_2);
        }
        lec_room.upgrade(playerName, position);
    }

    public void upgradePhdOffice() {
        BuildingFactory factory = new BuildingFactory();
        PhdOffice phd_office = factory.getPdhOffice();

        BuildingsPositionHelper helper = new BuildingsPositionHelper();
        int position = helper.getPosition(playerName, Building.CODE_PHD_OFFICE_1);
        if (position == 0) {
            position = helper.getPosition(playerName, Building.CODE_PHD_OFFICE_2);
        }
        phd_office.upgrade(playerName, position);
    }

    public void upgradeProfessors() {
        BuildingFactory factory = new BuildingFactory();
        ProfessorsOffice prof_office = factory.getProfOffice();

        BuildingsPositionHelper helper = new BuildingsPositionHelper();
        int position = helper.getPosition(playerName, Building.CODE_PROF_OFFICE_1);
        if (position == 0) {
            position = helper.getPosition(playerName, Building.CODE_PROF_OFFICE_2);
        }
        prof_office.upgrade(playerName, position);
    }

    public boolean getCanUpgradeLabs() {
        BuildingFactory factory = new BuildingFactory();
        Laboratories labs = factory.getLabs();

        BuildingsPositionHelper helper = new BuildingsPositionHelper();
        int position = helper.getPosition(playerName, Building.CODE_LABS);
        return !labs.canUpgradeLabs(playerName, position);
    }


    public boolean getCanUpgradeLecRoom() {
        System.out.println("The getter is called!");
        BuildingFactory factory = new BuildingFactory();
        LectureRoom lec_room = factory.getLecturerRoom();

        BuildingsPositionHelper helper = new BuildingsPositionHelper();
        int position = helper.getPosition(playerName, Building.CODE_LECTURER_ROOM_1);
        if (position == 0) {
            position = helper.getPosition(playerName, Building.CODE_LECTURER_ROOM_2);
        }
        return !lec_room.canUpgradeLecturerRoom(playerName, position);
    }

    public boolean getCanUpgradePhdOffice() {
        BuildingFactory factory = new BuildingFactory();
        PhdOffice phd_office = factory.getPdhOffice();

        BuildingsPositionHelper helper = new BuildingsPositionHelper();
        int position = helper.getPosition(playerName, Building.CODE_PHD_OFFICE_1);
        if (position == 0) {
            position = helper.getPosition(playerName, Building.CODE_PHD_OFFICE_2);
        }
        return !phd_office.canUpgradePhdOffice(playerName, position);
    }

    public boolean getCanUpgradeProfOffice() {
        BuildingFactory factory = new BuildingFactory();
        ProfessorsOffice prof_office = factory.getProfOffice();

        BuildingsPositionHelper helper = new BuildingsPositionHelper();
        int position = helper.getPosition(playerName, Building.CODE_PROF_OFFICE_1);
        if (position == 0) {
            position = helper.getPosition(playerName, Building.CODE_PROF_OFFICE_2);
        }
        return !prof_office.canUpdateProffesorOffice(playerName, position);
    }

    

}
