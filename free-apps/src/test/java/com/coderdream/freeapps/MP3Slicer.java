package com.coderdream.freeapps;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MP3Slicer {

    public static List<byte[]> split(File sourceFile, long segmentSize)
        throws InvalidDataException, UnsupportedTagException, IOException {
        List<byte[]> segmentBytesList = new ArrayList<>();
        Mp3File mp3File = new Mp3File(sourceFile);
        int startOffset = mp3File.getStartOffset();
        byte[] fileBytes = fileToByteArray(sourceFile);

        byte[] segmentHeadBytes = getMp3FileHeadBytes(mp3File, fileBytes);

        for (int start = startOffset; start < fileBytes.length; start += segmentSize) {
            int end = (int) Math.min(start + segmentSize, fileBytes.length);

            byte[] segmentBodyBytes = new byte[end - start];
            System.arraycopy(fileBytes, start, segmentBodyBytes, 0, end - start);

            byte[] segmentBytes = new byte[segmentHeadBytes.length + segmentBodyBytes.length];
            System.arraycopy(segmentHeadBytes, 0, segmentBytes, 0, segmentHeadBytes.length);
            System.arraycopy(segmentBodyBytes, 0, segmentBytes, segmentHeadBytes.length, segmentBodyBytes.length);

            segmentBytesList.add(segmentBytes);
        }
        return segmentBytesList;
    }

    public static byte[] split(File sourceFile, int startSecond, int endSecond)
        throws InvalidDataException, UnsupportedTagException, IOException {
        Mp3File mp3File = new Mp3File(sourceFile);
        long lengthInSeconds = mp3File.getLengthInSeconds(); // getLengthInMilliseconds
        if (startSecond < 0) {
            throw new IllegalArgumentException("Start time is invalid.");
        }
        if (endSecond > lengthInSeconds) {
            throw new IllegalArgumentException("End time exceeds the length of the MP3 file.");
        }

        byte[] fileBytes = fileToByteArray(sourceFile);

        int startOffset = mp3File.getStartOffset();
        byte[] segmentHeadBytes = getMp3FileHeadBytes(mp3File, fileBytes);

        int start = startSecond * mp3File.getBitrate() * 1000 / 8 + startOffset;
        int end = endSecond * mp3File.getBitrate() * 1000 / 8 + startOffset;

        byte[] segmentBodyBytes = new byte[end - start];
        System.arraycopy(fileBytes, start, segmentBodyBytes, 0, segmentBodyBytes.length);

        byte[] segmentBytes = new byte[segmentHeadBytes.length + segmentBodyBytes.length];
        System.arraycopy(segmentHeadBytes, 0, segmentBytes, 0, segmentHeadBytes.length);
        System.arraycopy(segmentBodyBytes, 0, segmentBytes, segmentHeadBytes.length, segmentBodyBytes.length);

        return segmentBytes;
    }

    public static byte[] splitLengthInMilliseconds(File sourceFile, int startMilliseconds, int endMilliseconds)
        throws InvalidDataException, UnsupportedTagException, IOException {
        Mp3File mp3File = new Mp3File(sourceFile);
        long lengthInSeconds = mp3File.getLengthInMilliseconds(); // getLengthInMilliseconds
        if (startMilliseconds < 0) {
            throw new IllegalArgumentException("Start time is invalid.");
        }
        if (endMilliseconds > lengthInSeconds) {
            throw new IllegalArgumentException("End time exceeds the length of the MP3 file.");
        }

        byte[] fileBytes = fileToByteArray(sourceFile);

        int startOffset = mp3File.getStartOffset();
        byte[] segmentHeadBytes = getMp3FileHeadBytes(mp3File, fileBytes);

        int start = startMilliseconds * mp3File.getBitrate() / 8 + startOffset;
        int end = endMilliseconds * mp3File.getBitrate() / 8 + startOffset;

        byte[] segmentBodyBytes = new byte[end - start];
        System.arraycopy(fileBytes, start, segmentBodyBytes, 0, segmentBodyBytes.length);

        byte[] segmentBytes = new byte[segmentHeadBytes.length + segmentBodyBytes.length];
        System.arraycopy(segmentHeadBytes, 0, segmentBytes, 0, segmentHeadBytes.length);
        System.arraycopy(segmentBodyBytes, 0, segmentBytes, segmentHeadBytes.length, segmentBodyBytes.length);

        return segmentBytes;
    }

    private static byte[] fileToByteArray(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            long fileSize = file.length();
            if (fileSize > Integer.MAX_VALUE) {
                throw new IOException("File is too large to fit in a byte array");
            }
            byte[] bytes = new byte[(int) fileSize];
            fis.read(bytes);

            return bytes;

        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] getMp3FileHeadBytes(Mp3File mp3File, byte[] fileBytes) {
        int startOffset = mp3File.getStartOffset();
        byte[] headBytes = new byte[startOffset];
        System.arraycopy(fileBytes, 0, headBytes, 0, headBytes.length);
        return headBytes;
    }
}
