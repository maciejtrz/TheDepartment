package buildings;

import ConnectionDataBase.BuildingsHelper;

public class BuildingFactory {

    /* Create all neccessary building objects. */

    private BlackMarket blackMarket;
    private BobTheBuilder bob;
    private Brain brain;
    private DocPub docPub;
    private Laboratories labs;
    private LecturerRoom lecturerRoom;
    private MacChicken macChicken;
    private PhdOffice pdhOffice;
    private ProfessorsOffice profOffice;
    private StudentUnion studentUnion;
    private Tresco tresco;

    public void initializeBuildings() {

        blackMarket = new BlackMarket(100);
        bob = new BobTheBuilder(100);
        brain = new Brain(100);
        docPub = new DocPub(100);
        labs = new Laboratories(100);
        lecturerRoom = new LecturerRoom(100);
        macChicken = new MacChicken(100);
        pdhOffice = new PhdOffice(100);
        profOffice = new ProfessorsOffice(100);
        studentUnion = new StudentUnion(100);
        tresco = new Tresco(100);

    }

    /* All neccessary getters. */

    public BlackMarket getBlackMarket () {
        return blackMarket;
    }

    public BobTheBuilder getBob() {
        return bob;
    }

    public Brain getBrain () {
        return brain;
    }

    public DocPub getPub() {
        return docPub;
    }

    public Laboratories getLabs() {
        return labs;
    }

    public LecturerRoom getLecturerRoom() {
        return lecturerRoom;
    }

    public MacChicken getMacChicken() {
        return macChicken;
    }

    public PhdOffice getPdhOffice() {
        return pdhOffice;
    }

    public ProfessorsOffice getProfOffice() {
        return profOffice;
    }

    public StudentUnion getStudentUnion() {
        return studentUnion;
    }

    public Tresco getTresco() {
        return tresco;
    }

}
