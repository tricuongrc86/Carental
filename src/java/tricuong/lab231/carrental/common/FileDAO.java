/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/**
 *
 * @author TriCuong
 */
public class FileDAO {

     public HashMap<String, String> readFile(String fileName) throws Exception {

        HashMap<String, String> hashFile = new HashMap<String, String>();
        FileReader f = null;
        BufferedReader br = null;
        try {
            f = new FileReader(fileName);
            br = new BufferedReader(f);
            String line;
            while ((line = br.readLine()) != null) {
                String part[] = line.split("=");
              //  if(part.length==2){
                String key = part[0];
                String value = part[1];
                hashFile.put(key, value);
             //   }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
            if (f != null) {
                f.close();
            }
        }
        return hashFile;
    }
}
