package Logic;

import java.util.Date;


public class Log{
    public String getlog(String first,String last){
        Date date=new Date();
        return (first+"\t"+String.valueOf(date)+"\t"+last+"\n");
    }
}
