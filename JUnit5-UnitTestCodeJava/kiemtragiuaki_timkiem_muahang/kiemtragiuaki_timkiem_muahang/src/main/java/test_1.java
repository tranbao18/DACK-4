public class test_1 {
    public int Premium(String Breakdowncover, String Windscreenrepair, int NOALY, int TotalM,
                       int EstimatedValue, String ParkingLocation){
        int sum;
        if(EstimatedValue <= 100){
            return 0;
        } else {
            sum = EstimatedValue;
        }
        if(Breakdowncover=="Roadside"){
            sum*=0.02;
        } else if (Breakdowncover=="No Cover") {
            sum*=0.01;
        } else if (Breakdowncover=="At home") {
            sum*=0.03;
        } else if (Breakdowncover=="European") {
            sum*=0.04;
        }
        if (Windscreenrepair=="Yes") {
            sum+=30;
        }else {
            sum+=0;
        }
        if(NOALY == 0){
            sum*=0.7;
        }

        if(TotalM > 5000){
            sum+=50;
        }

//        if(ParkingLocation == "Public Place"){
//            sum+=30;
//        }

        return sum;
    }
}
