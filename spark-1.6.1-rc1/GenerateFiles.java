import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 
 */

/**
 * @author RyanWork
 *
 */
public class GenerateFiles 
{
	private static final int BUFFERSIZE = 8192;
	private static final int BYTE_LENGTH = 8; // Number of bits in a byte
	private static final int GB = 1073741824; // Number of Bytes in 1 GB
	private static final String CWD = System.getProperty("user.dir");
	
	public static void main(String [ ] args) throws IOException
	{
		File moby = new File(CWD + "/mobydick.txt");
		byte[] mobyBuff = new byte[BUFFERSIZE];
		FileInputStream mobyStream = new FileInputStream(moby);
		mobyStream.read(mobyBuff);
		mobyStream.close();
		System.out.println("Generating File....");
		double fSize = (Double.parseDouble(args[1]) * GB);
		File genFile = new File(args[0]);
		FileOutputStream genStream = new FileOutputStream(genFile);
		double bytes = 0;
		while (bytes <= fSize)
		{
			genStream.write(mobyBuff, 0, BUFFERSIZE);
			bytes += BUFFERSIZE;
		}
		genStream.close();
		System.out.println("Finished\n");


		// System.out.println("Generating 512 MB File....");
		// // Generate 512 MB File
		// int fSize = (int) (0.5 * GB);
		// File genFile = new File("fiveHundMB.txt");
		// FileOutputStream genStream = new FileOutputStream(genFile);
		// int bytes = 0;
		// while (bytes <= fSize)
		// {
		// 	genStream.write(mobyBuff, 0, BUFFERSIZE);
		// 	bytes += BUFFERSIZE;
		// }
		// genStream.close();
		// System.out.println("Finished\n");

		// System.out.println("Generating 2 GB File....");
		// // Gernerate 2 GB File
		// int fSize2 = 2 * GB;
		// File genFile2 = new File("twoGB.txt");
		// FileOutputStream genStream2 = new FileOutputStream(genFile2);
		// int bytes2 = 0;
		// while (bytes2 <= fSize2)
		// {
		// 	genStream2.write(mobyBuff, 0, BUFFERSIZE);
		// 	bytes2 += BUFFERSIZE;
		// }
		// genStream2.close();
		// System.out.println("Finished\n");

		// System.out.println("Generating 5 GB File....");
		// // Generate 5 GB File
		// int fSize3 = 5 * GB;
		// File genFile3 = new File("fiveGB.txt");
		// FileOutputStream genStream3 = new FileOutputStream(genFile3);
		// int bytes3 = 0;
		// while (bytes3 <= fSize3)
		// {
		// 	genStream3.write(mobyBuff, 0, BUFFERSIZE);
		// 	bytes3 += BUFFERSIZE;
		// }
		// genStream3.close();
		// System.out.println("Finished\n");
	}
}
