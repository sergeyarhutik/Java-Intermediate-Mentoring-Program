package com.epam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class FileFolderScanner {
    static long filesSize = 0;
    static int filesCount = 0;
    static int foldersCount = 0;
    static final Object testLock = new Object();

    public static void main(String[] args) {
        String PATH;
        final File folder;
        long startTime;
        long endTime;

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter full folder path to scan: ");
        PATH = scan.next();
        folder = new File(PATH);

        startTime = System.currentTimeMillis();
        listFiles(folder);
        endTime = System.currentTimeMillis();
        System.out.println("\nTotal scan time: " + (endTime - startTime) +"ms");
        System.out.println("Folders: " + foldersCount + ", files: " + filesCount + ", files size: "
                + filesSize + " bytes (" + filesSize/1024 + " Kb)");

        ForkJoinPool fjp = new ForkJoinPool();
        startTime = System.currentTimeMillis();
        fjp.invoke(new ListFilesFJP(folder));
        endTime = System.currentTimeMillis();
        System.out.println("\nTotal scan time with FJP: " + (endTime - startTime) +"ms");
        System.out.println("Folders: " + ListFilesFJP.foldersCount + ", files: " + ListFilesFJP.filesCount
                + ", files size: " + ListFilesFJP.filesSize + " bytes (" + ListFilesFJP.filesSize/1024 + " Kb)");
    }

    public static void listFiles(final File folder) {
        for (final File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isDirectory()) {
                foldersCount++;
                listFiles(file);
            } else {
                filesCount++;
                filesSize += file.length();
            }
        }
    }

}

class ListFilesFJP extends RecursiveAction {
    static long filesSize = 0;
    static int filesCount = 0;
    static int foldersCount = 0;
    File folder;

    public ListFilesFJP(File folder) {
        this.folder = folder;
    }

    @Override
    protected void compute() {
        List<RecursiveAction> tasksList = new ArrayList<>();
        for (final File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isDirectory()) {
                foldersCountIncrement();
                ListFilesFJP task1 = new ListFilesFJP(file);
                tasksList.add(task1);
                task1.fork();
            } else {
                filesCountIncrement();
                filesSizeIncrement(file.length());
            }
        }
        for(RecursiveAction task : tasksList) {
            task.join();
        }
    }

    void filesCountIncrement() {
        synchronized (FileFolderScanner.testLock) {
            filesCount++;
        }
    }

    void filesSizeIncrement(long incSize) {
        synchronized (FileFolderScanner.testLock) {
            filesSize += incSize;
        }
    }

    void foldersCountIncrement() {
        synchronized (FileFolderScanner.testLock) {
            foldersCount++;
        }
    }

}