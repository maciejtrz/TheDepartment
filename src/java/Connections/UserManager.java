
package Connections;

import ConnectionDataBase.Playerresources;
import ConnectionDataBase.PlayerresourcesHelper;
import ConnectionDataBase.Research;
import ConnectionDataBase.ResearchHelper;
import ResearchPoints.ResearchBag;
import ResearchPoints.ResearchDevelopment;
import ResearchPoints.ResearchTreeNode;
import UserBeans.Auth;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import messageSystem.TradeOffer;
import resources.Resource;
import resources.ResourcesType;


public class UserManager {

    /* Set of all user currently having sessions */
    private static Map<String,Auth> sessionMap = new HashMap<String,Auth>();
    private static Map<String,ResearchBag> sessionResearchBag = new HashMap<String,ResearchBag>();

    public static boolean containsResearchBag(String username) {
        return sessionResearchBag.containsKey(username);
    }

    public static ResearchBag getResearchBag(String username) {
        return sessionResearchBag.get(username);
    }

    /* Singleton */
    private UserManager() {
    }


    static private boolean isUserMonitored(String username) {
        return sessionMap.containsKey(username);
    }


    static private void saveResources(Playerresources resources) {

        if(!isUserMonitored(resources.getIdname())) {
             saveToDatabase(resources);
        }

    }

    static private Playerresources getResources(String username) {

        Playerresources resources = null;
        if(isUserMonitored(username)) {

            resources = getUser(username).getResources();

        } else {

            PlayerresourcesHelper resourcesHelper = new PlayerresourcesHelper();
            resources = resourcesHelper.getResources(username);

        }

        return resources;
    }

    static private Auth getUser(String username) {
        return sessionMap.get(username);
    }

    static private void saveToDatabase(Playerresources resources) {
            PlayerresourcesHelper resourcesHelper = new PlayerresourcesHelper();
            resourcesHelper.updateResources(resources);
    }

    static synchronized public void addUser(Auth auth) {
        sessionMap.put(auth.getUsername(), auth);
    }

    static public void addResearchBag(ResearchBag researchBag) {
        sessionResearchBag.put(researchBag.getUserid(), researchBag);
    }

    static synchronized public void removeUser(String username) {
        sessionMap.remove(username);
    }

    static public void removeResearchBag(String username) {
        System.out.println("Is user monitored: " + isUserMonitored(username));
       // System.out.println("Are all researches finished: " + )

        if(!isUserMonitored(username) && getResearchBag(username) != null 
               && getResearchBag(username).getResearches().isEmpty() ) {

            ResearchBag researchBag = getResearchBag(username);
            ResearchHelper researchHelper = new ResearchHelper();

            researchHelper.deleteAllResearches(researchBag.getUserid());


            System.out.println("Writing researches to DB...");
            
            if (!researchBag.getAvailableResearch().isEmpty()) {

                researchHelper.addResearches(researchBag.getUserid(), researchBag.getAvailableResearch());

           }

           sessionResearchBag.remove(username);
        }
    }

    static synchronized public void addMoney(String username, int money) {

            Playerresources resources = getResources(username);
            resources.setMoney(resources.getMoney() + money);
            saveResources(resources);

    }

    static synchronized public int getMoney(String userName) {
        Playerresources resources = getResources(userName);
        return resources.getMoney();
    }

    static synchronized public boolean removeMoney(String username, int money) {

            boolean result = false;

            Playerresources resources = getResources(username);

            if(resources.getMoney() - money >= 0) {
                resources.setMoney(resources.getMoney() - money);
                saveResources(resources);
                result = true;
            }

            return result;

    }

    static synchronized public void addResearchPoints(String username, int researchPoints) {
            Playerresources resources = getResources(username);
            resources.setResearchpoints(resources.getResearchpoints()+researchPoints);
            saveResources(resources);
    }


    static synchronized public boolean removePhdsnumber(String username, int phdsnumber) {

            boolean result = false;

            Playerresources resources = getResources(username);

            if(resources.getPhdsnumber() - phdsnumber >= 0) {
                resources.setMoney(resources.getPhdsnumber() - phdsnumber);
                saveResources(resources);
                result = true;
            }

            return result;

    }

    static synchronized public void addPhdsnumber(String username, int phdsnumber) {
            Playerresources resources = getResources(username);
            resources.setPhdsnumber(resources.getPhdsnumber()+ phdsnumber);
            saveResources(resources);
    }

    static synchronized public boolean removeUndegraduatesnumber(String username, int undergraduatesnumber) {

            boolean result = false;

            Playerresources resources = getResources(username);

            if(resources.getUndergraduatesnumber() - undergraduatesnumber >= 0) {
                resources.setMoney(resources.getUndergraduatesnumber() - undergraduatesnumber);
                saveResources(resources);
                result = true;
            }

            return result;

    }

