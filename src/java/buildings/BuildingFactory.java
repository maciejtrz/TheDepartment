package buildings;

public class BuildingFactory {

    /* Create all neccessary building objects. */

    private static BlackMarket blackMarket;
    private static BobTheBuilder bob;
    private static Brain brain;
    private static DocPub docPub;
    private static Laboratories labs;
    private static LectureRoom lecturerRoom;
    private static MacChicken macChicken;
    private static PhdOffice pdhOffice;
    private static ProfessorsOffice profOffice;
    private static StudentUnion studentUnion;
    private static Tresco tresco;

    public static void initializeBuildings() {

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

    public static BlackMarket getBlackMarket () {
        return blackMarket;
    }

    public static BobTheBuilder getBob() {
        return bob;
    }

    public static Brain getBrain () {
        return brain;
    }

    public static DocPub getPub() {
        return docPub;
    }

    public static Laboratories getLabs() {
        return labs;
    }

    public static LectureRoom getLecturerRoom() {
        return lecturerRoom;
    }

    public static MacChicken getMacChicken() {
        return macChicken;
    }

    public static PhdOffice getPdhOffice() {
        return pdhOffice;
    }

    public static ProfessorsOffice getProfOffice() {
        return profOffice;
    }

    public static StudentUnion getStudentUnion() {
        return studentUnion;
    }

    public static Tresco getTresco() {
        return tresco;
    }

}
