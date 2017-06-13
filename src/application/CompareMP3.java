package application;

import object.Tags;

import java.io.IOException;
import java.util.Map;


/**
 * author Ghavrilin Oleg
 * 03.06.2017
 *
 */
public class CompareMP3 {


    // change mapperSaund in Audio object if found a comparison
    public Integer compare(String path,Tags audio, Map<String,Integer> mapperMap) throws IOException {

        for(String url: mapperMap.keySet())
        {
            if(compareTags(audio,TagsUtil.getSoundMetaData(url)))
                return mapperMap.get(url);
        }

        for(String url: mapperMap.keySet())
        {
            if(compareByteArray(path,url))
                return mapperMap.get(url);
           }
        return null;
    }

    //true if audio1 == audio2
    private static Boolean  compareTags(Tags audio1,Tags audio2)
    {
        if(audio1.getAuthor().equals(audio2.getAuthor())&&
                audio1.getAlbum().equals(audio2.getAlbum())&&
                audio1.getName().equals(audio2.getName())&&
                audio1.getYear().equals(audio2.getYear())&&
                audio1.getComment().equals(audio2.getComment()))
        {return true;}
        return false;
    }

    //true if audio1 == audio2
     private static Boolean compareByteArray(String path1, String path2) throws IOException {

        byte[] byteArray1 = TagsUtil.getByteArrayFromMapper(path1);

        byte[] byteArray2 = TagsUtil.getByteArrayFromMapper(path2);

        if(byteArray1.length != byteArray2.length)
        {
            return false;
        }else if (byteArray1 == byteArray2)
        {
            return true;
        }
        for(int i=0;i<byteArray1.length;i++)
            if(byteArray1[i]!=byteArray2[i])
                return false;
        return true;
    }
}
