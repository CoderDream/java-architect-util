package cn.xupengzhuang.chapter08.pipeline;

public abstract class ProcessingObject<T> {
    protected ProcessingObject<T> successor;

    public void setProcessingObject(ProcessingObject<T> successor) {
        this.successor = successor;
    }

    public T handle(T input){
        T t = handleWork(input);
        if (successor != null){
            return successor.handle(t);
        }
        return t;
    }
    public abstract T handleWork(T input);
}
