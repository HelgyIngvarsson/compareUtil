package application;

import object.Tags;
import srvice.DBService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Ghavrilin Oleg on 06.06.2017.
 */
public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        Integer audioId = Integer.parseInt(args[0]);
        String url = args[1];
/*
        Integer audioId = 47;
        String url = "D://sound_memory//sound32.mp3";*/

        Tags audio = TagsUtil.getSoundMetaData(url);

        CompareMP3 compareMP3 = new CompareMP3();

        Map<String,Integer> mapperMap= new DBService().getAllPath();

        Integer owmMappId = new DBService().getMappId(audioId);

        // delete from map value of our mapperSound
        for (Map.Entry<String,Integer> entry : mapperMap.entrySet()) {
            if (entry.getValue().equals(owmMappId)) {
                mapperMap.remove(entry.getKey());
                break;
            }
        }
        Integer mappId = compareMP3.compare(url,audio, mapperMap);

        if(mappId!=null)
            new DBService().changeMapper(audioId, mappId);
    }
    }

