package com.coderdream.freeapps.component;

public class ByteArray {
    private byte[] data;
    private int length;

    public ByteArray(){
        length = 0;
        data = new byte[length];
    }

    public ByteArray(byte[] ba){
        data = ba;
        length = ba.length;
    }

    /**
     合并数组
     */
    public  void cat(byte[] second, int offset, int length){

        if(this.length + length > data.length) {
            int allocatedLength = Math.max(data.length, length);
            byte[] allocated = new byte[allocatedLength << 1];
            System.arraycopy(data, 0, allocated, 0, this.length);
            System.arraycopy(second, offset, allocated, this.length, length);
            data = allocated;
        }else {
            System.arraycopy(second, offset, data, this.length, length);
        }

        this.length += length;
    }

    public  void cat(byte[] second){
        cat(second, 0, second.length);
    }

    public byte[] getArray(){
        if(length == data.length){
            return data;
        }

        byte[] ba = new byte[length];
        System.arraycopy(data, 0, ba, 0, this.length);
        data = ba;
        return ba;
    }

    public int getLength(){
        return length;
    }
}
