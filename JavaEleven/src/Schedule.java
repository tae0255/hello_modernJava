import java.util.HashMap;
import java.util.Map;

class Schedule {
    public String[][] staticSchedule;
    HashMap<String, Integer> DAY_DICT = new HashMap<>();
    {
        DAY_DICT.put("MO", 0);
        DAY_DICT.put("TU", 10000);
        DAY_DICT.put("WE", 20000);
        DAY_DICT.put("TH", 30000);
        DAY_DICT.put("FR", 40000);
    }

    public final int LECTURE_NUM = 5;
    public final int CLASS_NUM = 4;
    public int answer = 0;

    public int solution(String[][] schedule) {
        staticSchedule = schedule;

        //키 : 요일(만의 자리), 수업시작시간(빠른계산을 위해 시간을치환, 정밀도손실)
        //값 : 수업시간(빠른계산을 위해 시간을치환, 정밀도손실)
        HashMap<Integer, Integer> timeTable = new HashMap<>();
        for(int i=0; i<CLASS_NUM; i++){
            bruteforce(timeTable, 0, i);
        }

        return answer;
    }

    private void bruteforce(HashMap<Integer, Integer> timeTable, int lecture, int clas) {
        if(lecture>=LECTURE_NUM) return;

        HashMap<Integer, Integer> myTable = new HashMap<>(timeTable);
        if(checkMyTable(myTable, lecture, clas)){
            if(lecture==LECTURE_NUM-1) {
                answer++;
                //System.out.println(myTable.toString());
                return;
            }
            updateMyTable(myTable, lecture, clas);
            for(int i=0; i<CLASS_NUM; i++){
                bruteforce(myTable, lecture+1, i);
            }
        }
    }

    private boolean checkMyTable(HashMap<Integer, Integer> myTable, int lecture, int clas) {
        String[] s = staticSchedule[lecture][clas].split(" ");
        if(s.length==2){
            int day = DAY_DICT.get(s[0]);
            int start = Integer.parseInt(s[1].replace(":",""));
            int duration = 300;

            for(Map.Entry<Integer, Integer> e : myTable.entrySet()){
                if(e.getKey() < day+start && day+start < e.getKey()+e.getValue()) return false;
                if(e.getKey() < day+start+duration && day+start+duration < e.getKey()+e.getValue()) return false;
            }

        }else if(s.length==4){
            int day1 = DAY_DICT.get(s[0]), day2 = DAY_DICT.get(s[2]);
            int start1 = Integer.parseInt(s[1].replace(":",""))/60*100, start2 = Integer.parseInt(s[3].replace(":",""));
            int duration = 130;

            for(Map.Entry<Integer, Integer> e : myTable.entrySet()){
                if(e.getKey() < day1+start1 && day1+start1 < e.getKey()+e.getValue()) return false;
                if(e.getKey() < day2+start2 && day2+start2 < e.getKey()+e.getValue()) return false;
                if(e.getKey() < day1+start1+duration && day1+start1+duration < e.getKey()+e.getValue()) return false;
                if(e.getKey() < day2+start2+duration && day2+start2+duration < e.getKey()+e.getValue()) return false;
            }
        }

        return true;
    }

    private void updateMyTable(HashMap<Integer, Integer> myTable, int lecture, int clas) {
        String[] s = staticSchedule[lecture][clas].split(" ");
        if(s.length==2){
            int day = DAY_DICT.get(s[0]);
            int start = Integer.parseInt(s[1].replace(":",""));
            int duration = 300;
            myTable.put(day+start, duration);
        }else if(s.length==4){
            int day1 = DAY_DICT.get(s[0]), day2 = DAY_DICT.get(s[2]);
            int start1 = Integer.parseInt(s[1].replace(":","")), start2 = Integer.parseInt(s[3].replace(":",""));
            int duration = 130;
            myTable.put(day1+start1, duration);
            myTable.put(day2+start2, duration);
        }
    }

}