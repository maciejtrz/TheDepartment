package buildings;

public class BuildingFactory {

    /* Create all neccessary building objects. */

    private BlackMarket blackMarket;
    private BobTheBuilder bob;
    private Brain brain;
    private DocPub docPub;
    private Laboratories labs;
    private LectureRoom lecturerRoom;
    private MacChicken macChicken;
    private PhdOffice pdhOffice;
    private ProfessorsOffice profOffice;
    private StudentUnion studentUnion;
    private Tresco tresco;

    public void initializeBuildings() {

        blackMarket = new BlackMarket();
        bob = new BobTheBuilder();
        brain = new Brain();
        docPub = new DocPub();
        labs = new Laboratories();
        lecturerRoom = new LectureRoom();
        macChicken = new MacChicken();
        pdhOffice = new PhdOffice();
        profOffice = new ProfessorsOffice();
        studentUnion = new StudentUnion();
        tresco = new Tresco();

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

    public LectureRoom getLecturerRoom() {
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
