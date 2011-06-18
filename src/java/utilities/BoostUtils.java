
package utilities;

import ConnectionDataBase.Extrastats;
import ConnectionDataBase.ExtrastatsHelper;
import java.util.Iterator;
import java.util.List;

public class BoostUtils {

    private static final double MONEY_BOOST = 0.05;
    private static final double UNDERGRADS_BOOST = 0.5;
    private static final double PHD_BOOST = 1.0;
    private static final double PHD_RESSISTANCE = 1.5;

    public double calculateMoneyBoost(int input_money) {
        return input_money * MONEY_BOOST;
    }
    
    public double calculateStudentsBoost(String playerName, int phd , int undergrads) {
        
        double undergads_output = undergrads * UNDERGRADS_BOOST;
        double phd_output = phd * PHD_BOOST;
        /* Getting required statistics. */
        ExtrastatsHelper extraStats = new ExtrastatsHelper();
        Extrastats stats_record = extraStats.getPlayerStatsRecrod(playerName);
        if (stats_record == null) {
            // Should not happen
            System.err.println
                ("Error in calculateStudentsBoost(), player stats record does not exists.");
            return 0;
        }
        int alcoholism = stats_record.getAlcoholizm();
        int starvation = stats_record.getStarvation();
        int satisfaction = stats_record.getSatisfaction();

        // Calculating alcoholism boost.
        // Optimum for 20, possitive boost for (0 ; 40)
        double alco_boost_rate = 1;
        if (alcoholism >= 0 && alcoholism <= 40) {
            int temp = alcoholism;
            if (alcoholism > 20) {
                temp = 40 - alcoholism;
            }
            alco_boost_rate += temp/100;
        }
        else {
            // A decrease in performance
            alco_boost_rate -= (alcoholism-40)/100;
        }

        // Calculating starvation boost
        double starvation_boost =  1;
        if (starvation > 50) {
            // A negative impact
            starvation_boost -= (starvation-50)/100;
        }
        else {
            // A positive impact
            starvation_boost += (50 - starvation)/100;
        }

        // Calculating satisfaction boost
        double sati_boost = 1;
        sati_boost += satisfaction/100;

        // Calculating the overall boost.
        double boost = sati_boost * starvation_boost * alco_boost_rate;

        // Calculating phD and ungergratuates output
        undergads_output = undergads_output * boost;

        if (boost > 1) {
            phd_output = phd_output * boost * PHD_RESSISTANCE;
        }
        else {
            phd_output = phd_output * boost / PHD_RESSISTANCE;
        }

        return undergads_output + phd_output;
    }

    public int calculateLecturerBoost(Lecturer lecturer, String research_area) {
            int boost_value = 0;
            List<LecturerBenefits> benefits = lecturer.getSpecializations();
            Iterator<LecturerBenefits> it = benefits.iterator();
            while (it.hasNext()) {
                LecturerBenefits benefit = it.next();
                if (benefit.getField().equals(research_area)) {
                    boost_value += benefit.getBoost();
                    break;
                }
            }
            return boost_value;
    }

    public double calculateBuildingBoostMultiplier(String playerName) {
        BuildingsUtils utils = new BuildingsUtils();
        double rate_value = 1.0;
        if (utils.isLabBuilt(playerName)) {
            rate_value += 0.5;
        }
        if (utils.isBrainBuilt(playerName)) {
            rate_value += 1;
        }
        return rate_value;
    }
}
