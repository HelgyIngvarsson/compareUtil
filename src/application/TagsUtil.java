package application;


import object.Tags;

import java.io.*;
import java.util.Arrays;

/**
 * author Ghavrilin Oleg
 * 29.05.2017
 *
 */
public class TagsUtil {

    public static Tags getSoundMetaData(String url) throws IOException
    {
        File file  = new File(url);

        InputStream in = new FileInputStream(file.getAbsolutePath());

        Tags audio = new Tags();

        byte [] byteArray = inputStreamToByteArray(in);
        byte [] tag128 = Arrays.copyOfRange(byteArray,byteArray.length-128,byteArray.length);
        byte [] tag227 = Arrays.copyOfRange(byteArray,byteArray.length-227,byteArray.length);

        if(new String(Arrays.copyOfRange(tag128,0,3)).equals("TAG"))
        {
            return  audio = getID3(audio,tag128);
        }else if(new String(Arrays.copyOfRange(tag227,0,4)).equals("TAG+"))
        {
           return audio = getID3v1(audio,tag227);
        }
        return audio;
    }


    public  static byte[] getByteArrayFromMapper(String path) throws IOException {

        File file = new File(path);

        InputStream in = new FileInputStream(file.getAbsolutePath());

        byte[] bytes = inputStreamToByteArray(in);
        return bytes;
    }

//    getting Tags 128 bytes
    private static Tags  getID3(Tags audio,byte[] tags)
    {
        //audio.setTitle(new String(Arrays.copyOfRange(tags,0,3)));
        audio.setName(new String(Arrays.copyOfRange(tags,3,33)));
        audio.setAuthor(new String(Arrays.copyOfRange(tags,33,63)));
        audio.setAlbum(new String(Arrays.copyOfRange(tags,63,93)));
        audio.setYear(new String(Arrays.copyOfRange(tags,93,97)));
        audio.setComment(new String(Arrays.copyOfRange(tags,97,127)));
        return audio;
    }

//    getting Tags 227 bytes
    private static Tags getID3v1(Tags audio,byte[] tags)
    {
        //audio.setTitle(new String(Arrays.copyOfRange(tags,0,4)));
        audio.setName(new String(Arrays.copyOfRange(tags,4,64)));
        audio.setAuthor(new String(Arrays.copyOfRange(tags,64,124)));
        audio.setAlbum(new String(Arrays.copyOfRange(tags,124,184)));
//        soundMetaData.setYear(new String(Arrays.copyOfRange(tags,93,97)));
//        soundMetaData.setComment(new String(Arrays.copyOfRange(tags,97,127)));
        return audio;
    }



    private static byte[] inputStreamToByteArray(InputStream inStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = inStream.read(buffer)) > 0) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
