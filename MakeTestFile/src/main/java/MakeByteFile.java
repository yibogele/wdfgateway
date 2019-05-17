import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 11:04 2018/8/13
 * Modified By:
 */
public class MakeByteFile {
    public static void main(String[] args) {

        makeShakeTestFile();
        makeDumpRecordTestFile();
        makeCardNumTestFile();
    }

    private static void makeShakeTestFile(){
        try {
            //
            File f = new File("E:\\work\\WDFGateway\\testShake.txt");
            //
//            byte data[] = { 65,66,65,0,67,25,102,53,51,57,70,77,5,-40,-1,51,-119,
//                    -122,4,4,25,24,-128,9,23,2,0,0,0,0,0,0,0,0,29,0,0,0,0,0,0,0,0
//                    ,0,0,0,35,35,0,0,2,0,4,24,6,-67,18,0,4,24,72,-24,6,-81,74 };

            byte data[] = { 65,66,65,0,67,34,113,38,53,49,71,77,5,-40,-1,52,-119,
                    -122,6,23,4,0,37,-125,120,103,-93,-101,-58,6,0,-110,18,2,25,0,
                    0,0,0,0,0,0,0,0,0,0,44,44,0,0,2,0,0,36,23,-48,6,0,0,36,0,110,8,
                    -75,-43};

            // 创建基于文件的输出流
            FileOutputStream fos = new FileOutputStream(f);
            // 把数据写入到输出流
            fos.write(data);
            // 关闭输出流
            fos.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void makeDumpRecordTestFile(){
        try {
            //
            File f = new File("E:\\work\\WDFGateway\\testDumpRecord.txt");

            byte data[] = {
                    65, 66, 42, 0, 67, 33, 86, 20, 51, 57,
                    70, 77, 5, -45, -1, 51, 24, 5, 0, 0,
                    0, 0, 18, 6, 22, 11, 48, 21, 122, 48,
                    48, 48, 48, 49, 51, 53, 49, 57, 49, 0,
                    45, 112
            };

            // 创建基于文件的输出流
            FileOutputStream fos = new FileOutputStream(f);
            // 把数据写入到输出流
            fos.write(data);
            // 关闭输出流
            fos.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void makeCardNumTestFile(){
        try {
            //
            File f = new File("E:\\work\\WDFGateway\\testCardNum.txt");

            byte data[] = {
//                    65, 66, 30, 0, 67,
//                    33, 86, 20, 51, 57,
//                    70, 77, 5, -45, -1,
//                    51,   122, 48, 48, 48,
//                    48, 49, 51, 53, 49,
//                    57, 49, 0, -128, 85
                    65, 66, 35, 0, 67,
                    21, 80, 32, 55, 56,
                    67, 78, 5, -39, -1,
                    55, 49, 53, 52, 49,
                    49, 52, 55, 54, 52,
                    56, 53, 54, 55, 51,
                    55, 56, 0, -80, -17
            };

            // 创建基于文件的输出流
            FileOutputStream fos = new FileOutputStream(f);
            // 把数据写入到输出流
            fos.write(data);
            // 关闭输出流
            fos.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