    static synchronized public void addUndegraduatesnumber(String username, int undergraduatesnumber) {
            Playerresources resources = getResources(username);
            resources.setUndergraduatesnumber(resources.getUndergraduatesnumber()+undergraduatesnumber);
            saveResources(resources);
    }

    static synchronized private void addResearchFromDB(Research research) {

            ResearchBag researchBag = getResearchBag(research.getUserId());

            researchBag.getResearches().remove(research);

            Integer researchId = research.getId().getResearchid();
            ResearchTreeNode researchTreeNode =
                    ResearchDevelopment.getResearchTreeNode(researchId);

            Iterator<ResearchTreeNode> iterator =
                    researchTreeNode.getDependentResearches().iterator();

            ResearchHelper researchHelper = new ResearchHelper();

            while(iterator.hasNext()) {
                ResearchTreeNode newTreeNode = iterator.next();
                Research researchInstance = new Research(research.getUserId(), newTreeNode.getResearchInstance().getResearchid());

                researchHelper.addResearch(researchInstance);
            }

            System.out.println("Removing researchId: " + research.getId().getResearchid() +
                    " at user id: " + research.getId().getIdname());
            
            researchHelper.deleteResearch(research);
    }

    static synchronized private void addResearchFromMemory(Research research) {
        
            ResearchBag researchBag = getResearchBag(research.getUserId());
            researchBag.getResearches().remove(research);

            System.out.println("user mananger - userId: " + research.getUserId());
            System.out.println("Size of researchs: " + researchBag.getResearches().size());

            Integer researchId = research.getId().getResearchid();
            ResearchTreeNode researchTreeNode =
                    ResearchDevelopment.getResearchTreeNode(researchId);

            Iterator<ResearchTreeNode> iterator =
                    researchTreeNode.getDependentResearches().iterator();

            while(iterator.hasNext()) {

                ResearchTreeNode newTreeNode = iterator.next();
                researchBag.getAvailableResearch().add(newTreeNode.getResearchInstance().getResearchid());
            }

            researchBag.getAvailableResearch().remove(researchId);
            
    }

    static synchronized public void addResearch(Research research) {
        System.out.println("Adding research points...");
        addResearchPoints(research.getUserId(),research.getResearchpoints());

        if(containsResearchBag(research.getUserId())) {
            
            System.out.println("Adding info about research to main memory");
            addResearchFromMemory(research);

        } else {

            System.out.println("Adding info about research to DB");
            addResearchFromDB(research);

        }

           removeResearchBag(research.getUserId());

    }

    synchronized public static boolean makeTrade(TradeOffer tradeOffer) {
        

        Playerresources senResources = getResources(tradeOffer.getSenderid());
        Playerresources recResources = getResources(tradeOffer.getReceiverid());

        System.out.println("Sender: " + tradeOffer.getSenderid());
        System.out.println("Receiver: " + tradeOffer.getReceiverid());
        System.out.println("senRersources null: " + (senResources == null));
        System.out.println("recRersources null: " + (recResources == null));

        Resource resourceOffered = ResourcesType.getResourcesElement(tradeOffer.getResourcesOfferedType());
        Resource resourceWanted = ResourcesType.getResourcesElement(tradeOffer.getResourcesWantedType());

        boolean result = false;

        if(resourceOffered.canRemove(senResources, tradeOffer.getAmountOffered()) &&
                resourceWanted.canRemove(recResources, tradeOffer.getAmountWanted())) {
            result = true;

            resourceOffered.remove(senResources, tradeOffer.getAmountOffered());
            resourceOffered.add(recResources, tradeOffer.getAmountOffered());

            resourceWanted.remove(recResources, tradeOffer.getAmountWanted());
            resourceWanted.add(senResources, tradeOffer.getAmountWanted());
        }

        saveResources(senResources);
        saveResources(recResources);

        return result;
    }

    public static void notifyUserAboutMessage(String username) {
        if(isUserMonitored(username)) {
            Auth auth = getUser(username);
            auth.notifyUserAboutMessage();
        }
    }

    public static boolean hasNewMessage(String username) {
        boolean result = false;

        if(isUserMonitored(username)) {
            Auth auth = getUser(username);
            result = auth.getHasNewMessage();
        }

        return result;
    }

    public static void setBuildingPosition(String username, int position) {
        getUser(username).setBuildingPosition(position);
    }

    public static int getBuilidngPosition(String username) {
        return getUser(username).getBuildingPosition();
    }


}
