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
        blackMarket = new BlackMarket();
        return blackMarket;
    }

    public BobTheBuilder getBob() {
        bob = new BobTheBuilder();
        return bob;
    }

    public Brain getBrain () {
        brain = new Brain();
        return brain;
    }

    public DocPub getPub() {
        docPub = new DocPub();
        return docPub;
    }

    public Laboratories getLabs() {
        labs = new Laboratories();
        return labs;
    }

    public LectureRoom getLecturerRoom() {
        lecturerRoom = new LectureRoom();
        return lecturerRoom;
    }

    public MacChicken getMacChicken() {
        macChicken = new MacChicken();
        return macChicken;
    }

    public PhdOffice getPdhOffice() {
        pdhOffice = new PhdOffice();
        return pdhOffice;
    }

    public ProfessorsOffice getProfOffice() {
        profOffice = new ProfessorsOffice();
        return profOffice;
    }

    public StudentUnion getStudentUnion() {
        studentUnion = new StudentUnion();
        return studentUnion;
    }

    public Tresco getTresco() {
        tresco = new Tresco();
        return tresco;
    }

}
