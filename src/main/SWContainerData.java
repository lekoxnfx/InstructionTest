package main;

import importData.ContainerInfo;
import importData.ContainerInfoProcess;
import importData.ImportData;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.StringBuffer;
import java.util.List;

/**
 * Created by leko on 2016/1/15.
 */
public class SWContainerData extends SwingWorker {
    public File inFile;
    public String inJson;
    public boolean noError = true;
    public String result;

    @Override
    protected Object doInBackground() throws Exception {
        //读取File
        StringBuffer fileContent = new StringBuffer(); // 文件很长的话建议使用StringBuffer
        try {
            FileInputStream fis = new FileInputStream(inFile);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                fileContent.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = e.toString();
            noError = false;
        }
        System.out.println(fileContent);
        //解析字符串
        if(noError){
            try {
                List<ContainerInfo> containerInfoList = new ContainerInfoProcess().getContainerInfo(fileContent);
                ImportData.containerInfoList = containerInfoList;
                if (containerInfoList == null){
                    noError = false;
                    result = "解析失败！";

                }else{
//                    int size20num = 0;
//                    int size40num = 0;
//                    for (ContainerInfo containerInfo:containerInfoList) {
//                        System.out.print(containerInfo.getIYC_CNTR_AREA_ID()+" ");
//                        System.out.print(containerInfo.getIYC_CNTRNO()+" ");
//                        System.out.print(containerInfo.getIYC_CSZ_CSIZECD()+" ");
//                        if (containerInfo.getIYC_CSZ_CSIZECD().equals("20")){
//                            size20num=size20num+1;
//                        }
//                        else if (containerInfo.getIYC_CSZ_CSIZECD().equals("40")){
//                            size40num=size40num+1;
//                        }
//                        System.out.print(containerInfo.getIYC_CTYPECD()+" ");
//                        System.out.print(containerInfo.getIYC_PORTCD()+" ");
//                        System.out.print(containerInfo.getIYC_WEIGHT()+" ");
//                        System.out.print(containerInfo.getIYC_YLOCATION()+" ");
//                        System.out.println();
//                    }
//                    System.out.println(containerInfoList.size());
//                    System.out.println(size20num);
//                    System.out.println(size40num);
                    System.out.println("在场箱数据解析成功！");
                }

            } catch (Exception e) {
                e.printStackTrace();
                result = e.toString();
            }
        }
        return null;
    }
}
