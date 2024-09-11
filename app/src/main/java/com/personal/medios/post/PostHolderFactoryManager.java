package com.personal.medios.post;

public final class PostHolderFactoryManager {

    public static PostHolderFactory getFactory(int type){
        switch (type){
            case 0: return new VideoHolderFactory();
            case 1: return new ImageHolderFactory();
            case 2: return new ImageSequenceHolderFactory();
            default: throw new IllegalArgumentException();
        }
    }
}
