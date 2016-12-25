package src; /**
 * Data Structures
 * EN.600.226
 * HW# 8B
 * 11/20/2012
 *
 * Victor Amaral
 * Phone# 936-494-9224
 * JHED: vamaral1
 * Email: vamaral1@johnshopkins.edu
 *
 * Robert Davis
 * Phone# 410-585-4104
 * JHED: rdavisi1
 * Email: rdavisi1@jhu.edu
 *
 * Lucas Takatori
 * Phone# 425-347-9094
 * JHED: ltakato1
 * Email: ltakato1@johnshopkins.edu
 */

import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
/**src.Driver.*/
public final class Driver {

    /**checkstyle.*/
    private Driver() {
    }
    /** main method.
     * @param args checkstyle
     * @throws IOException exception
     */
    public static void main(String[] args) throws IOException{
        int value = 0, address = 0, tempSize = 0, allocCount = 0;
        int defragBBST = 0, defragHeap = 0, defragHash = 0;
        int failedBBST = 0, failedHeap = 0, failedHash = 0;
        int sizeFailBBST = 0, sizeFailHeap = 0, sizeFailHash = 0;
        long timeAllocBBST = 0, timeAllocHeap = 0, timeAllocHash = 0;
        long timeQSortBBST = 0, timeQSortHeap = 0, timeQSortHash = 0;
        long timeBuckBBST = 0, timeBuckHeap = 0, timeBuckHash = 0;
        int sizeSortBBST = 0, sizeSortHeap = 0, sizeSortHash = 0;
        int bbstAllocNum = 0, heapAllocNum = 0, hashAllocNum = 0;
        double bbstMath = 0, heapMath = 0, hashMath = 0;
        String line, translogline;
        long start = 0, end = 0;
        boolean success = false;
        Block[] sorted = null;
        final int k = 1000;

        BestFit<Block> bbst;
        Heap<Block> heap;
        Hashtable<Block> hash;

        Scanner file = new Scanner(new FileReader(args[0]));
        FileWriter analysis = new FileWriter("output/Analysis.txt");
        FileWriter transLog = new FileWriter("output/TransLog.txt");

        line = file.nextLine();
        //INITIALIZATION AND SETUP OF OUTPUT
        value = Integer.parseInt(line);
        Block block1 = new Block(0, value, 0);
        Block block2 = new Block(0, value, 0);
        Block block3 = new Block(0, value, 0);
        bbst = new BestFit<Block>(value, block1);
        heap = new Heap<Block>(value, block2);
        hash = new Hashtable<Block>(value, block3);
        Allocator<Heap<Block>> heapAlloc = new Allocator<Heap<Block>>(heap);
        Allocator<BestFit<Block>> bbstAlloc
            = new Allocator<BestFit<Block>>(bbst);
        Allocator<Hashtable<Block>> hashAlloc
            = new Allocator<Hashtable<Block>>(hash);

        transLog.write("Memory src.Allocator Translog\r\n");
        transLog.write("Memory Size: " + value + "\r\n");
        transLog.write("Input File Used: " + args[0] + "\r\n");
        transLog.write("---------------------------------------------------"
                + "-------------------------\r\n");
        transLog.write("IN\tREQ\tBEST FIT\t\tWORST FIT\t\tCLOSE FIT\r\n");
        transLog.write("---------------------------------------------------"
                + "-------------------------\r\n");
        analysis.write("Memory src.Allocator Analysis\r\n");
        analysis.write("Memory Size: " + value + "\r\n");
        analysis.write("Input File Used: " + args[0] + "\r\n");
        analysis.write("---------------------------------------------------"
                + "-------------------------\r\n");
        analysis.write(" STATS:\t\t\t\tBEST FIT\tWORST FIT\tCLOSE FIT\r\n");
        analysis.write("---------------------------------------------------"
                + "-------------------------\r\n");

        while (file.hasNext()) {
            line = file.nextLine();

            if (line.startsWith("A")) {
                translogline = line + "\t";
                value = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
                allocCount++;
                translogline += allocCount + "\t";
                // START ALLOC FOR BBST
                //                /*
                start = System.nanoTime();
                address = bbstAlloc.alloc(value);
                end = System.nanoTime();
                timeAllocBBST += end - start;
                bbstAllocNum++;
                if (address < 0) {
                    translogline += "DF ";
                    defragBBST++;
                    start = System.nanoTime();
                    sorted = bbstAlloc.bucket();
                    end = System.nanoTime();
                    timeBuckBBST += end - start;
                    start = System.nanoTime();
                    tempSize = bbstAlloc.quick().length;
                    end = System.nanoTime();
                    bbstAlloc.defrag(sorted);
                    timeQSortBBST += end - start;
                    sizeSortBBST += tempSize;
                    start = System.nanoTime();
                    address = bbstAlloc.alloc(value);
                    end = System.nanoTime();
                    bbstAllocNum++;
                    timeAllocBBST += end - start;
                    if (address < 0) {
                        failedBBST++;
                        sizeFailBBST += value;
                        translogline += "FAILURE\t\t";
                    } else {
                        translogline += "SUCCESS " + address + "\t\t";
                    }
                } else {
                    translogline += "SUCCESS " + address + "\t\t";
                }
                //                */
                //END ALLOC FOR BBST
                //START ALLOC FOR HEAP
                //                /*
                start = System.nanoTime();
                address = heapAlloc.alloc(value);
                end = System.nanoTime();
                timeAllocHeap += end - start;
                heapAllocNum++;
                if (address < 0) {
                    translogline += "DF ";
                    defragHeap++;
                    start = System.nanoTime();
                    if (!heapAlloc.structureEmpty()) {
                        tempSize = heapAlloc.bucket().length;
                    }
                    end = System.nanoTime();
                    timeBuckHeap += end - start;
                    start = System.nanoTime();
                    sorted = heapAlloc.quick();
                    end = System.nanoTime();
                    timeQSortHeap += end - start;
                    sizeSortHeap += tempSize;
                    heapAlloc.defrag(sorted);
                    start = System.nanoTime();
                    address = heapAlloc.alloc(value);
                    end = System.nanoTime();
                    timeAllocHeap += end - start;
                    heapAllocNum++;
                    if (address < 0) {
                        failedHeap++;
                        sizeFailHeap += value;
                        translogline += "FAILURE\t\t";
                    } else {
                        translogline += "SUCCESS " + address + "\t\t";
                    }
                } else {
                    translogline += "SUCCESS " + address + "\t\t";
                }
                //                */
                //END ALLOC FOR HEAP
                //START ALLOC FOR HASH
                //                /*
                start = System.nanoTime();
                address = hashAlloc.alloc(value);
                end = System.nanoTime();
                timeAllocHash += end - start;
                hashAllocNum++;
                if (address < 0) {
                    translogline += "DF ";
                    defragHash++;
                    start = System.nanoTime();
                    if (!hashAlloc.structureEmpty()) {
                        tempSize = hashAlloc.bucket().length;
                    }
                    end = System.nanoTime();
                    timeBuckHash += end - start;
                    start = System.nanoTime();
                    if (!hashAlloc.structureEmpty()) {
                        sorted = hashAlloc.bucket();
                    }
                    end = System.nanoTime();
                    hashAlloc.defrag(sorted);
                    timeQSortHash += end - start;
                    sizeSortHash += tempSize;
                    start = System.nanoTime();
                    address = hashAlloc.alloc(value);
                    end = System.nanoTime();
                    hashAllocNum++;
                    timeAllocHash += end - start;
                    if (address < 0) {
                        failedHash++;
                        sizeFailHash += value;
                        translogline += "FAILURE";
                    } else {
                        translogline += "SUCCESS " + address;
                    }
                } else {
                    translogline += "SUCCESS " + address;
                }
                //                */
                //END ALLOC FOR HASH
                translogline += "\r\n";
                transLog.write(translogline);
                //START DEALLOCATION
            } else if (line.startsWith("D")) {
                translogline = line + "\t\t";
                value = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
                success = bbstAlloc.deAlloc(value);
                if (success) {
                    translogline += "SUCCESS\t\t\t";
                } else {
                    translogline += "FAILURE\t\t\t";
                }
                success = heapAlloc.deAlloc(value);
                if (success) {
                    translogline += "SUCCESS\t\t\t";
                } else {
                    translogline += "FAILURE\t\t\t";
                }
                success = hashAlloc.deAlloc(value);
                if (success) {
                    translogline += "SUCCESS\t";
                } else {
                    translogline += "FAILURE\t";
                }
                translogline += "\r\n";
                transLog.write(translogline);
                //END DEALLOCATION
            } else {
                translogline = line + "\r\n";
                transLog.write(translogline);
            }
        }

        analysis.write("Number of Defrags:\t\t" + defragBBST
                + "\t\t" + defragHeap + "\t\t" + defragHash + "\r\n");
        analysis.write("Number of Failed Allocs:\t" + failedBBST
                + "\t\t" + failedHeap + "\t\t" + failedHash + "\r\n");
        if (failedBBST != 0) {
            bbstMath = ((double) sizeFailBBST) / failedBBST;
            bbstMath = Double.parseDouble(new
                    DecimalFormat("#.##").format(bbstMath));
        } else {
            bbstMath = 0;
        }
        if (failedHeap != 0) {
            heapMath = ((double) sizeFailHeap) / failedHeap;
            heapMath = Double.parseDouble(new
                    DecimalFormat("#.##").format(heapMath));
        } else {
            heapMath = 0;
        }
        if (failedHash != 0) {
            hashMath = ((double) sizeFailHash) / failedHash;
            hashMath = Double.parseDouble(new
                    DecimalFormat("#.##").format(hashMath));
        } else {
            hashMath = 0;
        }
        analysis.write("Avg. Size of Alloc Fail:\t" + bbstMath + "\t\t"
                + heapMath + "\t\t" + hashMath + "\r\n");
        if (sizeSortBBST != 0) {
            bbstMath = ((double) timeQSortBBST / k) / sizeSortBBST;
            bbstMath = Double.parseDouble(new
                    DecimalFormat("#.##").format(bbstMath));
        } else {
            bbstMath = 0;
        }
        if (sizeSortHeap != 0) {
            heapMath = ((double) timeQSortHeap / k) / sizeSortHeap;
            heapMath = Double.parseDouble(new
                    DecimalFormat("#.##").format(heapMath));
        } else {
            heapMath = 0;
        }
        if (sizeSortHash != 0) {
            hashMath = ((double) timeQSortHash / k) / sizeSortHash;
            hashMath = Double.parseDouble(new
                    DecimalFormat("#.##").format(hashMath));
        } else {
            hashMath = 0;
        }
        analysis.write("Avg. time/size for QSort:\t" + bbstMath + "ms\t\t"
                + heapMath + "ms\t\t" + hashMath + "ms\r\n");
        if (sizeSortBBST != 0) {
            bbstMath = ((double) timeBuckBBST / k) / sizeSortBBST;
            bbstMath = Double.parseDouble(new
                    DecimalFormat("#.##").format(bbstMath));
        } else {
            bbstMath = 0;
        }
        if (sizeSortHeap != 0) {
            heapMath = ((double) timeBuckHeap / k) / sizeSortHeap;
            heapMath = Double.parseDouble(new
                    DecimalFormat("#.##").format(heapMath));
        } else {
            heapMath = 0;
        }
        if (sizeSortHash != 0) {
            hashMath = ((double) timeBuckHash / k) / sizeSortHash;
            hashMath = Double.parseDouble(new
                    DecimalFormat("#.##").format(hashMath));
        } else {
            hashMath = 0;
        }
        analysis.write("Avg. time/size for Bucket:\t" + bbstMath + "ms\t\t"
                + heapMath + "ms\t\t" + hashMath + "ms\r\n");
        if (bbstAllocNum != 0) {
            bbstMath = ((double) timeAllocBBST / k) / bbstAllocNum;
            bbstMath = Double.parseDouble(new
                    DecimalFormat("#.##").format(bbstMath));
        } else {
            bbstMath = 0;
        }
        if (heapAllocNum != 0) {
            heapMath = ((double) timeAllocHeap / k) / heapAllocNum;
            heapMath = Double.parseDouble(new
                    DecimalFormat("#.##").format(heapMath));
        } else {
            heapMath = 0;
        }
        if (hashAllocNum != 0) {
            hashMath = ((double) timeAllocHash / k) / hashAllocNum;
            hashMath = Double.parseDouble(new
                    DecimalFormat("#.##").format(hashMath));
        } else {
            hashMath = 0;
        }
        analysis.write("Avg. time/size for Alloc:\t"  + bbstMath + "ms\t\t"
                + heapMath + "ms\t\t" + hashMath + "ms\r\n");
        file.close();
        analysis.close();
        transLog.close();
    }
}

